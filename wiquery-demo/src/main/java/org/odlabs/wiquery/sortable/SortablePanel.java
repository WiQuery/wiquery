package org.odlabs.wiquery.sortable;

import java.util.List;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.sortable.model.SimpleSortableElement;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.sortable.SortableBehavior;
import org.odlabs.wiquery.ui.sortable.SortableBehavior.ToleranceEnum;
import org.odlabs.wiquery.ui.sortable.SortableRevert;

/**
 * @author Stephane Gleizes
 */
public class SortablePanel extends Panel {

	private static final long serialVersionUID = 1L;

	private ListView<SimpleSortableElement> sortableListView;
	
	private SortableBehavior sortableBehavior;
	
	private boolean sortMode;

	public SortablePanel(String id, IModel<List<SimpleSortableElement>> listModel) {
		super(id);
		setOutputMarkupId(true);
		
		Form<List<SimpleSortableElement>> form = new Form<List<SimpleSortableElement>>("form", listModel) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				sortableListView.getModel().detach();
				sortableListView.removeAll();
				
				SortablePanel.this.setSortMode(false);
				throw new RestartResponseException(getPage());
			}
		};
		add(form);
		
		// List view
		sortableListView = new ListView<SimpleSortableElement>("elements", listModel) {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<SimpleSortableElement> item) {
				item.setOutputMarkupId(true);
				IModel<Integer> positionModel = new PropertyModel<Integer>(item.getModel(), "position");
				
				item.add(new RequiredTextField<Integer>("positionField", positionModel));
				
				item.add(new Label("positionLabel", positionModel));
				item.add(new Label("element", item.getModelObject().getName()));
			}
		};
		
		WebMarkupContainer elementsContainer = new WebMarkupContainer("elementsContainer");
		sortableBehavior = new SortableBehavior();
		sortableBehavior
			.setUpdateEvent(new JsScopeUiEvent() {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void execute(JsScopeContext scopeContext) {
					scopeContext.append("onSortableUpdate($(this));");
				}
			})
			.setPlaceholder("element-placeholder")
			.setRevert(new SortableRevert(true))
			.setForcePlaceholderSize(true)
			.setTolerance(ToleranceEnum.POINTER)
			.setDisabled(!isSortMode());
		elementsContainer.add(sortableListView);
		elementsContainer.add(sortableBehavior);
		elementsContainer.add(new AttributeAppender("class", new LoadableDetachableModel<String>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected String load() {
				if (!SortablePanel.this.isSortMode()) {
					return "ui-sortable-disabled";
				}
				return null;
			}
		}, " "));
		form.add(elementsContainer);
		
		// Actions
		WebMarkupContainer actions = new WebMarkupContainer("actions");
		form.add(actions);
		
		AjaxLink<?> editOrder = new AjaxLink<Void>("editOrder") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				SortablePanel.this.sortableBehavior.enable(target);
				SortablePanel.this.setSortMode(true);
				target.add(SortablePanel.this);
			}
			
			@Override
			protected void onConfigure() {
				super.onConfigure();
				setVisible(!SortablePanel.this.isSortMode());
			}
		};
		actions.add(editOrder);
		
		Button saveOrder = new Button("saveOrder") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onConfigure() {
				super.onConfigure();
				setVisible(SortablePanel.this.isSortMode());
			}
		};
		actions.add(saveOrder);
		
		AjaxLink<?> cancelOrder = new AjaxLink<Void>("cancelOrder") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				SortablePanel.this.setSortMode(false);
				SortablePanel.this.sortableBehavior.disable(target);
				target.add(SortablePanel.this);
			}
			
			@Override
			protected void onConfigure() {
				super.onConfigure();
				setVisible(SortablePanel.this.isSortMode());
			}
		};
		actions.add(cancelOrder);
	}
	
	public boolean isSortMode() {
		return sortMode;
	}
	
	public void setSortMode(boolean sortMode) {
		this.sortMode = sortMode;
	}
}
