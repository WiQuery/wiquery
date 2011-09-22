package org.odlabs.wiquery.tester.matchers;

import org.apache.wicket.Component;

public class ModelMatcher implements ComponentMatcher {
	private final Object modelObject;

	public ModelMatcher(Object object) {
		this.modelObject = object;
	}

	public boolean matches(Component component) {
		return modelObject.equals(component.getDefaultModelObject());
	}

	@Override
	public String toString() {
		return "model=='" + modelObject != null ? modelObject.toString()
				: "null" + "'";
	}
}
