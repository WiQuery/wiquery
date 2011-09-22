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
package org.odlabs.wiquery.ui.autocomplete;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.util.string.Strings;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * $Id$
 * <p>
 * Creates an autocomplete UI component which will bind on a Wicket model. The list of
 * possibles values have filled with Ajax
 * </p>
 * 
 * @author Julien Roche
 * @param <T>
 *            The model object type
 * @since 1.1
 */
public abstract class AutocompleteAjaxComponent<T> extends AbstractAutocompleteComponent<T>
{
	private String term;

	/**
	 * Ajax behavior to create the list of possibles values
	 * 
	 * @author Julien Roche
	 * 
	 */
	private class InnerAutocompleteAjaxBehavior extends AbstractAjaxBehavior
	{
		// Constants
		/** Constant of serialization */
		private static final long serialVersionUID = -5411632961744455568L;

		public void onRequest()
		{
			term =
				this.getComponent().getRequest().getQueryParameters().getParameterValue("term")
					.toString();

			if (!Strings.isEmpty(term))
			{
				StringWriter sw = new StringWriter();
				try
				{
					JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);

					AutocompleteJson value = null;
					Integer index = 0;
					List<Object> json = new ArrayList<Object>();

					for (T obj : getValues(term))
					{
						index++;
						value = newAutocompleteJson(index, obj);
						json.add(value);
					}

					new ObjectMapper().writeValue(gen, json);

				}
				catch (IOException e)
				{
					throw new WicketRuntimeException(e);
				}

				RequestCycle.get().scheduleRequestHandlerAfterCurrent(
					new TextRequestHandler("application/json", "utf-8", sw.toString()));
			}
		}
	}

	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -3377109382248062940L;

	// Wicket components
	private final InnerAutocompleteAjaxBehavior innerAutcompleteAjaxBehavior;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Wicket identifiant
	 * @param model
	 *            Model of the default value
	 */
	public AutocompleteAjaxComponent(String id, final IModel<T> model)
	{
		super(id, model);

		innerAutcompleteAjaxBehavior = new InnerAutocompleteAjaxBehavior();
		add(innerAutcompleteAjaxBehavior);
	}

	public AutocompleteAjaxComponent(String id, final IModel<T> model,
			IChoiceRenderer< ? super T> choiceRenderer)
	{
		super(id, model, choiceRenderer);
		innerAutcompleteAjaxBehavior = new InnerAutocompleteAjaxBehavior();
		add(innerAutcompleteAjaxBehavior);
	}

	/**
	 * Method called when the search is launched
	 * 
	 * @param term
	 *            Value typed
	 * @return possible values
	 */
	public abstract List<T> getValues(String term);

	@Override
	protected List< ? extends T> getChoices()
	{
		return getValues(term);
	}

	@Override
	protected void onBeforeRenderAutocomplete(Autocomplete< ? > autocomplete)
	{
		T defaultValue = AutocompleteAjaxComponent.this.getModelObject();

		if (defaultValue != null)
		{
			AutocompleteJson value = null;
			value = newAutocompleteJson(0, defaultValue);
			autocomplete.setDefaultModelObject(value.getLabel());
			getAutocompleteHidden().setModelObject(value.getValueId());
		}

		autocomplete.getOptions().putLiteral("source",
			innerAutcompleteAjaxBehavior.getCallbackUrl().toString());
	}
}
