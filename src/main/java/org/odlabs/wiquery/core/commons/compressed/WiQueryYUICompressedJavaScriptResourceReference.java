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
package org.odlabs.wiquery.core.commons.compressed;

import java.util.Locale;

import org.apache.wicket.Resource;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.CompressedPackageResource;
import org.apache.wicket.markup.html.PackageResource;
import org.odlabs.wiquery.core.commons.WiQuerySettings;

/**
 * <p>
 * Compresses the js resource on the fly with YUI Compressor. Then passes it on
 * to the {@link CompressedPackageResource} which gzips it.
 * </p>
 * 
 * @author Hielke Hoeve
 * @since 1.1
 */
public class WiQueryYUICompressedJavaScriptResourceReference extends
		ResourceReference {
	private static final long serialVersionUID = 1L;

	public WiQueryYUICompressedJavaScriptResourceReference(Class<?> scope,
			String name) {
		super(scope, name);
	}

	public WiQueryYUICompressedJavaScriptResourceReference(Class<?> scope,
			String name, Locale locale, String style) {
		super(scope, name, locale, style);
	}

	public WiQueryYUICompressedJavaScriptResourceReference(String name) {
		super(name);
	}

	@Override
	protected Resource newResource() {
		PackageResource packageResource;

		if (WiQuerySettings.get().isMinifiedResources())
			packageResource = WiQueryYUICompressedJavaScriptResource
					.newPackageResource(getScope(), getName(), getLocale(),
							getStyle());
		else
			packageResource = CompressedPackageResource.newPackageResource(
					getScope(), getName(), getLocale(), getStyle());

		if (packageResource != null) {
			locale = packageResource.getLocale();
		} else {
			throw new IllegalArgumentException("package resource [scope="
					+ getScope() + ",name=" + getName() + ",locale="
					+ getLocale() + "style=" + getStyle() + "] not found");
		}
		return packageResource;
	}
}