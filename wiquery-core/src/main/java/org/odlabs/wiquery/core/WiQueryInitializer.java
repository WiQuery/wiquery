/*
 * Copyright (c) 2009 WiQuery team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.odlabs.wiquery.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import org.apache.wicket.Application;
import org.apache.wicket.IInitializer;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.lang.WicketObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * $Id: WiQueryInitializer.java 721 2011-03-09 08:32:12Z hielke.hoeve@gmail.com $
 * <p>
 * {@link IInitializer} to retrieve settings for wiQuery
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class WiQueryInitializer implements IInitializer
{
	public static final MetaDataKey<WiQuerySettings> WIQUERY_INSTANCE_KEY =
		new MetaDataKey<WiQuerySettings>()
		{
			private static final long serialVersionUID = 1L;
		};

	private static final Logger LOGGER = LoggerFactory.getLogger(WiQueryInitializer.class);

	public void init(Application application)
	{
		Application.get().getComponentInstantiationListeners()
			.add(new WiQueryPluginInstantiationListener());

		// check for WiQuerySettings on the application
		WiQuerySettings settings = application.getMetaData(WIQUERY_INSTANCE_KEY);

		// create new one when application has none
		if (settings == null)
		{
			settings = new WiQuerySettings();
			// apply IWiQuerySettings to the applications metadata
			application.setMetaData(WIQUERY_INSTANCE_KEY, settings);
			// IWiQueryInitializer treatments
			retrieveAndCallInitializers(application, settings);
		}
		else
		{
			LOGGER.info("application already hasWiQuerySettings");
		}
	}

	public void destroy(Application application)
	{
		// noop
	}

	/**
	 * <p>
	 * Method finding and calling the {@link IWiQueryInitializer}.
	 * </p>
	 * 
	 * This will find find wiquery.properties files in the following order:
	 * <ul>
	 * <li>wiquery jar/bundle</li>
	 * <li>
	 * user's application jar/bundle</li>
	 * <li>
	 * all other resources</li>
	 * </ul>
	 * 
	 */
	private void retrieveAndCallInitializers(Application application,
			WiQuerySettings wiQuerySettings)
	{

		// Load any wiquery properties files we can find
		try
		{
			// Load properties files used by all libraries
			final Iterator<URL> resources =
				application.getApplicationSettings().getClassResolver()
					.getResources("wiquery.properties");
			while (resources.hasNext())
			{
				InputStream in = null;
				try
				{
					final URL url = resources.next();
					final Properties properties = new Properties();
					in = url.openStream();
					properties.load(in);
					load(application, wiQuerySettings, properties);
				}
				finally
				{
					IOUtils.close(in);
				}
			}
		}
		catch (IOException e)
		{
			throw new WicketRuntimeException("Unable to load initializers file", e);
		}

		// now call any initializers we read
		callInitializers(application, wiQuerySettings);
	}

	/**
	 * @param wiQuerySettings
	 * @param properties
	 *            Properties map with names of any library initializers in it
	 */
	private void load(Application application, WiQuerySettings wiQuerySettings,
			Properties properties)
	{
		addInitializer(wiQuerySettings, properties.getProperty("initializer"));
		addInitializer(wiQuerySettings,
			properties.getProperty(application.getName() + "-initializer"));
	}

	/**
	 * Construct and add initializer from the provided class name.
	 * 
	 * @param className
	 */
	private void addInitializer(WiQuerySettings wiQuerySettings, String className)
	{
		IWiQueryInitializer initializer =
			(IWiQueryInitializer) WicketObjects.newInstance(className);
		if (initializer != null)
		{
			wiQuerySettings.addInitializer(initializer);
		}
	}

	/**
	 * Iterate initializers list, calling any instances found in it.
	 * 
	 * @param wiQuerySettings
	 */
	private void callInitializers(Application application, WiQuerySettings wiQuerySettings)
	{
		for (IWiQueryInitializer initializer : wiQuerySettings.getInitializers())
		{
			LOGGER.info("[" + application.getName() + "] init: " + initializer);
			initializer.init(application, wiQuerySettings);
		}
	}
}
