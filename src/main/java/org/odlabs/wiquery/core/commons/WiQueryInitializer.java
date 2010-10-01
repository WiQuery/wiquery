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

import org.apache.wicket.Application;
import org.apache.wicket.IInitializer;
import org.apache.wicket.MetaDataKey;

/**
 * $Id$
 * 
 * <p>
 * 	{@link IInitializer} to retrieve settings for wiQuery
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
	
	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.IInitializer#init(org.apache.wicket.Application)
	 */
	public void init(Application application) {
		application.addPostComponentOnBeforeRenderListener(new WiQueryRenderingListener());
		//check for IWiQuerySettings on the application
		WiQuerySettings settings = application instanceof IWiQuerySettings ? ((IWiQuerySettings) application).getWiQuerySettings() : null;
		application.setMetaData(WIQUERY_INSTANCE_KEY, settings == null ? new WiQuerySettings() : settings);
	}
}
