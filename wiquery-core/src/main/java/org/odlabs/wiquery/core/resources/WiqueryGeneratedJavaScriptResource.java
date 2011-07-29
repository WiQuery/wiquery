package org.odlabs.wiquery.core.resources;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.util.template.PackageTextTemplate;

/**
 * <p>
 * Uses the {@link PackageTextTemplate} to make a generated resource.
 * </p>
 * 
 * @author Hielke Hoeve
 * @since 1.0
 */
public class WiqueryGeneratedJavaScriptResource {
	private static final long serialVersionUID = 1L;

	public static String wiqueryGeneratedJavascriptCode(CharSequence javaScriptCode) {
		PackageTextTemplate jstemplate = new PackageTextTemplate(
                WiqueryGeneratedJavaScriptResource.class,
                "wiquery-gen.js");

        Map<String, Object> genJs = new HashMap<String, Object>();
        genJs.put("wiqueryoutput", javaScriptCode);
        jstemplate.interpolate(genJs);

        return jstemplate.asString();
	}
}
