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
package org.odlabs.wiquery.ui.button;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

/**
 * $Id$
 * <p>
 * Bean to specify a button element for the button set
 * </p>
 * 
 * @param <T> The model object type
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class ButtonElement<T extends Serializable> implements Serializable {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = -4025676226101615452L;

	// Properties
	private IModel<T> model;
	private IModel<String> label;
	
	/**
	 * Default constructor
	 */
	public ButtonElement() {
		super();
		model = null;
		label = null;
	}
	
	/**
	 * Constructor
	 * @param model Model of the object
	 * @param label Model for the button's label
	 */
	public ButtonElement(IModel<T> model, IModel<String> label) {
		this();
		this.model = model;
		this.label = label;
	}
	
	/**
	 * @return the model of the label
	 */
	public IModel<String> getLabel() {
		return label;
	}
	
	/**
	 * @return the model of our object
	 */
	public IModel<T> getModel() {
		return model;
	}
	
	/**
	 * Set the model label
	 * @param label
	 */
	public void setLabel(IModel<String> label) {
		this.label = label;
	}
	
	/**
	 * Set the mode of the object
	 * @param model
	 */
	public void setModel(IModel<T> model) {
		this.model = model;
	}
}
