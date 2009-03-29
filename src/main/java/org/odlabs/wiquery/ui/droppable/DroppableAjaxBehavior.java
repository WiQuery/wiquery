package org.odlabs.wiquery.ui.droppable;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.javascript.JsScope;


public abstract class DroppableAjaxBehavior extends AbstractDefaultAjaxBehavior {

	private static final long serialVersionUID = 1L;

	private DroppableBehavior droppableBehavior;
	
	@Override
	protected void onBind() {
		getComponent().add(droppableBehavior = new DroppableBehavior());
		droppableBehavior.setOnDrop(JsScope.quickScope(this.getCallbackScript()));
	}

	@Override
	protected void respond(AjaxRequestTarget target) {
		onDrop(target);
	}
	
	public abstract void onDrop(AjaxRequestTarget target);

}
