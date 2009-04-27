package org.odlabs.wiquery.ui.droppable;

import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.draggable.DraggableJavaScriptResourceLocator;

public class DroppableBehavior extends WiQueryAbstractBehavior {

	private static final long serialVersionUID = 1L;

	private Options options = new Options();
	
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(CoreUIJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(new DroppableJavaScriptResourceLocator());
	}

	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain("droppable", this.options.getJavaScriptOptions());
	}
	
	public void setAccept(String accept) {
		this.options.putLiteral("accept", accept);
	}
	
	public void setOnDrop(JsScope onDrop) {
		this.options.put("drop", onDrop);
	}

}
