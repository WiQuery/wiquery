package org.odlabs.wiquery.tester.matchers;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IWrapModel;
import org.apache.wicket.model.PropertyModel;

public class PropertyExpressionMatcher implements ComponentMatcher
{
	private final String expression;

	public PropertyExpressionMatcher(String expression)
	{
		this.expression = expression;
	}

	public boolean matches(Component component)
	{
		IModel< ? > checkModel = component.getDefaultModel();
		while (checkModel instanceof IWrapModel< ? >)
			checkModel = ((IWrapModel< ? >) checkModel).getWrappedModel();
		if (checkModel instanceof PropertyModel< ? >)
		{
			PropertyModel< ? > model = (PropertyModel< ? >) checkModel;
			return model.getPropertyExpression().equals(expression);
		}
		return false;
	}

	@Override
	public String toString()
	{
		return ":'" + expression + "'";
	}
}
