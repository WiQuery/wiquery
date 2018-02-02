/*
 * Copyright (c) 2009 WiQuery team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.wicketstuff.wiquery.ui.button;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.wiquery.core.javascript.JsQuery;
import org.wicketstuff.wiquery.core.javascript.JsStatement;
import org.wicketstuff.wiquery.ui.widget.WidgetJavaScriptResourceReference;

/**
 * $Id$
 * <p>
 * Creates a set of button radio UI component from this {@link Panel}'s HTML markup.
 * </p>
 * 
 * @param <T>
 *            The model object type
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class ButtonRadioSet<T extends Serializable> extends Panel
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -7837076246968244110L;

	// Properties
	private final RadioGroup<T> radioGroup;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Wicket identifier
	 * @param radios
	 *            List of radios
	 */
	public ButtonRadioSet(String id, IModel<? extends List<ButtonElement<T>>> radios)
	{
		this(id, radios, new Model<T>());
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Wicket identifier
	 * @param radios
	 *            List of radios
	 */
	public ButtonRadioSet(String id, IModel<? extends List<ButtonElement<T>>> radios,
			IModel<T> model)
	{
		super(id);

		radioGroup = new RadioGroup<T>("buttonRadioSetGroup", model);
		radioGroup.setOutputMarkupId(true);
		radioGroup.setRenderBodyOnly(false);
		add(radioGroup);

		ListView< ? extends ButtonElement<T>> view =
			new ListView<ButtonElement<T>>("buttonRadioSetView", radios)
			{
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<ButtonElement<T>> item)
				{
					ButtonElement<T> buttonElement = item.getModelObject();

					Radio<T> radio = newRadio("buttonRadio", buttonElement.getModel(), radioGroup);
					radio.setLabel(buttonElement.getLabel());
					radio.setOutputMarkupId(true);

					SimpleFormComponentLabel label =
						new SimpleFormComponentLabel("buttonRadioLabel", radio);

					item.add(radio);
					item.add(label);
				}
			};
		radioGroup.add(view);
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Wicket identifier
	 * @param radios
	 *            List of radios
	 */
	public ButtonRadioSet(String id, List<ButtonElement<T>> radios)
	{
		this(id, Model.ofList(radios));
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Wicket identifier
	 * @param radios
	 *            List of radios
	 * @param model
	 *            Model of the default object
	 */
	public ButtonRadioSet(String id, List<ButtonElement<T>> radios, IModel<T> model)
	{
		this(id, Model.ofList(radios), model);
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		response.render(JavaScriptHeaderItem.forReference(WidgetJavaScriptResourceReference.get()));
		response.render(JavaScriptHeaderItem.forReference(ButtonJavaScriptResourceReference.get()));
		response.render(OnDomReadyHeaderItem.forScript(statement().render()));
	}

	/**
	 * @return the radio group of this component
	 */
	public RadioGroup<T> getRadioGroup()
	{
		return radioGroup;
	}

	/**
	 * Method creating a new {@link Radio}
	 * 
	 * @param wicketId
	 *            Wicket identifier
	 * @param model
	 *            Model to use
	 * @param group
	 *            Group of the {@link Radio}
	 * @return a {@link Radio}
	 */
	protected Radio<T> newRadio(String wicketId, IModel<T> model, RadioGroup<T> group)
	{
		Radio<T> radio = new Radio<T>(wicketId, model, group);
		return radio;
	}

	public JsStatement statement()
	{
		return new JsQuery(radioGroup).$().chain("buttonset");
	}
}
