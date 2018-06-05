package org.wicketstuff.wiquery.button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.wiquery.ui.button.ButtonBehavior;
import org.wicketstuff.wiquery.ui.button.ButtonCheckSet;
import org.wicketstuff.wiquery.ui.button.ButtonElement;
import org.wicketstuff.wiquery.ui.button.ButtonRadioSet;
import org.wicketstuff.wiquery.ui.themes.UiIcon;

/**
 * @author Stephane Gleizes
 */
public class ButtonPanel extends Panel
{

	private static final long serialVersionUID = 1L;

	private static final List<Model<String>> RADIO_ELEMENTS = Arrays.asList(Model.of("Element1"),
		Model.of("Element2"), Model.of("Element3"));

	public ButtonPanel(String id)
	{
		super(id);

		final Form<Void> form = new Form<>("form");
		add(form);

		// Simple button
		Button simpleButton = new Button("simpleButton");
		simpleButton.add(new ButtonBehavior().setIcon(UiIcon.HOME).setLabel("Simple button"));
		form.add(simpleButton);

		// Button without text
		Button buttonWithoutText = new Button("buttonWithoutText");
		buttonWithoutText.add(new ButtonBehavior().setIcon(UiIcon.HOME).setShowLabel(false));
		form.add(buttonWithoutText);

		// Radio button set
		List<ButtonElement<String>> elements = new ArrayList<>();
		for (IModel<String> model : RADIO_ELEMENTS)
		{
			elements.add(new ButtonElement<>(model, model));
		}
		ButtonRadioSet<String> radioSet = new ButtonRadioSet<>("radioSet", elements);
		form.add(radioSet);

		// Check button set
		ButtonCheckSet<String> checkSet = new ButtonCheckSet<>("checkSet", elements);
		form.add(checkSet);
	}
}
