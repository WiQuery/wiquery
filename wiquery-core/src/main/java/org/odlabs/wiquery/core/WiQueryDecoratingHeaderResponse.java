package org.odlabs.wiquery.core;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.IReferenceHeaderItem;
import org.apache.wicket.markup.head.ResourceAggregator;
import org.apache.wicket.request.resource.ResourceReference;
import org.odlabs.wiquery.core.ui.ICoreUICssResourceReference;
import org.odlabs.wiquery.core.ui.ICoreUIJavaScriptResourceReference;

/**
 * All that is left for WiQuery to do is check whether the added resources should be added
 * according to {@link WiQuerySettings}.
 * 
 * @author Hielke Hoeve
 */
public class WiQueryDecoratingHeaderResponse extends ResourceAggregator
{
	protected WiQuerySettings settings = WiQuerySettings.get();

	public WiQueryDecoratingHeaderResponse(IHeaderResponse real)
	{
		super(real);
	}

	@Override
	public void render(HeaderItem item)
	{
		if (item instanceof IReferenceHeaderItem)
		{
			IReferenceHeaderItem refItem = (IReferenceHeaderItem) item;
			if (isReferenceAllowed(refItem.getReference()))
			{
				super.render(item);
			}
		}
	}

	protected boolean isReferenceAllowed(ResourceReference reference)
	{
		if (reference == null)
		{
			throw new IllegalStateException("ResourceReference can not be null");
		}
		else if (!settings.isEnableWiqueryResourceManagement()
			&& (reference instanceof IWiQueryJavaScriptResourceReference || reference instanceof IWiQueryCssResourceReference))
		{
			return false;
		}
		else if (!settings.isAutoImportWiQueryJavaScriptResource()
			&& reference instanceof IWiQueryJavaScriptResourceReference)
		{
			return false;
		}
		else if (!settings.isAutoImportWiQueryStyleSheetResource()
			&& reference instanceof IWiQueryCssResourceReference)
		{
			return false;
		}
		else if (!settings.isAutoImportJQueryUIJavaScriptResource()
			&& reference instanceof ICoreUIJavaScriptResourceReference)
		{
			return false;
		}
		else if (!settings.isAutoImportJQueryUIStyleSheetResource()
			&& reference instanceof ICoreUICssResourceReference)
		{
			return false;
		}

		return true;
	}
}
