package org.odlabs.wiquery.accordion;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.accordion.Accordion;
import org.odlabs.wiquery.ui.accordion.AccordionAnimateOption;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

/**
 * @author Stephane Gleizes
 */
public class AccordionPanel extends Panel {

	private static final long serialVersionUID = 261756719214828133L;

	private Label indexLabel;
	
	public AccordionPanel(String id) {
		super(id);
		Accordion accordion = new Accordion("accordion");
		accordion.setCollapsible(true);
		accordion.setActive(false);
		accordion.setAnimate(new AccordionAnimateOption(200));
		add(accordion);
		
		indexLabel = new Label("indexLabel");
		indexLabel.setOutputMarkupId(true);
		add(indexLabel);
		
		JsStatement getActiveStatement = new JsQuery(accordion).$().chain("accordion", "'option'", "'active'");
		CharSequence labelValue = "'Active is: ' + " + getActiveStatement.render(false);
		accordion.setActivateEvent(JsScopeUiEvent.quickScope(new JsQuery(indexLabel).$().chain("text", labelValue)));
	}
}
