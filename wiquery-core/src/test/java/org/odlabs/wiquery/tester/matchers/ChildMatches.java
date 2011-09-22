package org.odlabs.wiquery.tester.matchers;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

/**
 * Matcher die controleert of een kind van het huidige component voldoet aan de opgegeven
 * matcher.
 */
public class ChildMatches implements ComponentMatcher
{
	private ComponentMatcher childMatcher;

	public ChildMatches(ComponentMatcher childMatcher)
	{
		assert childMatcher != null;
		this.childMatcher = childMatcher;
	}

	public boolean matches(Component component)
	{
		// alleen markupcontainers hebben kinderen
		if (component instanceof MarkupContainer)
		{
			MarkupContainer container = (MarkupContainer) component;
			ChildMatchingVisitor visitor = new ChildMatchingVisitor();
			container.visitChildren(visitor);
			return visitor.matches;
		}
		return false;
	}

	class ChildMatchingVisitor implements IVisitor<Component, Void>
	{
		boolean matches = false;

		public void component(Component child, IVisit<Void> visit)
		{
			if (childMatcher.matches(child))
				visit.stop();
		}
	}

	@Override
	public String toString()
	{
		return "child matches [" + childMatcher + "]";
	}
}
