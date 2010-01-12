package org.odlabs.wiquery.core.ajax;

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
public class JQueryAjaxOption extends Object implements IComplexOption {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 9153408474806895999L;
	
	// Properties
	private Options options;
	
	public JQueryAjaxOption() {
		super();
		options = new Options();
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return options.getJavaScriptOptions();
	}
	
	/**Method retrieving the options
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}
	
	/*---- Options section ---*/
	
	/**
	 * By default, all requests are sent asynchronous (i.e. this is set to true 
	 * by default). If you need synchronous requests, set this option to false. 
	 * Note that synchronous requests may temporarily lock the browser, disabling 
	 * any actions while the request is active.
	 * @param async
	 */
	public JQueryAjaxOption setAsync(boolean async) {
		this.options.put("async", async);
		return this;
	}
	
	/**
	 * @return the async option value
	 */
	public boolean isAsync() {
		if(this.options.containsKey("async")){
			return this.options.getBoolean("async");
		}
		
		return false;
	}
	
	/**
	 * Set to false it will force the pages that you request to not be cached by the browser.
	 * @param cache
	 */
	public JQueryAjaxOption setCache(boolean cache) {
		this.options.put("cache", cache);
		return this;
	}
	
	/**
	 * @return the cache option value
	 */
	public boolean isCache() {
		if(this.options.containsKey("cache")){
			return this.options.getBoolean("cache");
		}
		
		return false;
	}
	
	/**
	 * When sending data to the server, use this content-type. Default is 
	 * "application/x-www-form-urlencoded", which is fine for most cases.
	 * @param contentType
	 */
	public JQueryAjaxOption setContentType(String contentType) {
		this.options.put("contentType", contentType);
		return this;
	}
	
	/**
	 * @return the contentType option value
	 */
	public String getContentType() {
		return this.options.get("contentType");
	}
	
	/**
	 * Data to be sent to the server. It is converted to a query string, if not 
	 * already a string. It's appended to the url for GET-requests. See 
	 * processData option to prevent this automatic processing. Object must be 
	 * Key/Value pairs. If value is an Array, jQuery serializes multiple values 
	 * with same key i.e. {foo:["bar1", "bar2"]} becomes '&foo=bar1&foo=bar2'.
	 * @param contentType
	 */
	public JQueryAjaxOption setData(String data) {
		this.options.put("data", data);
		return this;
	}
	
	/**
	 * @return the contentType option value
	 */
	public String getData() {
		return this.options.get("data");
	}
	
	/**
	 * The type of data that you're expecting back from the server. 
	 * If none is specified, jQuery will intelligently pass either responseXML 
	 * or responseText to your success callback, based on the MIME type of the 
	 * response. The available types (and the result passed as the first argument 
	 * to your success callback) are:
	 * 	<ul>
	 * 		<li>"xml": Returns a XML document that can be processed via jQuery.</li>
	 * 		<li>"html": Returns HTML as plain text; included script tags are 
	 * evaluated when inserted in the DOM.</li>
	 * 		<li>"script": Evaluates the response as JavaScript and returns it as 
	 * plain text. Disables caching unless option "cache" is used. Note: 
	 * This will turn POSTs into GETs for remote-domain requests.</li>
	 * 		<li>"json": Evaluates the response as JSON and returns a JavaScript Object.</li>
	 * 		<li>"jsonp": Loads in a JSON block using JSONP. Will add an extra 
	 * "?callback=?" to the end of your URL to specify the callback. 
	 * (Added in jQuery 1.2)</li>
	 * 		<li>"text": A plain text string.</li>
	 * 	</ul>
	 * 
	 * @param dataType
	 */
	public JQueryAjaxOption setDataType(String dataType) {
		this.options.put("dataType", dataType);
		return this;
	}
	
	/**
	 * @return the dataType option value
	 */
	public String getDataType() {
		return this.options.get("dataType");
	}
	
	/*---- Event section ---*/
	
	/**A pre-callback to modify the XMLHttpRequest object before it is sent. 
	 * Use this to set custom headers etc. The XMLHttpRequest is passed as the 
	 * only argument. This is an Ajax Event. You may return false in function 
	 * to cancel the request.
	 * @param beforeSend
	 */
	public void setBeforeSendEvent(JsScope beforeSend) {
		this.options.put("beforeSend", beforeSend);
	}
	
	/**A function to be called when the request finishes (after success and error 
	 * callbacks are executed). The function gets passed two arguments: The 
	 * XMLHttpRequest object and a string describing the type of success of the 
	 * request
	 * @param complete
	 */
	public void setCompleteEvent(JsScope complete) {
		this.options.put("complete", complete);
	}
	
	/**A function to be used to handle the raw responsed data of XMLHttpRequest.
	 * This is a pre-filtering function to sanitize the response.You should 
	 * return the sanitized data.The function gets passed two arguments: The 
	 * raw data returned from the server, and the 'dataType' parameter. 
	 * @param dataFilter
	 */
	public void setDataFilterEvent(JsScope dataFilter) {
		this.options.put("dataFilter", dataFilter);
	}
	
	/**A function to be called if the request fails. The function is passed 
	 * three arguments: The XMLHttpRequest object, a string describing the 
	 * type of error that occurred and an optional exception object, if one 
	 * occurred. Possible values for the second argument (besides null) are 
	 * "timeout", "error", "notmodified" and "parsererror"
	 * @param error
	 */
	public void setErrorEvent(JsScope error) {
		this.options.put("error", error);
	}
}
