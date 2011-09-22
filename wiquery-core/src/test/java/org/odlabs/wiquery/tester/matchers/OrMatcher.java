package org.odlabs.wiquery.tester.matchers;

import org.apache.wicket.Component;

public class OrMatcher implements ComponentMatcher
{
	private ComponentMatcher left;

	private ComponentMatcher right;

	public OrMatcher(ComponentMatcher left, ComponentMatcher right)
	{
		this.left = left;
		this.right = right;
	}

	public boolean matches(Component component)
	{
		return left.matches(component) || right.matches(component);
	}

	@Override
	public String toString()
	{
		return "(" + left + " OR " + right + ")";
	}
}
