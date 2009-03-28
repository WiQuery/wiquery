package org.odlabs.wiquery.ui.droppable;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;


public abstract class DroppableAjaxBehavior extends AbstractDefaultAjaxBehavior {

	private static final long serialVersionUID = 1L;

	@Override
	protected void respond(AjaxRequestTarget target) {
		onDrop(target);
	}
	
	public abstract void onDrop(AjaxRequestTarget target);

}
