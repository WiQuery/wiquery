package org.odlabs.wiquery.core.commons;

import org.apache.wicket.resource.JQueryResourceReference;
import org.junit.Test;
import org.odlabs.wiquery.core.WiQuerySettings;
import org.odlabs.wiquery.tester.WiQueryTestCase;

/**
 * Tests {@link WiQuerySettings}.
 * 
 * @author Arthur Hupka
 * @since 01.10.2010
 */
public class WiQuerySettingsTest extends WiQueryTestCase
{
	@Test
	public void testWiquerySettingsWiQueryJavaScriptDisabled()
	{
		WiQuerySettings.get().setAutoImportWiQueryJavaScriptResource(false);
		tester.startPage(WiQuerySettingsTestPage.class);

		tester.assertContainsNot(JQueryResourceReference.class.getName());
		tester.assertContainsNot(WiQueryTestResourceReference.class.getName());
	}
}
