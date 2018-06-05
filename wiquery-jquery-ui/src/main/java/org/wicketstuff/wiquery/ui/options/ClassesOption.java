package org.wicketstuff.wiquery.ui.options;

import org.apache.wicket.model.IModel;
import org.wicketstuff.wiquery.core.options.IComplexOption;
import org.wicketstuff.wiquery.core.options.Options;

/**
 * Implementation of the classes option, used by most widgets
 * 
 * @author papegaaij
 */
public class ClassesOption implements IComplexOption
{
	private static final long serialVersionUID = 1L;

	private final Options options = new Options();

	public String getClasses(String structuralClass)
	{
		return options.getLiteral(structuralClass);
	}

	public ClassesOption setClasses(String structuralClass, String classes)
	{
		options.putLiteral(structuralClass, classes);
		return this;
	}

	public ClassesOption setClasses(String structuralClass, IModel<String> classes)
	{
		options.putLiteral(structuralClass, classes);
		return this;
	}

	@Override
	public CharSequence getJavascriptOption()
	{
		return options.getJavaScriptOptions();
	}
}
