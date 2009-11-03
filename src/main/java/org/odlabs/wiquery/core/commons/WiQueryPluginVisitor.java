package org.odlabs.wiquery.core.commons;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.Component.IVisitor;

public class WiQueryPluginVisitor implements IVisitor<Component>, Serializable {

	private static final long serialVersionUID = -4147296857772880048L;
	
	private IWiQueryPlugin wiQueryPlugin;
	
	public WiQueryPluginVisitor(IWiQueryPlugin wiQueryPlugin) {
		super();
		this.wiQueryPlugin = wiQueryPlugin;
	}

	public Object component(Component component) {
		if (component.getBehaviors().contains(this.wiQueryPlugin)) {
			return Boolean.TRUE;
		}
		if (component.equals(this.wiQueryPlugin)) {
			return Boolean.TRUE;
		}
		return IVisitor.CONTINUE_TRAVERSAL;
	}
	
}
