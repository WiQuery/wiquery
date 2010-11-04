package org.odlabs.wiquery.tester.matchers;

import org.apache.wicket.Component;

public class ParentMatches implements ComponentMatcher {
	private ComponentMatcher parentMatcher;

	public ParentMatches(ComponentMatcher parentMatcher) {
		assert parentMatcher != null;
		this.parentMatcher = parentMatcher;
	}

	public boolean matches(Component component) {
		if (component == null)
			return false;

		Component parent = component.getParent();

		if (parent != null && parentMatcher.matches(parent))
			return true;

		return parent != null && matches(parent);
	}

	@Override
	public String toString() {
		return "parent matches [" + parentMatcher + "]";
	}
}
