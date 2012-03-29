package org.odlabs.wiquery.core.util;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

/**
 * $Id: MarkupIdVisitor.java 1714M 2012-01-17 08:57:27Z (local) $
 * <p>
 * Class to retrieve the Wicket component with the specified markup identifier
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class MarkupIdVisitor implements IVisitor<Component, Void>
{
	// Properties
	private String id;

	private Component foundComponent;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Markup identifier
	 */
	public MarkupIdVisitor(String id)
	{
		this.id = id;
	}

	@Override
	public void component(Component component, IVisit<Void> visit)
	{
		if (component.getMarkupId().equals(this.id))
		{
			this.foundComponent = component;
			visit.stop();
		}
		if (component instanceof MarkupContainer)
		{
			((MarkupContainer) component).visitChildren(Component.class, this);
		}
	}

	/**
	 * @return the markup identifier
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @return the Wicket component that have been found
	 */
	public Component getFoundComponent()
	{
		return foundComponent;
	}
}