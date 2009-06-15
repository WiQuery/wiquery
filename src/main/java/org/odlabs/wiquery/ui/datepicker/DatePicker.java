/*
 * Copyright (c) 2009 WiQuery team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.odlabs.wiquery.ui.datepicker;

import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

/**
 * $Id$
 * <p>
 * Extends the {@link TextField} to provide a date picker.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.6
 */
@WiQueryUIPlugin
public class DatePicker<T> extends TextField<T> implements IWiQueryPlugin {

	private static final long serialVersionUID = 1L;

	private Options options;

	/**
	 * @param id
	 * @param type
	 */
	public DatePicker(String id, Class<T> type) {
		super(id, type);
		options = new Options();
	}

	/**
	 * @param id
	 * @param model
	 * @param type
	 */
	public DatePicker(String id, IModel<T> model, Class<T> type) {
		super(id, model, type);
		options = new Options();
	}

	/**
	 * @param id
	 * @param object
	 */
	public DatePicker(String id, IModel<T> object) {
		super(id, object);
		options = new Options();
	}

	/**
	 * @param id
	 */
	public DatePicker(String id) {
		super(id);
		options = new Options();
	}

	public IHeaderContributor getHeaderContribution() {
		// imports this component's needed resources
		return new IHeaderContributor() {

			private static final long serialVersionUID = -6585283714994481625L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.apache.wicket.markup.html.IHeaderContributor#renderHead(org.apache.wicket.markup.html.IHeaderResponse)
			 */
			public void renderHead(IHeaderResponse response) {
				// importing CSS theme and JavaScript needed resource to make
				// this component working properly
				response
						.renderJavascriptReference(new JavascriptResourceReference(
								DatePicker.class, "ui.datepicker.js"));
				response
						.renderJavascriptReference(new DatePickerLanguageResourceReference(
								getLocale()));
			}

		};
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(DatePicker.class,
				"ui.datepicker.js");
		wiQueryResourceManager
				.addJavaScriptResource(new DatePickerLanguageResourceReference(
						getLocale()));
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("datepicker",
				options.getJavaScriptOptions());
	}
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}

	/* options */

	/**
	 * Sets if the date's month is selectable in a drop down list or not.
	 */
	public void setChangeMonth(boolean changeMonth) {
		options.put("changeMonth", changeMonth);
	}

	/**
	 * Returns true if the date's month is selectable in a drop down list,
	 * returns false otherwise.
	 */
	public boolean isChangeMonth() {
		return options.getBoolean("changeMonth");
	}

	/**
	 * Sets the selectable year range. This range can either be defined by a
	 * start year and an end year (like 2001 to 2010), or it can be defined
	 * relatively to the today's date (like current-10 to current+10).
	 * <p>
	 * Example to use a relatvie range:
	 * <code>aDatPickerInstance.setYearRange(-10, 10);</code>
	 * 
	 * @param yearFrom
	 *            the range's start
	 * @param yearTo
	 *            the range's end
	 */
	public void setYearRange(short yearFrom, short yearTo) {
		if (yearFrom > yearTo) {
			throw new IllegalArgumentException("Invalid year range");
		}
		options.putLiteral("yearRange", yearFrom + ":" + yearTo);
	}

	/**
	 * Sets if the date's year is selectable in a drop down list or not.
	 */
	public void setChangeYear(boolean changeYear) {
		options.put("changeYear", changeYear);
	}

	/**
	 * Returns true if the date's year is selectable in a drop down list,
	 * returns false otherwise.
	 */
	public boolean getChangeYear() {
		return options.getBoolean("changeYear");
	}

	/**
	 * Sets the calendar's starting day. 0 is for sunday, 1 for monday ...
	 * 
	 * @param firstDay
	 */
	public void setFirstDay(short firstDay) {
		options.put("firstDay", firstDay);
	}

	/**
	 * Returns the calendar's starting day.
	 */
	public short getFirstDay() {
		return options.getShort("firstDay");
	}

	/**
	 * Sets if the calendar's first day can be changed by the user or not.
	 */
	public void setChangeFirstDay(boolean changeFirstDay) {
		options.put("changeFirstDay", changeFirstDay);
	}

	/**
	 * Returns if the calendar's first day can be changed by the user or not.
	 */
	public boolean getChangeFirstDay() {
		return options.getBoolean("changeFirstDay");
	}

	/**
	 * Sets if the week is highlighted when the user's mouse is over a day.
	 */
	public void setHighlightWeek(boolean highlightWeek) {
		options.put("highlightWeek", highlightWeek);
	}

	/**
	 * Returns if the week is highlighted when the user's mouse is over a day.
	 */
	public boolean getHighlightWeek() {
		return options.getBoolean("highlightWeek");
	}

	/**
	 * Sets if the next/previous months are showed in the calendar.
	 */
	public void setShowOtherMonths(boolean showOtherMonths) {
		options.put("showOtherMonths", showOtherMonths);
	}

	/**
	 * Returns if the next/previous months are showed in the calendar.
	 */
	public boolean getShowOtherMonths() {
		return options.getBoolean("showOtherMonths");
	}

	/**
	 * Sets if the week names are showed in the calendar.
	 */
	public void setShowWeeks(boolean showWeeks) {
		options.put("showWeeks", showWeeks);
	}

	/**
	 * Returns true if the week names are showed in the calendar, false
	 * otherwise.
	 */
	public boolean getShowWeeks() {
		return options.getBoolean("showWeeks");
	}

	/**
	 * Sets the number of months displayed on the date picker.
	 */
	public void setNumberOfMonths(short numberOfMonths) {
		options.put("numberOfMonths", numberOfMonths);
	}

	/**
	 * Returns the number of months displayed on the date picker.
	 */
	public short getNumberOfMonths() {
		return options.getShort("numberOfMonths");
	}

	/**
	 * Sets the number of months stepped when the next/previous button are hit.
	 */
	public void setStepMonths(short stepMonths) {
		options.put("stepMonths", stepMonths);
	}

	/**
	 * Returns the number of months stepped when the next/previous button are
	 * hit.
	 */
	public short getStepMonths() {
		return options.getShort("stepMonths");
	}

	/**
	 * Sets if a date range can be selected.
	 */
	public void setRangeSelect(boolean rangeSelect) {
		options.put("rangeSelect", rangeSelect);
	}

	/**
	 * Returns true if a date range can be selected.
	 */
	public boolean getRangeSelect() {
		return options.getBoolean("rangeSelect");
	}

	/**
	 * Sets the date range separator.
	 */
	public void setRangeSeparator(String rangeSeparator) {
		options.put("rangeSeparator", rangeSeparator);
	}

	/**
	 * Returns the date range separator.
	 */
	public String getRangeSeparator() {
		return options.get("rangeSeparator");
	}

	/**
	 * Sets the ISO date format to use.
	 */
	public void setDateFormat(String dateFormat) {
		options.putLiteral("dateFormat", dateFormat);
	}

	/**
	 * Returns the ISO date format to use.
	 */
	public String getDateFormat() {
		return options.getLiteral("dateFormat");
	}

	/* Status bar options */

	/**
	 * Sets the initial message put in the calendar's status bar.
	 */
	public void setInitStatus(String initStatus) {
		options.putLiteral("initStatus", initStatus);
	}

	/**
	 * Returns the initial message put in the calendar's status bar.
	 */
	public String getInitStatus() {
		return options.getLiteral("initStatus");
	}

	/**
	 * Sets if the status bar is shown or not.
	 */
	public void setShowStatus(boolean showStatus) {
		options.put("showStatus", showStatus);
	}

	/**
	 * Returns true if the status bar is shown or not, false otherwise.
	 */
	public boolean getShowStatus() {
		return options.getBoolean("showStatus");
	}

}
