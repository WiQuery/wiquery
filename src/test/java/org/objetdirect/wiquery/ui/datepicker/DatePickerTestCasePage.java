package org.objetdirect.wiquery.ui.datepicker;

import org.objetdirect.wiquery.commons.WickextTestPage;
import org.objetdirect.wiquery.core.commons.WickextPlugin;
import org.objetdirect.wiquery.ui.datepicker.DatePicker;

public class DatePickerTestCasePage extends WickextTestPage {

	private DatePicker<String> datePicker;
	
	public DatePickerTestCasePage() {
		super();
		this.datePicker = new DatePicker<String>("datepicker");
		add(datePicker);
	}

	@Override
	public WickextPlugin getPluginToTest() {
		return datePicker;
	}

}
