package org.odlabs.wiquery.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.ui.dialog.Dialog;
import org.odlabs.wiquery.ui.dialog.DialogAnimateOption;
import org.odlabs.wiquery.ui.dialog.DialogButton;
import org.odlabs.wiquery.ui.effects.FoldEffectJavaScriptResourceReference;
import org.odlabs.wiquery.ui.position.PositionAlignmentOptions;
import org.odlabs.wiquery.ui.position.PositionOptions;
import org.odlabs.wiquery.ui.position.PositionRelation;
import org.odlabs.wiquery.ui.themes.UiIcon;

/**
 * @author Stephane Gleizes
 */
public class DialogPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	public DialogPanel(String id) {
		super(id);
		final WebMarkupContainer dialogContainer = new WebMarkupContainer("dialogContainer");
		dialogContainer.setOutputMarkupId(true);
		add(dialogContainer);
		
		final Dialog dialog = new Dialog("dialog");
		dialog.setResizable(true);
		dialog.setTitle("Dialog title");
		dialog.setPosition(new PositionOptions()
				.setAt(new PositionAlignmentOptions(PositionRelation.TOP))
				.setMy(new PositionAlignmentOptions(PositionRelation.TOP))
				.setOf("#" + dialogContainer.getMarkupId())
		);
		dialog.setShow(new DialogAnimateOption("fold"));
		dialog.setHide(new DialogAnimateOption(100));
		dialog.setButtons(new DialogButton("Close", JsScope.quickScope(dialog.close()))
				.setIcons(UiIcon.CLOSE, null)
		);
		dialogContainer.add(dialog);
		
		final AjaxLink<Void> openDialog = new AjaxLink<Void>("openDialog") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				target.appendJavaScript(dialog.open().render().toString());
			}
		};
		dialogContainer.add(openDialog);
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(FoldEffectJavaScriptResourceReference.get()));
	}
}
