package org.odlabs.wiquery.tester.matchers;

import org.apache.wicket.Component;

public class ComponentTypeMatcher implements ComponentMatcher {
	private Class<? extends Component> componentType;

	public ComponentTypeMatcher(Class<? extends Component> componentType) {
		this.componentType = componentType;
	}

	public boolean matches(Component component) {
		return componentType.isAssignableFrom(component.getClass());
	}

	@Override
	public String toString() {
		return "component::=" + componentType.getSimpleName();
	}
}
