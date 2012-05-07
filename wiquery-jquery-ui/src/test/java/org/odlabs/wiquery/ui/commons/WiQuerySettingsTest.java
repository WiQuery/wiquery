package org.odlabs.wiquery.ui.commons;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.resource.JQueryResourceReference;
import org.apache.wicket.util.tester.Result;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.odlabs.wiquery.core.events.Event;
import org.odlabs.wiquery.core.events.WiQueryEventBehavior;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.themes.WiQueryCoreThemeResourceReference;
import org.odlabs.wiquery.ui.widget.WidgetJavaScriptResourceReference;

/**
 * Tests {@link WiQuerySettings}.
 * 
 * @author Arthur Hupka
 * @since 01.10.2010
 */
public class WiQuerySettingsTest extends WiQueryTestCase
{
	@WiQueryUIPlugin
	private final class TestEventBehavior extends WiQueryEventBehavior
	{
		private static final long serialVersionUID = 1L;

		private TestEventBehavior(Event event)
		{
			super(event);
		}

		@Override
		public void renderHead(Component component, IHeaderResponse response)
		{
			response.render(JavaScriptHeaderItem.forReference(WidgetJavaScriptResourceReference
				.get()));
		}
	}

	private final class TestEvent extends Event
	{
		private static final long serialVersionUID = 1L;

		@Override
		public JsScope callback()
		{
			return new JsScope()
			{
				private static final long serialVersionUID = 1L;

				@Override
				protected void execute(JsScopeContext scopeContext)
				{
				}
			};
		}
	}

	@Test
	public void testWiquerySettingsDefault()
	{
		startTestPage();
		tester.dumpPage();
		tester.assertContains(JQueryResourceReference.class.getName());
		tester.assertContains(WiQueryCoreThemeResourceReference.class.getName());
		tester.assertContains(CoreUIJavaScriptResourceReference.class.getName());
		tester.assertContains(WidgetJavaScriptResourceReference.class.getName());

	}

	private void startTestPage()
	{
		WiQuerySettingsTestPage p = new WiQuerySettingsTestPage();
		p.add(new TestEventBehavior(new TestEvent()));
		tester.startPage(p);
	}

	public void assertNotContains(String message, String string)
	{
		Result r = tester.ifContains("^((?!" + string + ").)*$");
		if (r.wasFailed())
		{
			throw new ComparisonFailure("String [" + string
				+ "] found in page, but shouldn't be there:  " + message, string, "@page");
		}
	}
}
