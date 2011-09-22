package org.odlabs.wiquery.tester.matchers;

import org.apache.wicket.Component;

public interface ComponentMatcher {
	public boolean matches(Component component);
}
