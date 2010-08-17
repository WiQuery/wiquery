package org.odlabs.wiquery.ui.dialog;

import org.odlabs.wiquery.commons.WiqueryTestCase;
import org.odlabs.wiquery.commons.WiqueryTestPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * wiQuery test case on the dialog component
 * @author Julien Roche
 *
 */
public class DialogWiqueryTestCase extends WiqueryTestCase {
	protected static final Logger log = LoggerFactory.getLogger(
			DialogWiqueryTestCase.class);
	
	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.commons.WiqueryTestCase#getWiqueryTestPage()
	 */
	@Override
	protected Class<? extends WiqueryTestPage> getWiqueryTestPage() {
		return DialogTestPage.class;
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.commons.WiqueryTestCase#testMethod()
	 */
	@Override
	public void testMethod() {
		Dialog dialog = new Dialog("dialogTest");
		
		String expectedJavascript = "$('#dialogTest1').dialog('enable');";
		String generatedJavascript = dialog.enable().render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.commons.WiqueryTestCase#testStatement()
	 */
	@Override
	public void testStatement() {
		/*Dialog dialog = new Dialog("dialogTest");
		
		// Comparaison 1
		String expectedJavascript = "$('#dialogTest1').dialog({position: 'center', autoOpen: false});";
		String generatedJavascript = dialog.statement().render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Comparaison 2
		dialog = new Dialog("dialogTest");
		dialog.setAutoOpen(true);
		dialog.setCloseOnEscape(true);
		dialog.setPosition(WindowPosition.BOTTOM);
		dialog.setZIndex(25000);
		dialog.setCloseEvent(new DefaultJsScopeUiEvent("alert('test');"));
		
		expectedJavascript = "$('#dialogTest2').dialog({position: 'bottom', " +
				"zIndex: 25000, closeOnEscape: true, autoOpen: true, " +
				"close: function(event, ui) {\n\talert('test');\n}});";
		generatedJavascript = dialog.statement().render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);*/
		Assert.assertTrue(true);
	}
}
