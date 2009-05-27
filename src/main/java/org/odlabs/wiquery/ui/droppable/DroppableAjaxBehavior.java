package org.odlabs.wiquery.ui.droppable;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Component.IVisitor;
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
		droppableBehavior.setOnDrop(new JsScope("event", "ui") {
		
			@Override
			protected void execute(JsScopeContext scopeContext) {
				scopeContext.append("wicketAjaxGet('" + getCallbackUrl(true) +
						"&droppedId='+ui.draggable[0].id, null,null, function() {return true;})");
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
	
	public final void onDrop(AjaxRequestTarget target) {
		// getting dropped element id to retreive the wicket component
		String input = this.getComponent().getRequest().getParameter("droppedId");
		MarkupIdVisitor visitor = new MarkupIdVisitor(input);
		this.getComponent().getPage().visitChildren(visitor);
		onDrop(visitor.getFoundComponent(), target);
	}
	
	public abstract void onDrop(Component droppedComponent, AjaxRequestTarget ajaxRequestTarget);

	// TODO refactor -> create separated file
	private static class MarkupIdVisitor implements IVisitor<Component> {
		private final String id;
		private Component found;

		public MarkupIdVisitor(String id) {
			this.id = id;
		}

		public Object component(Component component) {
			if (component.getMarkupId().equals(id)) {
				this.found = component;
				return IVisitor.STOP_TRAVERSAL;
			}
			if (component instanceof MarkupContainer) {
				return ((MarkupContainer)component).visitChildren(this);
			}
			return IVisitor.CONTINUE_TRAVERSAL;
		}
		
		public Component getFoundComponent() {
			return found;
		}
	}
}
