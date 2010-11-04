package org.odlabs.wiquery.tester.matchers;

import java.util.Arrays;

import org.apache.wicket.Component;

public class ComponentMatchers {
	public static AndMatcher and(ComponentMatcher left, ComponentMatcher right) {
		return new AndMatcher(left, right);
	}

	public static AndMatcher and(ComponentMatcher first,
			ComponentMatcher second, ComponentMatcher... others) {
		if (others == null || others.length == 0) {
			return and(first, second);
		}
		ComponentMatcher firstOfOthers = others[0];
		ComponentMatcher[] remainderOfOthers = Arrays.copyOfRange(others, 1,
				others.length - 1, ComponentMatcher[].class);

		return and(first, and(second, firstOfOthers, remainderOfOthers));
	}

	public static OrMatcher or(ComponentMatcher left, ComponentMatcher right) {
		return new OrMatcher(left, right);
	}

	public static ComponentIdMatcher withId(String id) {
		return new ComponentIdMatcher(id);
	}

	public static PropertyExpressionMatcher forProperty(String expression) {
		return new PropertyExpressionMatcher(expression);
	}

	public static ComponentTypeMatcher ofType(Class<? extends Component> type) {
		return new ComponentTypeMatcher(type);
	}

	public static ModelMatcher forModel(Object modelObject) {
		return new ModelMatcher(modelObject);
	}

	public static ParentMatches parentMatches(ComponentMatcher matcher) {
		return new ParentMatches(matcher);
	}

	public static ChildMatches childMatches(ComponentMatcher matcher) {
		return new ChildMatches(matcher);
	}
}
