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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.CollectionModel;
import org.wicketstuff.wiquery.core.javascript.JsQuery;
import org.wicketstuff.wiquery.core.javascript.JsStatement;
import org.wicketstuff.wiquery.ui.JQueryUIJavaScriptResourceReference;

/**
 * $Id$
 * <p>
 * Creates a set of button checkbox UI component from this {@link Panel}'s HTML markup.
 * </p>
 *
 * @param <T>
 *            The model object type
 *
 * @author Julien Roche
 * @since 1.1
 */
public class ButtonCheckSet<T extends Serializable> extends Panel
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -7837076246968244110L;

	// Properties
	private final CheckGroup<T> checkGroup;

	/**
	 * Constructor
	 *
	 * @param id
	 *            Wicket identifier
	 * @param checks
	 *            List of checks
	 */
	public ButtonCheckSet(String id, IModel<? extends List<ButtonElement<T>>> checks)
	{
		this(id, checks, new CollectionModel<>(new ArrayList<T>()));
	}

	/**
	 * Constructor
	 *
	 * @param id
	 *            Wicket identifier
	 * @param checks
	 *            List of checks
	 * @param model
	 *            Model of the default object
	 */
	public ButtonCheckSet(String id, IModel<? extends List<ButtonElement<T>>> checks,
		IModel<? extends Collection<T>> model)
	{
		super(id);

		checkGroup = new CheckGroup<>("buttonCheckSetGroup", model);
		checkGroup.setOutputMarkupId(true);
		checkGroup.setRenderBodyOnly(false);
		add(checkGroup);

		ListView<? extends ButtonElement<T>> view = new ListView<ButtonElement<T>>(
			"buttonCheckSetView", checks)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<ButtonElement<T>> item)
			{
				ButtonElement<T> buttonElement = item.getModelObject();

				Check<T> check = newCheck("buttonCheck", buttonElement.getModel(), checkGroup);
				check.setLabel(buttonElement.getLabel());
				check.setOutputMarkupId(true);

				SimpleFormComponentLabel label = new SimpleFormComponentLabel("buttonCheckLabel",
					check);

				item.add(check);
				item.add(label);
			}
		};
		checkGroup.add(view);
	}

	/**
	 * Constructor
	 *
	 * @param id
	 *            Wicket identifier
	 * @param checks
	 *            List of checks
	 */
	public ButtonCheckSet(String id, List<ButtonElement<T>> checks)
	{
		this(id, Model.ofList(checks));
	}

	/**
	 * Constructor
	 *
	 * @param id
	 *            Wicket identifier
	 * @param checks
	 *            List of checks
	 * @param model
	 *            Model of the default object
	 */
	public ButtonCheckSet(String id, List<ButtonElement<T>> checks,
		IModel<? extends Collection<T>> model)
	{
		this(id, Model.ofList(checks), model);
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		response
			.render(JavaScriptHeaderItem.forReference(JQueryUIJavaScriptResourceReference.get()));
		response.render(OnDomReadyHeaderItem.forScript(statement().render()));
	}

	/**
	 * @return the check group of this component
	 */
	public CheckGroup<T> getCheckGroup()
	{
		return checkGroup;
	}

	/**
	 * Method creating a new {@link Check}
	 *
	 * @param wicketId
	 *            Wicket identifier
	 * @param model
	 *            Model to use
	 * @param group
	 *            Group of the {@link Check}
	 * @return a {@link Check}
	 */
	protected Check<T> newCheck(String wicketId, IModel<T> model, CheckGroup<T> group)
	{
		Check<T> check = new Check<>(wicketId, model, group);
		return check;
	}

	public JsStatement statement()
	{
		return new JsQuery(checkGroup).$().chain("buttonset");
	}
}
