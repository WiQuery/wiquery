package org.odlabs.wiquery.ui.commons;

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.ui.dialog.Dialog;
import org.testng.annotations.Test;

public class WiQueryUIPluginTestCase extends TestCase {
	/**
	 * Method testing the inheritance of the annotation
	 */
	@Test
	public void testInheritance() {
		@SuppressWarnings("unused")
		WicketTester wicketTester = new WicketTester(new WebApplication() {
			@Override
			public Class<? extends Page> getHomePage() {
				return null;
			}
		});
		
		Dialog dialog = new Dialog("dialogId");
		assertNotNull(dialog.getClass().getAnnotation(WiQueryUIPlugin.class));
		
		InnerClassDialog innerClassDialog = new InnerClassDialog("innerDialogId");
		assertNotNull(innerClassDialog.getClass().getAnnotation(WiQueryUIPlugin.class));
	}
	
	/**
	 * A simple class to test the inheritance of the annotation
	 * @author Julien Roche
	 *
	 */
	private class InnerClassDialog extends Dialog {
		private static final long serialVersionUID = -647533062898281291L;

		public InnerClassDialog(String id) {
			super(id);
		}
		
	}
}
