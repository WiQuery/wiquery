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
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * $Id: AutocompleteComponent.java 463 2010-10-19 12:14:45Z richardjohnwilkinson@gmail.com
 * $
 * <p>
 * Creates an autocomplete UI component which will bind on a Wicket model
 * </p>
 * 
 * @author Julien Roche
 * @param <T>
 *            The model object type
 * @since 1.1
 */
public abstract class AutocompleteComponent<T> extends AbstractAutocompleteComponent<T>
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -3377109382248062940L;

	// Properties
	private final IModel< ? extends List< ? extends T>> list;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Wicket identifiant
	 * @param model
	 *            Model of the default value
	 * @param list
	 *            List of possibles values
	 */
	public AutocompleteComponent(String id, final IModel<T> model,
			final IModel< ? extends List< ? extends T>> list)
	{
		super(id, model);
		this.list = list;
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Wicket identifiant
	 * @param model
	 *            Model of the default value
	 * @param list
	 *            List of possibles values
	 */
	public AutocompleteComponent(String id, final IModel<T> model,
			final IModel< ? extends List< ? extends T>> list,
			IChoiceRenderer< ? super T> choiceRenderer)
	{
		super(id, model, choiceRenderer);
		this.list = list;
	}

	@Override
	protected void onBeforeRenderAutocomplete(Autocomplete< ? > autocomplete)
	{
		StringWriter sw = new StringWriter();

		try
		{
			JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);

			List<Object> json = new ArrayList<Object>();
			T defaultValue = AutocompleteComponent.this.getModelObject();
			AutocompleteJson value = null;
			Integer index = 0;

			for (T obj : AutocompleteComponent.this.list.getObject())
			{
				index++;
				value = newAutocompleteJson(index, obj);
				json.add(value);

				if (obj.equals(defaultValue))
				{
					autocomplete.setDefaultModelObject(value.getLabel());
					getAutocompleteHidden().setModelObject(value.getValueId());
				}
			}

			new ObjectMapper().writeValue(gen, json);

		}
		catch (IOException e)
		{
			throw new WicketRuntimeException(e);
		}

		autocomplete.getOptions().put("source", sw.toString());
	}

	@Override
	protected List< ? extends T> getChoices()
	{
		return list.getObject();
	}

	@Override
	protected void onDetach()
	{
		super.onDetach();
		if (list != null)
		{
			list.detach();
		}
	}

}
