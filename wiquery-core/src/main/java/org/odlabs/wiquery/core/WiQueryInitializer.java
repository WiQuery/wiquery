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

import org.apache.wicket.Application;
import org.apache.wicket.IInitializer;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;
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

	@Override
	public void init(Application application)
	{
		// set HeaderResponseDecorator in order to check for allowed resources
		application.setHeaderResponseDecorator(new IHeaderResponseDecorator()
		{
			@Override
			public IHeaderResponse decorate(IHeaderResponse response)
			{
				return new WiQueryDecoratingHeaderResponse(response);
			}
		});

		// check for WiQuerySettings on the application
		WiQuerySettings settings = application.getMetaData(WIQUERY_INSTANCE_KEY);

		// create new one when application has none
		if (settings == null)
		{
			settings = new WiQuerySettings();
			// apply IWiQuerySettings to the applications metadata
			application.setMetaData(WIQUERY_INSTANCE_KEY, settings);
		}
		else
		{
			LOGGER.info("application already hasWiQuerySettings");
		}
	}

	@Override
	public void destroy(Application application)
	{
		// noop
	}
}
