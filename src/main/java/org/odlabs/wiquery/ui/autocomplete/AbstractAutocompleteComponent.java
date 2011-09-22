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

import java.util.List;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.Strings;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

/**
 * $Id$
 * <p>
 * Base for the autocomplete component
 * </p>
 * @author Julien Roche
 * @param <T> The model object type
 * @since 1.1
 */
public abstract class AbstractAutocompleteComponent<T> extends FormComponentPanel<T> {

	private boolean autoUpdate = false;
	/**
	 * Inner {@link Autocomplete}
	 * @author Julien Roche
	 *
	 */
	private class InnerAutocomplete<E> extends Autocomplete<E> {
		// Constants
		/**	Constant of serialization */
		private static final long serialVersionUID = -6129719872925080990L;

		/**
		 * Constructor
		 * @param id Wicket identifiant
		 * @param model Model
		 */
		public InnerAutocomplete(String id, IModel<E> model) {
			super(id, model);
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.autocomplete.Autocomplete#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
		 */
		@Override
		public void contribute(WiQueryResourceManager wiQueryResourceManager) {
			super.contribute(wiQueryResourceManager);
			wiQueryResourceManager.addJavaScriptResource(WiQueryAutocompleteJavascriptResourceReference.get());
		}

		/**
		 * {@inheritDoc}
		 * @see org.apache.wicket.markup.html.form.AbstractTextComponent#onBeforeRender()
		 */
		@Override
		protected void onBeforeRender() {
			onBeforeRenderAutocomplete(this);
			super.onBeforeRender();
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.autocomplete.Autocomplete#setCloseEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public Autocomplete<E> setCloseEvent(JsScopeUiEvent close) {
			throw new WicketRuntimeException("You can't define the close event");
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.autocomplete.Autocomplete#setSelectEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public Autocomplete<E> setSelectEvent(JsScopeUiEvent select) {
			throw new WicketRuntimeException("You can't define the select event");
		}
		
		@Override
		public Autocomplete<E> setChangeEvent(JsScopeUiEvent select) {
		     throw new WicketRuntimeException("You can't define the change event");
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.autocomplete.Autocomplete#setSource(org.odlabs.wiquery.ui.autocomplete.AutocompleteSource)
		 */
		@Override
		public Autocomplete<E> setSource(AutocompleteSource source) {
			throw new WicketRuntimeException("You can't define the source");
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.autocomplete.Autocomplete#statement()
		 */
		@Override
		public JsStatement statement() {
			StringBuilder js = new StringBuilder();
			js.append("$.ui.autocomplete.wiquery.changeEvent(event, ui,")
				.append(JsUtils.quotes(autocompleteHidden.getMarkupId()));
			if(isAutoUpdate()){
				js.append(",'").append(updateAjax.getCallbackUrl(true)).append("'");
			}
			js.append(");");
			super.setChangeEvent(JsScopeUiEvent.quickScope(js.toString()));
			super.setSelectEvent(JsScopeUiEvent.quickScope(js.append("$(event.target).blur();").toString()));
			JsStatement jsStatement = super.statement();
			return jsStatement;
		}
	}

	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = -3377109382248062940L;

	/** Constant of wiQuery Autocomplete resource */
	public static final JavascriptResourceReference WIQUERY_AUTOCOMPLETE_JS =
		new JavascriptResourceReference(
				AutocompleteAjaxComponent.class,
		"wiquery-autocomplete.js");

	// Wicket components
	private final Autocomplete<String> autocompleteField;

	private final HiddenField<String> autocompleteHidden;
	
	private static final String NOT_ENTERED = "NOT_ENTERED";

	/** The choiceRenderer used to generate display/id values for the objects. */
	private IChoiceRenderer<? super T> choiceRenderer;

	private AbstractDefaultAjaxBehavior updateAjax;

	/**
	 * Constructor
	 * @param id Wicket identifiant
	 * @param model Model of the default value
	 */
	public AbstractAutocompleteComponent(String id, final IModel<T> model) {
		super(id, model);
		setOutputMarkupPlaceholderTag(true);

		autocompleteHidden = new HiddenField<String>("autocompleteHidden", new Model<String>(NOT_ENTERED){
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				T modelObject = AbstractAutocompleteComponent.this.getModelObject();
				if(modelObject != null){
					return super.getObject();
				}
				else{
					return null;
				}
			}
		});
		autocompleteHidden.setOutputMarkupId(true);
		add(autocompleteHidden);

		autocompleteField = new InnerAutocomplete<String>("autocompleteField", new IModel<String>() {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unchecked")
			public String getObject() {
				T modelObject = AbstractAutocompleteComponent.this.getModelObject();
				if(modelObject != null){
					T objectValue = (T)choiceRenderer.getDisplayValue(modelObject);
					Class<T> objectClass = (Class<T>)(objectValue == null ? null : objectValue.getClass());
	
					String displayValue = "";
					if (objectClass != null && objectClass != String.class)
					{
						final IConverter converter = getConverter(objectClass);
	
						displayValue = converter.convertToString(objectValue, getLocale());
					}
					else if (objectValue != null)
					{
						displayValue = objectValue.toString();
					}
					return displayValue;
				}
				else{
					return null;
				}
			}
			public void setObject(String object) { }
			public void detach() { }
		});
		add(autocompleteField);

		updateAjax = new AbstractDefaultAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void respond(AjaxRequestTarget target) {
				final String hiddenInput = autocompleteHidden.getInput();
				final String fieldInput = autocompleteField.getInput();
				autocompleteHidden.setConvertedInput(hiddenInput);
				autocompleteField.setConvertedInput(fieldInput);
				validate();
				if(isValid()){
					updateModel();
					onUpdate(target);
				}
			}
		};
		add(updateAjax);
	}

	public AbstractAutocompleteComponent(String id, final IModel<T> model, IChoiceRenderer<? super T> renderer){
		this(id, model);
		this.setChoiceRenderer(renderer);
	}

	/**
	 * Called when the value has been updated via ajax
	 * @param target
	 */
	protected void onUpdate(AjaxRequestTarget target){

	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.markup.html.form.FormComponent#convertInput()
	 */
	@Override
	protected final void convertInput() {
		String valueId = autocompleteHidden.getConvertedInput();
		String input = autocompleteField.getConvertedInput();
		final T object = this.getModelObject();
		final IChoiceRenderer<? super T> renderer = getChoiceRenderer();

		if (NOT_ENTERED.equals(valueId))
			valueId = null;
		
		if(valueId == null && Strings.isEmpty(input)){
			setConvertedInput(null);

		} else if(valueId == null){
			setConvertedInput(getValueOnSearchFail(input));
			
		} else if (object == null || input.compareTo((String) renderer.getDisplayValue(object)) != 0) {
			final List<? extends T> choices = getChoices();
			boolean found = false;
			for (int index = 0; index < choices.size(); index++)
			{
				// Get next choice
				final T choice = choices.get(index);
				final String idValue = renderer.getIdValue(choice, index + 1);
				if (idValue.equals(valueId))
				{
					setConvertedInput(choice);
					found = true;
					break;
				}
			}
			if(!found){
				//if it is still not entered, then it means this field was not touched
				//so keep the original value
				if(valueId.equals(NOT_ENTERED)){
					setConvertedInput(getModelObject());
				}
				else{
					setConvertedInput(getValueOnSearchFail(input));
				}
			}
		} else {
			setConvertedInput(object);
		}
	}
	
	protected abstract List<? extends T> getChoices();

	/**
	 * @return the autocomplete field
	 */
	public Autocomplete<String> getAutocompleteField() {
		return autocompleteField;
	}

	/**
	 * @return Hidden field storing the identifiant of the Wicket model
	 */
	public HiddenField<String> getAutocompleteHidden() {
		return autocompleteHidden;
	}

	/**
	 * Method called when the input is not empty and the search failed
	 * @param input Current input
	 * @return a new value
	 */
	public abstract T getValueOnSearchFail(String input);

	/**
	 * Create an {@link AutocompleteJson}
	 * @param id
	 * @param obj
	 * @return a new instance of {@link AutocompleteJson}
	 */
	protected AutocompleteJson newAutocompleteJson(int id, T obj) {

		boolean thisOneSelected = obj.equals(getModelObject());
		Object objectValue = getChoiceRenderer().getDisplayValue(obj);
		Class<?> objectClass = (objectValue == null ? null : objectValue.getClass());

		String displayValue = "";
		if (objectClass != null && objectClass != String.class)
		{
			final IConverter converter = getConverter(objectClass);
			displayValue = converter.convertToString(objectValue, getLocale());
		}
		else if (objectValue != null)
		{
			displayValue = objectValue.toString();
		}
		final String idValue = getChoiceRenderer().getIdValue(obj, id);
		if(thisOneSelected){
			autocompleteHidden.setModelObject(idValue);
		}

		return new AutocompleteJson(idValue, displayValue);
	}

	/**
	 * Call in the onBeforeRender of the autocomplete behavior
	 * @param autocomplete
	 */
	protected void onBeforeRenderAutocomplete(Autocomplete<?> autocomplete) {

	}

	public void setChoiceRenderer(IChoiceRenderer<? super T> choiceRenderer) {
		this.choiceRenderer = choiceRenderer;
	}

	public IChoiceRenderer<? super T> getChoiceRenderer() {
		if(choiceRenderer == null){
			choiceRenderer = new ChoiceRenderer<T>();
		}
		return choiceRenderer;
	}

	public void setAutoUpdate(boolean autoUpdate) {
		this.autoUpdate = autoUpdate;
	}

	/**
	 * Should this value get sent to the server when it is selected automatically
	 * @return
	 */
	public boolean isAutoUpdate() {
		return autoUpdate;
	}
}

