package org.odlabs.wiquery.core.ajax;

import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;

/**
 * $Id: JQueryAjaxOptions
 * <p>
 * Bean storing jQuery Ajax options
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class JQueryAjaxOption extends Object implements IComplexOption, IModel<JQueryAjaxOption>
{
	/**
	 * Enumeration of Ajax type
	 * 
	 * @author Julien Roche
	 * 
	 */
	public enum AjaxType
	{
		GET,
		POST;
	}

	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 9153408474806895999L;

	// Properties
	private Options options;

	public JQueryAjaxOption()
	{
		super();
		options = new Options();
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
	 * By default, all requests are sent asynchronous (i.e. this is set to true by
	 * default). If you need synchronous requests, set this option to false. Note that
	 * synchronous requests may temporarily lock the browser, disabling any actions while
	 * the request is active.
	 * 
	 * @param async
	 */
	public JQueryAjaxOption setAsync(boolean async)
	{
		this.options.put("async", async);
		return this;
	}

	/**
	 * @return the async option value
	 */
	public boolean isAsync()
	{
		if (this.options.containsKey("async"))
		{
			return this.options.getBoolean("async");
		}

		return true;
	}

	/**
	 * Set to false it will force the pages that you request to not be cached by the
	 * browser.
	 * 
	 * @param cache
	 */
	public JQueryAjaxOption setCache(boolean cache)
	{
		this.options.put("cache", cache);
		return this;
	}

	/**
	 * @return the cache option value
	 */
	public boolean isCache()
	{
		if (this.options.containsKey("cache"))
		{
			return this.options.getBoolean("cache");
		}

		return true;
	}

	/**
	 * When sending data to the server, use this content-type. Default is
	 * "application/x-www-form-urlencoded", which is fine for most cases.
	 * 
	 * @param contentType
	 */
	public JQueryAjaxOption setContentType(String contentType)
	{
		this.options.putLiteral("contentType", contentType);
		return this;
	}

	/**
	 * @return the contentType option value
	 */
	public String getContentType()
	{
		String contentType = this.options.getLiteral("contentType");
		return contentType == null ? "application/x-www-form-urlencoded" : contentType;
	}

	/**
	 * Data to be sent to the server. It is converted to a query string, if not already a
	 * string. It's appended to the url for GET-requests. See processData option to
	 * prevent this automatic processing. Object must be Key/Value pairs. If value is an
	 * Array, jQuery serializes multiple values with same key i.e. {foo:["bar1", "bar2"]}
	 * becomes '&foo=bar1&foo=bar2'.
	 * 
	 * @param contentType
	 */
	public JQueryAjaxOption setData(String data)
	{
		this.options.put("data", data);
		return this;
	}

	/**
	 * @return the contentType option value
	 */
	public String getData()
	{
		return this.options.get("data");
	}

	/**
	 * The type of data that you're expecting back from the server. If none is specified,
	 * jQuery will intelligently pass either responseXML or responseText to your success
	 * callback, based on the MIME type of the response. The available types (and the
	 * result passed as the first argument to your success callback) are:
	 * <ul>
	 * <li>"xml": Returns a XML document that can be processed via jQuery.</li>
	 * <li>"html": Returns HTML as plain text; included script tags are evaluated when
	 * inserted in the DOM.</li>
	 * <li>"script": Evaluates the response as JavaScript and returns it as plain text.
	 * Disables caching unless option "cache" is used. Note: This will turn POSTs into
	 * GETs for remote-domain requests.</li>
	 * <li>"json": Evaluates the response as JSON and returns a JavaScript Object.</li>
	 * <li>"jsonp": Loads in a JSON block using JSONP. Will add an extra "?callback=?" to
	 * the end of your URL to specify the callback. (Added in jQuery 1.2)</li>
	 * <li>"text": A plain text string.</li>
	 * </ul>
	 * 
	 * @param dataType
	 */
	public JQueryAjaxOption setDataType(String dataType)
	{
		this.options.putLiteral("dataType", dataType);
		return this;
	}

	/**
	 * @return the dataType option value
	 */
	public String getDataType()
	{
		return this.options.getLiteral("dataType");
	}

	/**
	 * Whether to trigger global AJAX event handlers for this request. The default is
	 * true. Set to false to prevent the global handlers like ajaxStart or ajaxStop from
	 * being triggered. This can be used to control various Ajax Events.
	 * 
	 * @param global
	 */
	public JQueryAjaxOption setGlobal(boolean global)
	{
		this.options.put("global", global);
		return this;
	}

	/**
	 * @return the global option value
	 */
	public boolean isGlobal()
	{
		if (this.options.containsKey("global"))
		{
			return this.options.getBoolean("global");
		}

		return true;
	}

	/**
	 * Allow the request to be successful only if the response has changed since the last
	 * request. This is done by checking the Last-Modified header. Default value is false,
	 * ignoring the header.
	 * 
	 * @param ifModified
	 */
	public JQueryAjaxOption setIfModified(boolean ifModified)
	{
		this.options.put("ifModified", ifModified);
		return this;
	}

	/**
	 * @return the ifModified option value
	 */
	public boolean isIfModified()
	{
		if (this.options.containsKey("ifModified"))
		{
			return this.options.getBoolean("ifModified");
		}

		return true;
	}

	/**
	 * Override the callback function name in a jsonp request. This value will be used
	 * instead of 'callback' in the 'callback=?' part of the query string in the url for a
	 * GET or the data for a POST. So {jsonp:'onJsonPLoad'} would result in
	 * 'onJsonPLoad=?' passed to the server.
	 * 
	 * @param jsonp
	 */
	public JQueryAjaxOption setJsonp(String jsonp)
	{
		this.options.put("jsonp", jsonp);
		return this;
	}

	/**
	 * @return the jsonp option value
	 */
	public String getJsonp()
	{
		return this.options.get("jsonp");
	}

	/**
	 * A password to be used in response to an HTTP access authentication request.
	 * 
	 * @param password
	 */
	public JQueryAjaxOption setPassword(String password)
	{
		this.options.putLiteral("password", password);
		return this;
	}

	/**
	 * @return the password option value
	 */
	public String getPassword()
	{
		return this.options.getLiteral("password");
	}

	/**
	 * By default, data passed in to the data option as an object (technically, anything
	 * other than a string) will be processed and transformed into a query string, fitting
	 * to the default content-type "application/x-www-form-urlencoded". If you want to
	 * send DOMDocuments, or other non-processed data, set this option to false.
	 * 
	 * @param processData
	 */
	public JQueryAjaxOption setProcessData(boolean processData)
	{
		this.options.put("processData", processData);
		return this;
	}

	/**
	 * @return the processData option value
	 */
	public boolean isProcessData()
	{
		if (this.options.containsKey("processData"))
		{
			return this.options.getBoolean("processData");
		}

		return true;
	}

	/**
	 * Only for requests with 'jsonp' or 'script' dataType and GET type. Forces the
	 * request to be interpreted as a certain charset. Only needed for charset differences
	 * between the remote and local content.
	 * 
	 * @param scriptCharset
	 */
	public JQueryAjaxOption setScriptCharset(String scriptCharset)
	{
		this.options.putLiteral("scriptCharset", scriptCharset);
		return this;
	}

	/**
	 * @return the scriptCharset option value
	 */
	public String getScriptCharset()
	{
		return this.options.getLiteral("scriptCharset");
	}

	/**
	 * Set a local timeout (in milliseconds) for the request. This will override the
	 * global timeout, if one is set via $.ajaxSetup. For example, you could use this
	 * property to give a single request a longer timeout than all other requests that
	 * you've set to time out in one second. See $.ajaxSetup() for global timeouts.
	 * 
	 * @param timeout
	 */
	public JQueryAjaxOption setTimeout(int timeout)
	{
		this.options.put("timeout", timeout);
		return this;
	}

	/**
	 * @return the timeout option value
	 */
	public int getTimeout()
	{
		if (this.options.containsKey("timeout"))
		{
			return this.options.getInt("timeout");
		}

		return 0;
	}

	/**
	 * The type of request to make ("POST" or "GET"), default is "GET". Note: Other HTTP
	 * request methods, such as PUT and DELETE, can also be used here, but they are not
	 * supported by all browsers.
	 * 
	 * @param type
	 */
	public JQueryAjaxOption setType(AjaxType type)
	{
		this.options.putLiteral("type", type.toString());
		return this;
	}

	/**
	 * @return the type option value
	 */
	public AjaxType getType()
	{
		String type = this.options.getLiteral("type");
		return type == null ? AjaxType.GET : AjaxType.valueOf(type);
	}

	/**
	 * The URL to request. This *must* be a string (e.g., document.location.href) and not
	 * a Location object (e.g., document.location)
	 * 
	 * @param url
	 */
	public JQueryAjaxOption setUrl(String url)
	{
		this.options.putLiteral("url", url);
		return this;
	}

	/**
	 * @return the url option value
	 */
	public String getUrl()
	{
		return this.options.getLiteral("url");
	}

	/**
	 * A username to be used in response to an HTTP access authentication request.
	 * 
	 * @param username
	 */
	public JQueryAjaxOption setUsername(String username)
	{
		this.options.putLiteral("username", username);
		return this;
	}

	/**
	 * @return the username option value
	 */
	public String getUsername()
	{
		return this.options.getLiteral("username");
	}

	/*---- Event section ---*/

	/**
	 * A pre-callback to modify the XMLHttpRequest object before it is sent. Use this to
	 * set custom headers etc. The XMLHttpRequest is passed as the only argument. This is
	 * an Ajax Event. You may return false in function to cancel the request.
	 * 
	 * @param beforeSend
	 */
	public JQueryAjaxOption setBeforeSendEvent(JsScope beforeSend)
	{
		this.options.put("beforeSend", beforeSend);
		return this;
	}

	/**
	 * A function to be called when the request finishes (after success and error
	 * callbacks are executed). The function gets passed two arguments: The XMLHttpRequest
	 * object and a string describing the type of success of the request
	 * 
	 * @param complete
	 */
	public JQueryAjaxOption setCompleteEvent(JsScope complete)
	{
		this.options.put("complete", complete);
		return this;
	}

	/**
	 * A function to be used to handle the raw responsed data of XMLHttpRequest. This is a
	 * pre-filtering function to sanitize the response.You should return the sanitized
	 * data.The function gets passed two arguments: The raw data returned from the server,
	 * and the 'dataType' parameter.
	 * 
	 * @param dataFilter
	 */
	public JQueryAjaxOption setDataFilterEvent(JsScope dataFilter)
	{
		this.options.put("dataFilter", dataFilter);
		return this;
	}

	/**
	 * A function to be called if the request fails. The function is passed three
	 * arguments: The XMLHttpRequest object, a string describing the type of error that
	 * occurred and an optional exception object, if one occurred. Possible values for the
	 * second argument (besides null) are "timeout", "error", "notmodified" and
	 * "parsererror"
	 * 
	 * @param error
	 */
	public JQueryAjaxOption setErrorEvent(JsScope error)
	{
		this.options.put("error", error);
		return this;
	}

	/**
	 * A function to be called if the request succeeds. The function gets passed two
	 * arguments: The data returned from the server, formatted according to the 'dataType'
	 * parameter, and a string describing the status. This is an Ajax Event.
	 * 
	 * @param success
	 */
	public JQueryAjaxOption setSuccessEvent(JsScope success)
	{
		this.options.put("success", success);
		return this;
	}

	/**
	 * Callback for creating the XMLHttpRequest object. Defaults to the ActiveXObject when
	 * available (IE), the XMLHttpRequest otherwise. Override to provide your own
	 * implementation for XMLHttpRequest or enhancements to the factory.It's not available
	 * in jQuery 1.2.6 and in any early version.
	 * 
	 * @param xhr
	 */
	public JQueryAjaxOption setXhrEvent(JsScope xhr)
	{
		this.options.put("xhr", xhr);
		return this;
	}

	public JQueryAjaxOption getObject()
	{
		return this;
	}

	public void setObject(JQueryAjaxOption object)
	{
		throw new UnsupportedOperationException(
			"The setObject() function is not supported for object JQueryAjaxOption.");
	}

	public void detach()
	{
		options.detach();
	}
}
