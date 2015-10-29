package org.wicketstuff.wiquery.tester.matchers;

import org.apache.wicket.Component;

public class ComponentIdMatcher implements ComponentMatcher
{
	private final String id;

	public ComponentIdMatcher(String id)
	{
		this.id = id;
	}

	@Override
	public boolean matches(Component component)
	{
		return component.getId().equals(id);
	}

	@Override
	public String toString()
	{
		return "id == '" + id + "'";
	}
}
