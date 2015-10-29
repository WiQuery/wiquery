package org.wicketstuff.wiquery.tester.matchers;

import org.apache.wicket.Component;

public class AndMatcher implements ComponentMatcher
{
	private ComponentMatcher left;

	private ComponentMatcher right;

	public AndMatcher(ComponentMatcher left, ComponentMatcher right)
	{
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean matches(Component component)
	{
		return left.matches(component) && right.matches(component);
	}

	@Override
	public String toString()
	{
		return "( " + left + " AND " + right + ")";
	}
}
