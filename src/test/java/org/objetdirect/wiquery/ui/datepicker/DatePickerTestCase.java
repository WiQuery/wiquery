package org.objetdirect.wiquery.ui.datepicker;

import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.apache.wicket.protocol.http.documentvalidation.Tag;
import org.objetdirect.wiquery.commons.WickextTestCase;
import org.objetdirect.wiquery.commons.WickextTestPage;
import org.objetdirect.wiquery.ui.datepicker.DatePicker;
import org.objetdirect.wiquery.ui.datepicker.DatePickerLanguageResourceReference;

public class DatePickerTestCase extends WickextTestCase {

	@Override
	protected void appendBody(Tag body) {
		Tag datePickerInput = new Tag("input");
		body.addExpectedChild(datePickerInput);
	}

	@Override
	protected void appendNeededResources(Tag headTag) {
		addExpectedJavaScriptResource(headTag, new JavascriptResourceReference(
				DatePicker.class, "ui.datepicker.js"));
		addExpectedJavaScriptResource(headTag,
				new DatePickerLanguageResourceReference(getComponent().getLocale()));

	}

	@Override
	protected Class<? extends WickextTestPage> getTestPage() {
		return DatePickerTestCasePage.class;
	}

}
