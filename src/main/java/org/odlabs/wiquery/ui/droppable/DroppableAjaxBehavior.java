package org.odlabs.wiquery.ui.droppable;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsScopeContext;


public abstract class DroppableAjaxBehavior extends AbstractDefaultAjaxBehavior {

	private static final long serialVersionUID = 1L;

	private DroppableBehavior droppableBehavior = new DroppableBehavior();
	
	@Override
	protected void onBind() {
		getComponent().add(droppableBehavior);
		droppableBehavior.setOnDrop(new JsScope() {
		
			@Override
			protected void execute(JsScopeContext scopeContext) {
				scopeContext.append(getCallbackScript());
			}
		
		}); 
	}

	public void setAccept(String accept) {
		droppableBehavior.setAccept(accept);
	}
	
	@Override
	protected void respond(AjaxRequestTarget target) {
		onDrop(target);
	}
	
	public abstract void onDrop(AjaxRequestTarget target);

}
