/*
 * Copyright (c) 2008 Objet Direct
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
package org.objetdirect.wiquery.core.events;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IFormSubmittingComponent;

/**
 * $Id$
 * <p>
 * Binds the given {@link Event} to an Ajax request posting the associated
 * {@link Form} data.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.6
 */
public abstract class WickextAjaxFormSubmitBehavior extends
		WickextAjaxEventBehavior {

	/**
	 * The form associated with the request.
	 */
	private Form<?> form;

	/**
	 * Creates a new WickeXt event submitting form data via Ajax processing.
	 * 
	 * @param form
	 *            the submitted form.
	 * @param events
	 *            the list of events triggering submission.
	 */
	public WickextAjaxFormSubmitBehavior(Form<?> form, EventLabel... events) {
		super(events);
		this.form = form;
		// used to ask for automatic-JavaScript form submission
		form.setOutputMarkupId(true);
	}

	@Override
	protected void respond(AjaxRequestTarget target) {
		// performs the form life cycle
		form.onFormSubmitted();
		// if error are present, calling on error callback
		// calling on submit callback otherwise
		if (form.hasError()) {
			onError(target);
		} else {
			onSubmit(target);
		}
	}

	@Override
	protected CharSequence getHandlerScript() {
		final String formId = form.getMarkupId();
		final CharSequence url = getCallbackUrl();
		StringBuilder ajaxSubmissionCall = new StringBuilder();
		ajaxSubmissionCall.append("wicketSubmitFormById('").append(formId)
				.append("', '").append(url).append("', ");

		if (getComponent() instanceof IFormSubmittingComponent) {
			// adding the submitting component name
			IFormSubmittingComponent formComponent = ((IFormSubmittingComponent) getComponent());
			ajaxSubmissionCall.append("'").append(formComponent.getInputName())
					.append("' ");
		} else {
			ajaxSubmissionCall.append("null");
		}
		ajaxSubmissionCall.append(")");
		return ajaxSubmissionCall;
	}

	/**
	 * Processes form submission after validation cycle success.
	 */
	protected abstract void onSubmit(AjaxRequestTarget target);

	/**
	 * Processes form submission after validation cycle fails.
	 */
	protected abstract void onError(AjaxRequestTarget target);

}
