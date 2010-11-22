package org.odlabs.wiquery.ui.commons;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.dialog.Dialog;

public class WiQueryUIPluginTestCase extends WiQueryTestCase {
	/**
	 * Method testing the inheritance of the annotation
	 */
	@Test
	public void testInheritance() {
		Dialog dialog = new Dialog("dialogId");
		assertNotNull(dialog.getClass().getAnnotation(WiQueryUIPlugin.class));

		InnerClassDialog innerClassDialog = new InnerClassDialog(
				"innerDialogId");
		assertNotNull(innerClassDialog.getClass().getAnnotation(
				WiQueryUIPlugin.class));
	}

	/**
	 * A simple class to test the inheritance of the annotation
	 * 
	 * @author Julien Roche
	 */
	private class InnerClassDialog extends Dialog {
		private static final long serialVersionUID = -647533062898281291L;

		public InnerClassDialog(String id) {
			super(id);
		}

	}
}
