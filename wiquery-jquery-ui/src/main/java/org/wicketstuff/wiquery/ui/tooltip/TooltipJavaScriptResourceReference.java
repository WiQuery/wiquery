package org.wicketstuff.wiquery.ui.tooltip;

import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.wicketstuff.wiquery.core.resources.JavaScriptHeaderItems;
import org.wicketstuff.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.wicketstuff.wiquery.ui.position.PositionJavaScriptResourceReference;
import org.wicketstuff.wiquery.ui.widget.WidgetJavaScriptResourceReference;

public final class TooltipJavaScriptResourceReference extends JavaScriptResourceReference
{
	private static final long serialVersionUID = -4771815414204892357L;

	/**
	 * Singleton instance.
	 */
	private static final TooltipJavaScriptResourceReference INSTANCE =
		new TooltipJavaScriptResourceReference();

	/**
	 * Builds a new instance of {@link TooltipJavaScriptResourceReference}.
	 */
	private TooltipJavaScriptResourceReference()
	{
		super(TooltipJavaScriptResourceReference.class, "jquery.ui.tooltip.js");
	}

	/**
	 * Returns the {@link TooltipJavaScriptResourceReference} instance.
	 */
	public static TooltipJavaScriptResourceReference get()
	{
		return INSTANCE;
	}

	@Override
	public List<HeaderItem> getDependencies()
	{
		return JavaScriptHeaderItems.forReferences(CoreUIJavaScriptResourceReference.get(),
			WidgetJavaScriptResourceReference.get(), PositionJavaScriptResourceReference.get());
	}
}
