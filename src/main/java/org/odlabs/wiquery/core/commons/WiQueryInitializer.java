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
package org.odlabs.wiquery.core.commons;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.wicket.Application;
import org.apache.wicket.IInitializer;
import org.apache.wicket.MetaDataKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * $Id$
 * <p>
 * {@link IInitializer} to retrieve settings for wiQuery
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class WiQueryInitializer implements IInitializer {
	/**
	 * Meta data for {@link WiQueryInstantiationListener}.
	 */
	protected static final MetaDataKey<WiQuerySettings> WIQUERY_INSTANCE_KEY = new MetaDataKey<WiQuerySettings>() {
		private static final long serialVersionUID = 1L;
	};

	/** Logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WiQueryInitializer.class);

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.wicket.IInitializer#init(org.apache.wicket.Application)
	 */
	public void init(Application application) {
		application.getComponentPostOnBeforeRenderListeners().add(
				new WiQueryRenderingListener());

		// check for IWiQuerySettings on the application
		WiQuerySettings settings = application instanceof IWiQuerySettings ? ((IWiQuerySettings) application)
				.getWiQuerySettings() : null;
		application.setMetaData(WIQUERY_INSTANCE_KEY,
				settings == null ? new WiQuerySettings() : settings);

		// IWiQueryInitializer treatments
		retrieveAndCallInitializers(application, settings);
	}

	private IWiQueryInitializer getIWiQueryInitializer(String className) {
		if (className == null) {
			return null;
		}

		try {
			return (IWiQueryInitializer) Class.forName(className).newInstance();
			
		} catch (InstantiationException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return null;
	}

	/**
	 * Method finding and calling the {@link IWiQueryInitializer}
	 */
	private void retrieveAndCallInitializers(Application application,
			WiQuerySettings wiQuerySettings) {
		InputStream in = null;
		Iterator<URL> resources = null;
		IWiQueryInitializer wiQueryInitializer;
		List<IWiQueryInitializer> initializers = new ArrayList<IWiQueryInitializer>();
		Properties properties = null;
		URL url = null;

		try {
			resources = application.getApplicationSettings().getClassResolver()
					.getResources("wiquery.properties");
			while (resources.hasNext()) {
				try {
					url = resources.next();
					LOGGER.info("Find the wiQuery initializer:", url.toString());

					properties = new Properties();
					in = url.openStream();
					properties.load(in);

					wiQueryInitializer = getIWiQueryInitializer(properties
							.getProperty("initializer"));

					if (wiQueryInitializer != null) {
						initializers.add(wiQueryInitializer);
					}

					wiQueryInitializer = getIWiQueryInitializer(properties
							.getProperty(application.getName() + "-initializer"));

					if (wiQueryInitializer != null) {
						initializers.add(wiQueryInitializer);
					}

				} finally {
					if (in != null) {
						in.close();
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Unable to load initializers file", e);
		}

		if (initializers.size() > 0) {
			for (IWiQueryInitializer initializer : initializers) {
				LOGGER.info("[" + application.getName() + "] init: "
						+ initializer);
				initializer.init(application, wiQuerySettings);
			}
		}
	}
}
