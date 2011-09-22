package org.odlabs.wiquery.core.util;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.IPageRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class WiQueryUtil
{

	public static Page getCurrentPage()
	{
		if (RequestCycle.get().getActiveRequestHandler() instanceof IPageRequestHandler)
			return (Page) ((IPageRequestHandler) RequestCycle.get().getActiveRequestHandler())
				.getPage();

		return null;
	}

	public static boolean isCurrentRequestAjax()
	{
		return AjaxRequestTarget.get() != null;
	}

	public static PageParameters getCurrentPageParameters()
	{
		if (RequestCycle.get().getActiveRequestHandler() instanceof IPageRequestHandler)
		{
			Page page =
				(Page) ((IPageRequestHandler) RequestCycle.get().getActiveRequestHandler())
					.getPage();
			if (page != null)
				return page.getPageParameters();
		}

		return null;
	}
}
