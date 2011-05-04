package org.odlabs.wiquery.core.commons;

import org.apache.wicket.util.tester.Result;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;

/**
 * Tests {@link WiQuerySettings}.
 * 
 * @author Arthur Hupka
 * @since 01.10.2010
 */
public class WiQuerySettingsTest extends WiQueryTestCase {

	@Test
	public void testWiquerySettingsDefault() {
		startTestPage();
		tester.assertContains(CoreJavaScriptResourceReference.class.getName());
	}

	@Test
	public void testWiquerySettingsCoreLibraryDisabled() {
		WiQuerySettings.get().setAutoImportJQueryResource(false);
		startTestPage();
		assertNotContains(
				"Core library is disabled. Resource reference shouldn't be rendered",
				CoreJavaScriptResourceReference.class.getName());
	}

	@Test
	public void testWiquerySettingsUILibraryDisabled() {
		WiQuerySettings.get().setAutoImportJQueryUIResource(false);
		startTestPage();
		tester.assertContains(CoreJavaScriptResourceReference.class.getName());
	}

	@Test
	public void testWiqueryResourceManagementDisabled() {
		WiQuerySettings.get().setEnableWiqueryResourceManagement(false);
		startTestPage();
		assertNotContains(
				"Resource Management is disabled. Reference shouldn't be rendered",
				CoreJavaScriptResourceReference.class.getName());
	}

	private void startTestPage() {
		WiQuerySettingsTestPage p = new WiQuerySettingsTestPage();
		tester.startPage(p);
	}

	public void assertNotContains(String message, String string) {
		Result r = tester.ifContains("^((?!" + string + ").)*$");
		if (r.wasFailed()) {
			throw new ComparisonFailure("String [" + string
					+ "] found in page, but shouldn't be there:  " + message, string, "@page");
		}
	}

}
