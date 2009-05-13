package org.odlabs.wiquery.ui.slider;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

@WiQueryUIPlugin
public class Slider extends WebMarkupContainer implements IWiQueryPlugin {

	public static enum Orientation { VERTICAL, HORIZONTAL};
	
	private Options options;
	
	public Slider(String id, Number min, Number max) {
		super(id);
	}

	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(Slider.class, "ui.slider.js");
	}

	public JsStatement statement() {
		return new JsQuery(this).$().chain("slider", options.getJavaScriptOptions());
	}

}
