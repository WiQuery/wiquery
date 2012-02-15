package org.odlabs.wiquery.core.resources;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.ResourceReference;

public abstract class JavaScriptHeaderItems extends JavaScriptHeaderItem
{
	protected JavaScriptHeaderItems(String condition)
	{
		super(condition);
	}

	/**
	 * @return a list of {@link JavaScriptReferenceHeaderItem}s in the order of and
	 *         containing the given references
	 */
	public static Iterable< ? extends JavaScriptReferenceHeaderItem> forReferences(
			ResourceReference... references)
	{
		if (references == null || references.length == 0)
			return new ArrayList<JavaScriptReferenceHeaderItem>(0);

		List<JavaScriptReferenceHeaderItem> list =
			new ArrayList<JavaScriptReferenceHeaderItem>(references.length);

		for (ResourceReference reference : references)
			list.add(forReference(reference));

		return list;
	}
}
