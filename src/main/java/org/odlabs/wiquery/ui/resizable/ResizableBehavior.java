package org.odlabs.wiquery.ui.resizable;

import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.droppable.DroppableJavaScriptResourceLocator;

public class ResizableBehavior extends WiQueryAbstractBehavior {

	private Options options = new Options();
	
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(CoreUIJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(new ResizableJavaScriptResourceReference());
	}
	
	@Override
	public JsStatement statement() {
		return new JsQuery(this.getComponent()).$().chain("resizable", this.options.getJavaScriptOptions());
	}
	
	public void setAlsoResize(String cssSelector) {
		this.options.putLiteral("alsoResize", cssSelector);
	}
	
	public String getAlsoResize() {
		return this.options.getLiteral("alsoResize");
	}
	
	public void setAnimate(boolean animate) {
		this.options.put("animate", animate);
	}
	
	public boolean isAnimate() {
		return this.options.getBoolean("alsoResize");
	}

	public void setAnimateEasing(String easing) {
		options.put("animateEasing", easing);
	}

	public String getAnimateEasing() {
		return options.get("animateEasing");
	}
	
}
