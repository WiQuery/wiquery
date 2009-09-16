package org.odlabs.wiquery.core.commons;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.IClusterable;
import org.apache.wicket.Resource;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.apache.wicket.util.time.Time;

public class WiqueryGeneratedJavaScriptResourceReference extends
		ResourceReference implements IClusterable {

	private static final long serialVersionUID = 1L;
	
	private static final String TEMPLATE_NAME = "wiquery-gen.js";
	
	private CharSequence javaScriptCode;
	
	private PackagedTextTemplate jstemplate = new PackagedTextTemplate(WiqueryGeneratedJavaScriptResourceReference.class, "wiquery-gen.js");
	
	public WiqueryGeneratedJavaScriptResourceReference(CharSequence javaScriptCode) {
		super(WiqueryGeneratedJavaScriptResourceReference.class, System.currentTimeMillis() + TEMPLATE_NAME);
		this.javaScriptCode = javaScriptCode;
	}

	/**
	 * Creates a new resource which returns the interpolated value of the text
	 * template.
	 * 
	 * @return a new resource which returns the interpolated value of the text
	 *         template
	 */
	protected Resource newResource() {
		return new Resource() {
			private static final long serialVersionUID = 1L;

			public IResourceStream getResourceStream() {
				Map<String, Object> genJs = new HashMap<String, Object>();
				genJs.put("wiqueryoutput", javaScriptCode);
				jstemplate.interpolate(genJs);
				final String stringValue = jstemplate.asString();
				return new StringResourceStream(stringValue);
			}
		};
	}

	/**
	 * Returns the last modified time of the {@link PackagedTextTemplate}
	 * itself.
	 * 
	 * @return the last modified time of the {@link PackagedTextTemplate} itself
	 */
	public Time lastModifiedTime() {
		return jstemplate.lastModifiedTime();
	}
}