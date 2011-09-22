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
package org.odlabs.wiquery.ui.tabs;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.string.Strings;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.datepicker.DateOption;

/**
 * $Id: JQueryCookieOption
 * <p>
 * Bean storing jQuery cookie options (only the value of cookie won't be stored into the
 * options)
 * </p>
 * <p>
 * Warning : You must import the {@link CookieJavaScriptResourceReference} to enable the
 * cookie option
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class JQueryCookieOption extends Object implements IComplexOption
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 9153408474806895999L;

	// Properties
	private String value;

	private String name;

	private Options options;

	/**
	 * Constructeur
	 * 
	 * @param name
	 *            Name of the cookie
	 */
	public JQueryCookieOption(String name)
	{
		super();

		if (Strings.isEmpty(name))
		{
			throw new WicketRuntimeException("name cannot be null or empty");
		}

		options = new Options();
		setName(name);
	}

	public CharSequence getJavascriptOption()
	{
		return options.getJavaScriptOptions();
	}

	/**
	 * Method retrieving the options
	 * 
	 * @return the options
	 */
	protected Options getOptions()
	{
		return options;
	}

	/*---- Options section ---*/

	/**
	 * Set's the domain name (example: server.domain.net)
	 * 
	 * @param domain
	 */
	public JQueryCookieOption setDomain(String domain)
	{
		this.options.putLiteral("domain", domain);
		return this;
	}

	/**
	 * @return the domain option value
	 */
	public String getDomain()
	{
		return this.options.getLiteral("domain");
	}

	/**
	 * Set's with a Number or a Date the time of validation of the cookie
	 * 
	 * @param expires
	 */
	public JQueryCookieOption setExpires(DateOption expires)
	{
		this.options.put("expires", expires);
		return this;
	}

	/**
	 * @return the expires option value
	 */
	public DateOption getExpires()
	{
		IComplexOption expires = this.options.getComplexOption("expires");

		if (expires != null && expires instanceof DateOption)
		{
			return (DateOption) expires;
		}

		return null;
	}

	/**
	 * Set's the name of the cookie
	 * 
	 * @param name
	 */
	private JQueryCookieOption setName(String name)
	{
		this.name = name;
		return this;
	}

	/**
	 * @return the name option value
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set's the path ("/" or "http://server.domain.net" for examples)
	 * 
	 * @param path
	 */
	public JQueryCookieOption setPath(String path)
	{
		this.options.putLiteral("path", path);
		return this;
	}

	/**
	 * @return the path option value
	 */
	public String getPath()
	{
		return this.options.getLiteral("path");
	}

	/**
	 * Set's the transaction security status (must we enable the SSL protocol ?)
	 * 
	 * @param secure
	 */
	public JQueryCookieOption setSecure(boolean secure)
	{
		this.options.put("secure", secure);
		return this;
	}

	/**
	 * @return the secure option value
	 */
	public boolean isSecure()
	{
		if (this.options.containsKey("secure"))
		{
			return this.options.getBoolean("secure");
		}

		return false;
	}

	/**
	 * Set's the value of the cookie
	 * 
	 * @param value
	 */
	public JQueryCookieOption setValue(String value)
	{
		this.value = value;
		return this;
	}

	/**
	 * @return the value
	 */
	public String getValue()
	{
		return this.value;
	}

	/*---- Methods section ---*/

	/**
	 * Method to delete the cookie This will return the element back to its pre-init
	 * state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement deleteCookie()
	{
		return new JsStatement().$().chain("cookie", JsUtils.quotes(getName()), "null",
			getJavascriptOption());
	}

	/**
	 * Method to delete the cookie within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void deleteCookie(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.deleteCookie().render().toString());
	}

	/**
	 * Method to retrieve the cookie This will return the element back to its pre-init
	 * state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement getCookie()
	{
		return new JsStatement().$().chain("cookie", JsUtils.quotes(getName()));
	}

	/**
	 * Method to set the cookie This will return the element back to its pre-init state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement setCookie()
	{
		return new JsStatement().$().chain("cookie", JsUtils.quotes(getName()),
			JsUtils.quotes(getValue()), getJavascriptOption());
	}

	/**
	 * Method to set the cookie within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void setCookie(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.setCookie().render().toString());
	}
}
