package org.odlabs.wiquery.ui.progressbar;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.options.UiOptionsRenderer;

@WiQueryUIPlugin
public class ProgressBar extends WebMarkupContainer implements IWiQueryPlugin {

	private static final long serialVersionUID = 8268721447610956664L;

	private Options options;
	
	public ProgressBar(String id) {
		super(id);
		this.options = new Options();
		this.options.setRenderer(new UiOptionsRenderer("progressbar", this));
	}

	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(
				ProgressBar.class, "ui.progressbar.js");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.objetdirect.wickext.core.commons.JavaScriptCallable#statement()
	 */
	public JsStatement statement() {
		JsStatement componentStatement = new JsQuery(this).$().chain("progressbar");
		JsStatement wholeStatement = new JsStatement();
		wholeStatement.append(componentStatement.render());
		wholeStatement.append(options.getJavaScriptOptions());
		return wholeStatement;
	}
	
	public JsStatement update() {
		JsStatement wholeStatement = new JsStatement();
		wholeStatement.append(options.getJavaScriptOptions());
		return wholeStatement;
	}
	
	public void setValue(int value) {
		this.options.put("value", value);
	}
	
	public int getValue() {
		return this.options.getInt("value");
	}
	
}
