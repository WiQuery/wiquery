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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.behavior.HeaderContributor;
import org.odlabs.wiquery.core.commons.listener.WiQueryPluginRenderingListener;

/**
 * $Id: WiQueryInstantiationListener.java 89 2009-06-02 21:42:53Z lionel.armanet
 * $
 * <p>
 * Listens to WiQuery components instantiation and automatically binds a
 * {@link WiQueryCoreHeaderContributor} to these components.
 * </p>
 * <p>
 * The added header contributor will generated the needed JavaScript code and
 * will import all needed resources (e.g. CSS/JavaScript files).
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.6
 */
public class WiQueryInstantiationListener implements
		IComponentInstantiationListener, Serializable {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -7398777039788778234L;
	
	/** Singleton of {@link WiQueryInstantiationListener} object. */
	private static WiQueryInstantiationListener current;
	
	/** Mutex */
	private static Object mutex = new Object();
	
	// Properties
	private final boolean autoImportJQueryResource;
	private final List<WiQueryPluginRenderingListener> listeners;
	
	/**
	 * Get {@link WiQueryInstantiationListener} for current thread.
	 * 
	 * @return The current thread's {@link WiQueryInstantiationListener}
	 */
	public static WiQueryInstantiationListener get() {
		if (current == null) {
			throw new WicketRuntimeException(
					"There is no WiQueryInstantiationListener attached to the application " + 
					Thread.currentThread().getName());
		}
		
		return current;
	}
	
	/**
	 * Default constructor
	 */
	public WiQueryInstantiationListener() {
		super();
		
		synchronized (mutex) {
			if(current != null){
				throw new WicketRuntimeException("A WiQueryInstantiationListener is already instanciate");
			}
			
			current = this;
		}
		
		// try to read some options
		Application app = Application.get();
		
		if(app.getClass().isAnnotationPresent(WiQueryOptions.class)){
			WiQueryOptions options = app.getClass().getAnnotation(WiQueryOptions.class);
			autoImportJQueryResource = options.autoImportJQueryResource();
			listeners = new ArrayList<WiQueryPluginRenderingListener>();
			
			if(options.listeners() != null && options.listeners().length > 0){
				for(Class<? extends WiQueryPluginRenderingListener> plugin : options.listeners()) {
					try {
						listeners.add((WiQueryPluginRenderingListener) plugin.newInstance());
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		} else {
			autoImportJQueryResource = true;
			listeners = new ArrayList<WiQueryPluginRenderingListener>();
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.application.IComponentInstantiationListener#onInstantiation(org.apache.wicket.Component)
	 */
	public void onInstantiation(final Component component) {
		// theme management
		if (component instanceof IWiQueryPlugin) {
			WiQueryCoreHeaderContributor wickeryHeaderContributor = WiQueryCoreHeaderContributor.bindToRequestCycle();
			// binding component as a plugin
			wickeryHeaderContributor.addPlugin((IWiQueryPlugin) component);
			component.add(new HeaderContributor(wickeryHeaderContributor));
		}
	}

	/**
	 * @return the list of listener from the listeners option
	 */
	public ListIterator<WiQueryPluginRenderingListener> getListeners() {
		return listeners.listIterator();
	}
	
	/**
	 * @return the state of the autoImportJQueryResource option
	 */
	public boolean isAutoImportJQueryResource() {
		return autoImportJQueryResource;
	}
}
