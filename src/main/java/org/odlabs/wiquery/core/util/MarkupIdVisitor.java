package org.odlabs.wiquery.core.util;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Component.IVisitor;

/**
 * $Id$
 * <p>
 * Class to retrieve the Wicket component with the specified markup identifiant
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class MarkupIdVisitor implements IVisitor<Component> {
	// Properties
	private String id;
	private Component foundComponent;

	/**Constructor
	 * @param id Markup identifiant
	 */
	public MarkupIdVisitor(String id) {
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.Component.IVisitor#component(org.apache.wicket.Component)
	 */
	public Object component(Component component) {
		if (component.getMarkupId().equals(this.id)) {
			this.foundComponent = component;
			return IVisitor.STOP_TRAVERSAL;
		}
		return IVisitor.CONTINUE_TRAVERSAL;
	}

	/**
	 * @return the markup identifiant
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the Wicket component that have been found
	 */
	public Component getFoundComponent() {
		return foundComponent;
	}
}