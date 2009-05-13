package org.odlabs.wiquery.ui.options;

import org.apache.wicket.Component;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.IOptionsRenderer;

public class UiOptionsRenderer implements IOptionsRenderer {

	private String statement;
	
	private Component component;
	
	public UiOptionsRenderer(String statement, Component component) {
		super();
		this.statement = statement;
		this.component = component;
	}

	public void renderAfter(StringBuilder stringBuilder) {
	}

	public void renderBefore(StringBuilder stringBuilder) {
	}

	public CharSequence renderOption(String name, Object value, boolean isLast) {
		JsStatement jsStatement 
			= new JsQuery(this.component).
				$().
				chain(this.statement, "'option'", "'" + name + "'", value.toString());
		return jsStatement.render();
	}
	
}
