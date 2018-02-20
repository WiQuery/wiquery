package org.wicketstuff.wiquery.core.util;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.WicketAjaxJQueryResourceReference;
import org.apache.wicket.core.request.handler.IPageRequestHandler;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

public final class WiQueryUtil
{
	public static Page getCurrentPage()
	{
		if (RequestCycle.get().getActiveRequestHandler() instanceof IPageRequestHandler)
			return (Page) ((IPageRequestHandler) RequestCycle.get().getActiveRequestHandler())
				.getPage();

		return null;
	}

	public static PageParameters getCurrentPageParameters()
	{
		if (RequestCycle.get().getActiveRequestHandler() instanceof IPageRequestHandler)
		{
			Page page = getCurrentPage();
			if (page != null)
				return page.getPageParameters();
		}

		return null;
	}

	public static boolean isCurrentRequestAjax()
	{
		return RequestCycle.get() != null
			? RequestCycle.get().find(AjaxRequestTarget.class) != null : false;
	}

	/**
	 * Looks for the jQuery resource reference. First we see if the application knows
	 * where it is, in case the user has overriden it. If that fails we use the default
	 * resource reference.
	 */
	public static ResourceReference getJQueryResourceReference()
	{
		ResourceReference reference;
		if (Application.exists())
		{
			reference = Application.get().getJavaScriptLibrarySettings().getJQueryReference();
		}
		else
		{
			reference = JQueryResourceReference.get();
		}
		return reference;
	}


	public static ResourceReference getWicketAjaxReference()
	{
		ResourceReference reference;
		if (Application.exists())
		{
			reference = Application.get().getJavaScriptLibrarySettings().getWicketAjaxReference();
		}
		else
		{
			reference = WicketAjaxJQueryResourceReference.get();
		}
		return reference;
	}
	
	private WiQueryUtil()
	{
	}
}
