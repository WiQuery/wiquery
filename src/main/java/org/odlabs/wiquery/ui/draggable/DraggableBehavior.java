package org.odlabs.wiquery.ui.draggable;

import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;

public class DraggableBehavior extends WiQueryAbstractBehavior {

	private static final long serialVersionUID = 1L;

	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(CoreUIJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(new DraggableJavaScriptResourceLocator());
	}

	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain("draggable");
	}

}
