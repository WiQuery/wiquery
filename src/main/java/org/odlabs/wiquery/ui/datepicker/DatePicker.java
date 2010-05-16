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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.ICollectionItemOptions;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.datepicker.DatePickerDuration.DurationEnum;
import org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerDateTextEvent;
import org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerEvent;
import org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerOnChangeEvent;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

/**
 * $Id$
 * <p>
 * Extends the {@link TextField} to provide a date picker.
 * </p>
 * 
 * <p>
 * By default, the datepicker calendar opens in a small overlay onFocus and closes automatically onBlur or when a date if selected. For an inline calendar, simply attach the datepicker to a div or span.
 * 
 * You can use keyboard shortcuts to drive the datepicker:
 * 	<ul>
 *     <li>page up/down - previous/next month</li>
 *     <li>ctrl+page up/down - previous/next year</li>
 *     <li>ctrl+home - current month or open when closed</li>
 *     <li>ctrl+left/right - previous/next day</li>
 *     <li>ctrl+up/down - previous/next week</li>
 *     <li>enter - accept the selected date</li>
 *     <li>ctrl+end - close and erase the date</li>
 *     <li>escape - close the datepicker without selection</li>
 * 	</ul>
 * </p>
 * 
 * Missing functionnalities
 * 	<ul>
 * 		<li>Method : dialog</li>
 * 	</ul>
 * 
 * @author Lionel Armanet
 * @since 0.6
 */
@WiQueryUIPlugin
public class DatePicker<T> extends TextField<T> implements IWiQueryPlugin {
	/**
	 * ShowOn option enumeration
	 * @author Julien Roche
	 *
	 */
	public enum ShowOnEnum {
		BOTH,
		BUTTON,
		FOCUS;
	}
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 2L;

	// Properties
	private Options options;

	/**Constructor
	 * @param id Markup identifiant
	 * @param type Class type
	 */
	public DatePicker(String id, Class<T> type) {
		super(id, type);
		options = new Options();
	}

	/**Constructor
	 * @param id Markup identifiant
	 * @param model Model to use
	 * @param type Class type
	 */
	public DatePicker(String id, IModel<T> model, Class<T> type) {
		super(id, model, type);
		options = new Options();
	}

	/**Constructor
	 * @param id Markup identifiant
	 * @param model Model to use
	 */
	public DatePicker(String id, IModel<T> model) {
		super(id, model);
		options = new Options();
	}

	/**Constructor
	 * @param id Markup identifiant
	 */
	public DatePicker(String id) {
		super(id);
		options = new Options();
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(DatePickerJavaScriptResourceReference.get());
		
		wiQueryResourceManager
			.addJavaScriptResource(new DatePickerLanguageResourceReference(
				getLocale())); // If locale is null or there is no translation, we will have the english version
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

	/*---- Options section ---*/
	
	/**The jQuery selector for another field that is to be updated with the 
	 * selected date from the datepicker. Use the altFormat setting below to 
	 * change the format of the date within this field. Leave as blank for no 
	 * alternate field.
	 * @param altField
	 * @return instance of the current component
	 */
	public DatePicker<T> setAltField(String altField) {
		this.options.putLiteral("altField", altField);
		return this;
	}
	
	/**
	 * @return the altField option value
	 */
	public String getAltField() {
		String altField = this.options.getLiteral("altField");
		return altField == null ? "" : altField;
	}
	
	/**The dateFormat to be used for the altField option. This allows one date 
	 * format to be shown to the user for selection purposes, while a different 
	 * format is actually sent behind the scenes.
	 * 
	 * The format can be combinations of the following:
	 * <ul>
	 * 	<li>d - day of month (no leading zero)</li>
	 * 	<li>dd - day of month (two digit)</li>
	 * 	<li>o - day of the year (no leading zeros)</li>
	 * 	<li>oo - day of the year (three digit)</li>
	 * 	<li>D - day name short</li>
	 * 	<li>DD - day name long</li>
	 * 	<li>m - month of year (no leading zero)</li>
	 * 	<li>mm - month of year (two digit)</li>
	 * 	<li>M - month name short</li>
	 * 	<li>MM - month name long</li>
	 * 	<li>y - year (two digit)</li>
	 * 	<li>yy - year (four digit)</li>
	 * 	<li>@ - Unix timestamp (ms since 01/01/1970)</li>
	 * 	<li>'...' - literal text</li>
	 * 	<li>'' - single quote</li>
	 * 	<li>anything else - literal text</li>
	 * </ul>
	 * 
	 * @param altFormat
	 * @return instance of the current component
	 */
	public DatePicker<T> setAltFormat(String altFormat) {
		this.options.putLiteral("altFormat", altFormat);
		return this;
	}
	
	/**
	 * @return the altFormat option value
	 */
	public String getAltFormat() {
		String altFormat = this.options.getLiteral("altFormat");
		return altFormat == null ? "" : altFormat;
	}
	
	/**Set's the text to display after each date field, e.g. to show the required format.
	 * @param appendText
	 * @return instance of the current component
	 */
	public DatePicker<T> setAppendText(String appendText) {
		this.options.putLiteral("appendText", appendText);
		return this;
	}
	
	/**
	 * @return the appendText option value
	 */
	public String getAppendText() {
		String appendText = this.options.getLiteral("appendText");
		return appendText == null ? "" : appendText;
	}
	
	/**
	 * Set to true to automatically resize the input field to accomodate dates 
	 * in the current dateFormat.
	 * @return instance of the current component
	 */
	public DatePicker<T> setAutoSize(boolean autoSize) {
		options.put("autoSize", autoSize);
		return this;
	}

	/**
	 * @return the autoSize option
	 */
	public boolean isAutoSize() {
		if(this.options.containsKey("autoSize")){
			return options.getBoolean("autoSize");
		}
		
		return false;
	}
	
	/**Set's URL for the popup button image. If set, button text becomes the alt 
	 * value and is not directly displayed.
	 * @param buttonImage
	 * @return instance of the current component
	 */
	public DatePicker<T> setButtonImage(String buttonImage) {
		this.options.putLiteral("buttonImage", buttonImage);
		return this;
	}
	
	/**
	 * @return the buttonImage option value
	 */
	public String getButtonImage() {
		String buttonImage = this.options.getLiteral("buttonImage");
		return buttonImage == null ? "" : buttonImage;
	}
	
	/**Set to true to place an image after the field to use as the trigger 
	 * without it appearing on a button.
	 * @param buttonImageOnly
	 * @return instance of the current component
	 */
	public DatePicker<T> setButtonImageOnly(boolean buttonImageOnly) {
		options.put("buttonImageOnly", buttonImageOnly);
		return this;
	}

	/**
	 * @return the buttonImageOnly option value
	 */
	public boolean isButtonImageOnly() {
		if(this.options.containsKey("buttonImageOnly")){
			return options.getBoolean("buttonImageOnly");
		}
		
		return false;
	}
	
	/**Set's the text to display on the trigger button. Use in conjunction with 
	 * showOn equal to 'button' or 'both'.
	 * @param buttonText
	 * @return instance of the current component
	 */
	public DatePicker<T> setButtonText(String buttonText) {
		this.options.putLiteral("buttonText", buttonText);
		return this;
	}
	
	/**
	 * @return the buttonText option value
	 */
	public String getButtonText() {
		String buttonText = this.options.getLiteral("buttonText");
		return buttonText == null ? "..." : buttonText;
	}
	
	/**A function to calculate the week of the year for a given date. The default 
	 * implementation uses the ISO 8601 definition: weeks start on a Monday; 
	 * the first week of the year contains the first Thursday of the year.
	 * 
	 * Default: $.datepicker.iso8601Week
	 * 
	 * @param calculateWeek
	 * @return instance of the current component
	 */
	public DatePicker<T> setCalculateWeek(JsScope calculateWeek) {
		this.options.put("calculateWeek", calculateWeek);
		return this;
	}

	/**
	 * Sets if the date's month is selectable in a drop down list or not.
	 * @return instance of the current component
	 */
	public DatePicker<T> setChangeMonth(boolean changeMonth) {
		options.put("changeMonth", changeMonth);
		return this;
	}

	/**
	 * Returns true if the date's month is selectable in a drop down list,
	 * returns false otherwise.
	 */
	public boolean isChangeMonth() {
		if(this.options.containsKey("changeMonth")){
			return options.getBoolean("changeMonth");
		}
		
		return false;
	}
	
	/**The text to display for the week of the year column heading. This attribute 
	 * is one of the regionalisation attributes. Use showWeek to display this column.
	 * @param weekHeader
	 * @return instance of the current component
	 */
	public DatePicker<T> setWeekHeader(String weekHeader) {
		this.options.putLiteral("weekHeader", weekHeader);
		return this;
	}
	
	/**
	 * @return the weekHeader option value
	 */
	public String getWeekHeader() {
		String weekHeader = this.options.getLiteral("weekHeader");
		return weekHeader == null ? "WK" : weekHeader;
	}

	/**
	 * Sets the selectable year range. This range can either be defined by a
	 * start year and an end year (like 2001 to 2010), or it can be defined
	 * relatively to the today's date (like current-10 to current+10).
	 * 
	 * @param yearRange
	 * @return instance of the current component
	 */
	public DatePicker<T> setYearRange(DatePickerYearRange yearRange) {
		options.put("yearRange", yearRange);
		return this;
	}
	
	/**
	 * @return the year range valu option
	 */
	public DatePickerYearRange getYearRange() {
		IComplexOption yearRange = this.options.getComplexOption("yearRange");
		if(yearRange != null && yearRange instanceof DatePickerYearRange){
			return (DatePickerYearRange)yearRange;
		}
		
		return new DatePickerYearRange(new Short("-10"), new Short("10"));
	}
	
	/**Additional text to display after the year in the month headers. This 
	 * attribute is one of the regionalisation attributes.
	 * @param yearSuffix
	 * @return instance of the current component
	 */
	public DatePicker<T> setYearSuffix(String yearSuffix) {
		this.options.putLiteral("yearSuffix", yearSuffix);
		return this;
	}
	
	/**
	 * @return the yearSuffix option value
	 */
	public String getYearSuffix() {
		String yearSuffix = this.options.getLiteral("yearSuffix");
		return yearSuffix == null ? "" : yearSuffix;
	}

	/**
	 * Sets if the date's year is selectable in a drop down list or not.
	 * @return instance of the current component
	 */
	public DatePicker<T> setChangeYear(boolean changeYear) {
		options.put("changeYear", changeYear);
		return this;
	}

	/**
	 * Returns true if the date's year is selectable in a drop down list,
	 * returns false otherwise.
	 */
	public boolean isChangeYear() {
		if(this.options.containsKey("changeYear")){
			return options.getBoolean("changeYear");
		}
		
		return false;
	}
	
	/**Set's the text to display for the close link. This attribute is one of 
	 * the regionalisation attributes. Use the showButtonPanel to display this button.
	 * @param closeText
	 * @return instance of the current component
	 */
	public DatePicker<T> setCloseText(String closeText) {
		this.options.putLiteral("closeText", closeText);
		return this;
	}
	
	/**
	 * @return the closeText option value
	 */
	public String getCloseText() {
		String closeText = this.options.getLiteral("closeText");
		return closeText == null ? "Done" : closeText;
	}
	
	/**True if the input field is constrained to the current date format.
	 * @param constrainInput
	 * @return instance of the current component
	 */
	public DatePicker<T> setConstrainInput(boolean constrainInput) {
		options.put("constrainInput", constrainInput);
		return this;
	}

	/**
	 * @return the buttonImageOnly option value
	 */
	public boolean isConstrainInput() {
		if(this.options.containsKey("constrainInput")){
			return options.getBoolean("constrainInput");
		}
		
		return true;
	}
	
	/**Set's the text to display for the current day link. This attribute is one 
	 * of the regionalisation attributes. Use the showButtonPanel to display 
	 * this button.
	 * @param currentText
	 * @return instance of the current component
	 */
	public DatePicker<T> setCurrentText(String currentText) {
		this.options.putLiteral("currentText", currentText);
		return this;
	}
	
	/**
	 * @return the currentText option value
	 */
	public String getCurrentText() {
		String currentText = this.options.getLiteral("currentText");
		return currentText == null ? "Today" : currentText;
	}

	/**
	 * Sets the calendar's starting day. 0 is for sunday, 1 for monday ...
	 * 
	 * @param firstDay
	 * @return instance of the current component
	 */
	public DatePicker<T> setFirstDay(short firstDay) {
		options.put("firstDay", firstDay);
		return this;
	}

	/**
	 * Returns the calendar's starting day.
	 */
	public short getFirstDay() {
		if(this.options.containsKey("firstDay")){
			return options.getShort("firstDay");
		}
		
		return 0;
	}
	
	
	
	/**If true, the current day link moves to the currently selected date instead of today.
	 * @param gotoCurrent
	 * @return instance of the current component
	 */
	public DatePicker<T> setGotoCurrent(boolean gotoCurrent) {
		options.put("gotoCurrent", gotoCurrent);
		return this;
	}

	/**
	 * @return the gotoCurrent option value
	 */
	public boolean isGotoCurrent() {
		if(this.options.containsKey("gotoCurrent")){
			return options.getBoolean("gotoCurrent");
		}
		
		return false;
	}
	
	/**Normally the previous and next links are disabled when not applicable 
	 * (see minDate/maxDate). You can hide them altogether by setting this 
	 * attribute to true.
	 * @param hideIfNoPrevNext
	 * @return instance of the current component
	 */
	public DatePicker<T> setHideIfNoPrevNext(boolean hideIfNoPrevNext) {
		options.put("hideIfNoPrevNext", hideIfNoPrevNext);
		return this;
	}

	/**
	 * @return the hideIfNoPrevNext option value
	 */
	public boolean isHideIfNoPrevNext() {
		if(this.options.containsKey("hideIfNoPrevNext")){
			return options.getBoolean("hideIfNoPrevNext");
		}
		
		return false;
	}
	
	/**True if the current language is drawn from right to left. This attribute 
	 * is one of the regionalisation attributes.
	 * @param isRTL
	 * @return instance of the current component
	 */
	public DatePicker<T> setIsRTL(boolean isRTL) {
		options.put("isRTL", isRTL);
		return this;
	}

	/**
	 * @return the isRTL option value
	 */
	public boolean isIsRTL() {
		if(this.options.containsKey("isRTL")){
			return options.getBoolean("isRTL");
		}
		
		return false;
	}
	
	/**Set a maximum selectable date via a Date object, or a number of days from 
	 * today (e.g. +7) or a string of values and periods ('y' for years, 'm' for 
	 * months, 'w' for weeks, 'd' for days, e.g. '+1m +1w'), or null for no limit.
	 * @param maxDate
	 * @return instance of the current component
	 */
	public DatePicker<T> setMaxDate(DateOption maxDate) {
		options.put("maxDate", maxDate);
		return this;
	}

	/**
	 * @return the maxDate option value
	 */
	public DateOption getMaxDate() {
		IComplexOption maxDate = options.getComplexOption("maxDate");
		
		if(maxDate != null && maxDate instanceof DateOption){
			return (DateOption) maxDate;
		}
		
		return null;
	}
	
	/**Set a minimum selectable date via a Date object, or a number of days from 
	 * today (e.g. +7) or a string of values and periods ('y' for years, 'm' for 
	 * months, 'w' for weeks, 'd' for days, e.g. '+1m +1w'), or null for no limit.
	 * @param minDate
	 * @return instance of the current component
	 */
	public DatePicker<T> setMinDate(DateOption minDate) {
		options.put("minDate", minDate);
		return this;
	}

	/**
	 * @return the minDate option value
	 */
	public DateOption getMinDate() {
		IComplexOption minDate = options.getComplexOption("minDate");
		
		if(minDate != null && minDate instanceof DateOption){
			return (DateOption) minDate;
		}
		
		return null;
	}
	
	/**Set's the list of full month names, as used in the month header on each 
	 * datepicker and as requested via the dateFormat setting. This attribute is 
	 * one of the regionalisation attributes.
	 * @param monthNames
	 * @return instance of the current component
	 */
	public DatePicker<T> setMonthNames(ArrayOfMonthNames monthNames) {
		options.put("monthNames", monthNames);
		return this;
	}

	/**
	 * @return the monthNames option value
	 */
	public ArrayOfMonthNames getMonthNames() {
		IComplexOption monthNames = options.getComplexOption("monthNames");
		
		if(monthNames != null && monthNames instanceof ArrayOfMonthNames){
			return (ArrayOfMonthNames) monthNames;
		}
		
		return new ArrayOfMonthNames("January", "February", "March", "April", 
				"May", "June", "July", "August", "September", "October", "November", "December");
	}
	
	/**Set's the list of abbreviated month names, for use as requested via the 
	 * dateFormat setting. This attribute is one of the regionalisation attributes.
	 * @param monthNamesShort
	 * @return instance of the current component
	 */
	public DatePicker<T> setMonthNamesShort(ArrayOfMonthNames monthNamesShort) {
		options.put("monthNamesShort", monthNamesShort);
		return this;
	}

	/**
	 * @return the monthNames option value
	 */
	public ArrayOfMonthNames getMonthNamesShort() {
		IComplexOption monthNamesShort = options.getComplexOption("monthNamesShort");
		
		if(monthNamesShort != null && monthNamesShort instanceof ArrayOfMonthNames){
			return (ArrayOfMonthNames) monthNamesShort;
		}
		
		return new ArrayOfMonthNames("Jan", "Feb", "Mar", "Apr", 
				"May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
	}
	
	/**When true the formatDate function is applied to the prevText, nextText, 
	 * and currentText values before display, allowing them to display the 
	 * target month names for example.
	 * @param navigationAsDateFormat
	 * @return instance of the current component
	 */
	public DatePicker<T> setNavigationAsDateFormat(boolean navigationAsDateFormat) {
		options.put("navigationAsDateFormat", navigationAsDateFormat);
		return this;
	}

	/**
	 * @return the navigationAsDateFormat option value
	 */
	public boolean isNavigationAsDateFormat() {
		if(this.options.containsKey("navigationAsDateFormat")){
			return options.getBoolean("navigationAsDateFormat");
		}
		
		return false;
	}
	
	/**Set's the text to display for the next month link. This attribute is one 
	 * of the regionalisation attributes. With the standard ThemeRoller styling, 
	 * this value is replaced by an icon.
	 * @param nextText
	 * @return instance of the current component
	 */
	public DatePicker<T> setNextText(String nextText) {
		this.options.putLiteral("nextText", nextText);
		return this;
	}
	
	/**
	 * @return the nextText option value
	 */
	public String getNextText() {
		String nextText = this.options.getLiteral("nextText");
		return nextText == null ? "Next" : nextText;
	}

	/**
	 * Sets if the next/previous months are showed in the calendar.
	 * @return instance of the current component
	 */
	public DatePicker<T> setShowOtherMonths(boolean showOtherMonths) {
		options.put("showOtherMonths", showOtherMonths);
		return this;
	}

	/**
	 * Returns if the next/previous months are showed in the calendar.
	 */
	public boolean isShowOtherMonths() {
		if(this.options.containsKey("showOtherMonths")){
			return options.getBoolean("showOtherMonths");
		}
		
		return false;
	}

	/**
	 * Sets the number of months displayed on the date picker.
	 * @return instance of the current component
	 */
	public DatePicker<T> setNumberOfMonths(DatePickerNumberOfMonths numberOfMonths) {
		options.put("numberOfMonths", numberOfMonths);
		return this;
	}

	/**
	 * Returns the number of months displayed on the date picker.
	 */
	public DatePickerNumberOfMonths getNumberOfMonths() {
		IComplexOption numberOfMonths = options.getComplexOption("numberOfMonths");
		
		if(numberOfMonths != null && numberOfMonths instanceof DatePickerNumberOfMonths){
			return (DatePickerNumberOfMonths) numberOfMonths;
		}
		
		return new DatePickerNumberOfMonths(new Short("1"));
	}
	
	/**Set's the text to display for the previous month link. This attribute is one 
	 * of the regionalisation attributes. With the standard ThemeRoller styling, 
	 * this value is replaced by an icon.
	 * @param prevText
	 * @return instance of the current component
	 */
	public DatePicker<T> setPrevText(String prevText) {
		this.options.putLiteral("prevText", prevText);
		return this;
	}
	
	/**
	 * @return the prevText option value
	 */
	public String getPrevText() {
		String prevText = this.options.getLiteral("prevText");
		return prevText == null ? "Prev" : prevText;
	}
	
	/**When true days in other months shown before or after the current month 
	 * are selectable. This only applies if showOtherMonths is also true.
	 * @param selectOtherMonths
	 * @return instance of the current behavior
	 */
	public DatePicker<T> setSelectOtherMonths(boolean selectOtherMonths) {
		this.options.put("selectOtherMonths", selectOtherMonths);
		return this;
	}
	
	/**
	 * @return the selectOtherMonths option
	 */
	public boolean isSelectOtherMonths() {
		if(this.options.containsKey("selectOtherMonths")){
			return this.options.getBoolean("selectOtherMonths");
		}
		
		return false;
	}
	
	/**Set the cutoff year for determining the century for a date
	 * @param shortYearCutoff
	 * @return instance of the current component
	 */
	public DatePicker<T> setShortYearCutoff(DatePickerShortYearCutOff shortYearCutoff) {
		options.put("shortYearCutoff", shortYearCutoff);
		return this;
	}

	/**
	 * Returns the shortYearCutoff option value.
	 */
	public DatePickerShortYearCutOff getShortYearCutoff() {
		IComplexOption shortYearCutoff = options.getComplexOption("shortYearCutoff");
		
		if(shortYearCutoff != null && shortYearCutoff instanceof DatePickerShortYearCutOff){
			return (DatePickerShortYearCutOff) shortYearCutoff;
		}
		
		return new DatePickerShortYearCutOff("+10");
	}
	
	/**Set the name of the animation used to show/hide the datepicker. Use 
	 * 'show' (the default), 'slideDown', 'fadeIn', or any of the show/hide 
	 * jQuery UI effects
	 * @param showAnim
	 * @return instance of the current component
	 */
	public DatePicker<T> setShowAnim(String showAnim) {
		this.options.putLiteral("showAnim", showAnim);
		return this;
	}
	
	/**
	 * @return the showAnim option value
	 */
	public String getShowAnim() {
		String showAnim = this.options.getLiteral("showAnim");
		return showAnim == null ? "show" : showAnim;
	}
	
	/**Whether to show the button panel.
	 * @param showButtonPanel
	 * @return instance of the current component
	 */
	public DatePicker<T> setShowButtonPanel(boolean showButtonPanel) {
		options.put("showButtonPanel", showButtonPanel);
		return this;
	}

	/**
	 * @return the showButtonPanel option value
	 */
	public boolean isShowButtonPanel() {
		if(this.options.containsKey("showButtonPanel")){
			return options.getBoolean("showButtonPanel");
		}
		
		return false;
	}
	
	/**Specify where in a multi-month display the current month shows, starting 
	 * from 0 at the top/left.
	 * @param showCurrentAtPos
	 * @return instance of the current component
	 */
	public DatePicker<T> setShowCurrentAtPos(short showCurrentAtPos) {
		options.put("showCurrentAtPos", showCurrentAtPos);
		return this;
	}

	/**
	 * @return the showCurrentAtPos option value
	 */
	public short getShowCurrentAtPos() {
		if(this.options.containsKey("showCurrentAtPos")){
			return options.getShort("showCurrentAtPos");
		}
		
		return 0;
	}
	
	/**Whether to show the month after the year in the header.
	 * @param showMonthAfterYear
	 * @return instance of the current component
	 */
	public DatePicker<T> setShowMonthAfterYear(boolean showMonthAfterYear) {
		options.put("showMonthAfterYear", showMonthAfterYear);
		return this;
	}

	/**
	 * @return the showMonthAfterYear option value
	 */
	public boolean isShowMonthAfterYear() {
		if(this.options.containsKey("showMonthAfterYear")){
			return options.getBoolean("showMonthAfterYear");
		}
		
		return false;
	}
	
	/**Have the datepicker appear automatically when the field receives focus 
	 * ('focus'), appear only when a button is clicked ('button'), or appear 
	 * when either event takes place ('both').
	 * @param showOn
	 * @return instance of the current component
	 */
	public DatePicker<T> setShowOn(ShowOnEnum showOn) {
		options.putLiteral("showOn", showOn.name().toLowerCase());
		return this;
	}

	/**
	 * @return the showOn option value
	 */
	public ShowOnEnum getShowOn() {
		String literal = options.getLiteral("showOn");
		return literal == null ? ShowOnEnum.FOCUS : ShowOnEnum.valueOf(literal.toUpperCase());
	}
	
	/**If using one of the jQuery UI effects for showAnim, you can provide 
	 * additional settings for that animation via this option.
	 * @param showOptions
	 * @return instance of the current component
	 */
	public DatePicker<T> setShowOptions(ListItemOptions<LiteralOption> showOptions) {
		options.put("showOptions", showOptions);
		return this;
		// TODO change this method
	}

	/**
	 * @return the showOptions option value
	 */
	@SuppressWarnings("unchecked")
	public ListItemOptions<LiteralOption> getShowOptions() {
		ICollectionItemOptions showOptions = options.getListItemOptions("showOptions");
		
		if(showOptions != null && showOptions instanceof ListItemOptions){
			return (ListItemOptions<LiteralOption>) showOptions;
		}
		
		return null;
	}
	
	/**
	 * When true a column is added to show the week of the year. The calculateWeek 
	 * option determines how the week of the year is calculated. You may also 
	 * want to change the firstDay option.
	 * @return instance of the current component
	 */
	public DatePicker<T> setShowWeek(boolean showWeek) {
		options.put("showWeek", showWeek);
		return this;
	}

	/**
	 * @return the showWeek option
	 */
	public boolean isShowWeek() {
		if(this.options.containsKey("showWeek")){
			return options.getBoolean("showWeek");
		}
		
		return false;
	}

	/**
	 * Sets the number of months stepped when the next/previous button are hit.
	 * @return instance of the current component
	 */
	public DatePicker<T> setStepMonths(short stepMonths) {
		options.put("stepMonths", stepMonths);
		return this;
	}

	/**
	 * Returns the number of months stepped when the next/previous button are
	 * hit.
	 */
	public short getStepMonths() {
		if(this.options.containsKey("stepMonths")){
			return options.getShort("stepMonths");
		}
		
		return 1;
	}

	/**
	 * Sets the ISO date format to use.
	 * 
	 *  The format can be combinations of the following:
	 * <ul>
	 * 	<li>d - day of month (no leading zero)</li>
	 * 	<li>dd - day of month (two digit)</li>
	 * 	<li>o - day of the year (no leading zeros)</li>
	 * 	<li>oo - day of the year (three digit)</li>
	 * 	<li>D - day name short</li>
	 * 	<li>DD - day name long</li>
	 * 	<li>m - month of year (no leading zero)</li>
	 * 	<li>mm - month of year (two digit)</li>
	 * 	<li>M - month name short</li>
	 * 	<li>MM - month name long</li>
	 * 	<li>y - year (two digit)</li>
	 * 	<li>yy - year (four digit)</li>
	 * 	<li>@ - Unix timestamp (ms since 01/01/1970)</li>
	 * 	<li>'...' - literal text</li>
	 * 	<li>'' - single quote</li>
	 * 	<li>anything else - literal text</li>
	 * </ul>
	 * @return instance of the current component
	 */
	public DatePicker<T> setDateFormat(String dateFormat) {
		options.putLiteral("dateFormat", dateFormat);
		return this;
	}

	/**
	 * Returns the ISO date format to use.
	 */
	public String getDateFormat() {
		String dateFormat = options.getLiteral("dateFormat");
		return dateFormat == null ? "mm/dd/yy" : dateFormat;
	}
	
	/**Set's the list of long day names, starting from Sunday, for use as 
	 * requested via the dateFormat setting. They also appear as popup hints 
	 * when hovering over the corresponding column headings. This attribute is 
	 * one of the regionalisation attributes.
	 * @param dayNames
	 * @return instance of the current component
	 */
	public DatePicker<T> setDayNames(ArrayOfDayNames dayNames) {
		options.put("dayNames", dayNames);
		return this;
	}

	/**
	 * @return the dayNames option value
	 */
	public ArrayOfDayNames getDayNames() {
		IComplexOption dayNames = options.getComplexOption("dayNames");
		
		if(dayNames != null && dayNames instanceof ArrayOfDayNames){
			return (ArrayOfDayNames) dayNames;
		}
		
		return new ArrayOfDayNames("Sunday", "Monday", "Tuesday", "Wednesday", 
				"Thursday", "Friday", "Saturday");
	}
	
	/**Set's the list of minimised day names, starting from Sunday, for use as 
	 * column headers within the datepicker. This attribute is one of the 
	 * regionalisation attributes.
	 * @param dayNamesMin
	 * @return instance of the current component
	 */
	public DatePicker<T> setDayNamesMin(ArrayOfDayNames dayNamesMin) {
		options.put("dayNamesMin", dayNamesMin);
		return this;
	}

	/**
	 * @return the dayNamesMin option value
	 */
	public ArrayOfDayNames getDayNamesMin() {
		IComplexOption dayNamesMin = options.getComplexOption("dayNamesMin");
		
		if(dayNamesMin != null && dayNamesMin instanceof ArrayOfDayNames){
			return (ArrayOfDayNames) dayNamesMin;
		}
		
		return new ArrayOfDayNames("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa");
	}
	
	/**Set's the list of abbreviated day names, starting from Sunday, for use as 
	 * requested via the dateFormat setting. This attribute is one of the 
	 * regionalisation attributes.
	 * @param dayNamesShort
	 * @return instance of the current component
	 */
	public DatePicker<T> setDayNamesShort(ArrayOfDayNames dayNamesShort) {
		options.put("dayNamesShort", dayNamesShort);
		return this;
	}

	/**
	 * @return the dayNamesShort option value
	 */
	public ArrayOfDayNames getDayNamesShort() {
		IComplexOption dayNamesShort = options.getComplexOption("dayNamesShort");
		
		if(dayNamesShort != null && dayNamesShort instanceof ArrayOfDayNames){
			return (ArrayOfDayNames) dayNamesShort;
		}
		
		return new ArrayOfDayNames("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
	}
	
	/**Set the date to highlight on first opening if the field is blank. Specify 
	 * either an actual date via a Date object, or a number of days from today 
	 * (e.g. +7) or a string of values and periods ('y' for years, 'm' for months, 
	 * 'w' for weeks, 'd' for days, e.g. '+1m +7d'), or null for today.
	 * @param defaultDate
	 * @return instance of the current component
	 */
	public DatePicker<T> setDefaultDate(DateOption defaultDate) {
		options.put("defaultDate", defaultDate);
		return this;
	}

	/**
	 * @return the defaultDate option value
	 */
	public DateOption getDefaultDate() {
		IComplexOption defaultDate = options.getComplexOption("defaultDate");
		
		if(defaultDate != null && defaultDate instanceof DateOption){
			return (DateOption) defaultDate;
		}
		
		return null;
	}
	
	/**Control the speed at which the datepicker appears, it may be a time in 
	 * milliseconds, a string representing one of the three predefined speeds 
	 * ("slow", "normal", "fast"), or '' for immediately.
	 * @param duration
	 * @return instance of the current component
	 */
	public DatePicker<T> setDuration(DatePickerDuration duration) {
		options.put("duration", duration);
		return this;
	}

	/**
	 * @return the duration option value
	 */
	public DatePickerDuration getDuration() {
		IComplexOption duration = options.getComplexOption("duration");
		
		if(duration != null && duration instanceof DatePickerDuration){
			return (DatePickerDuration) duration;
		}
		
		return new DatePickerDuration(DurationEnum.NORMAL);
	}
	
	/**Disables (true) or enables (false) the datepicker. Can be set when 
	 * initialising (first creating) the datepicker.
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public DatePicker<T> setDisabled(boolean disabled) {
		this.options.put("disabled", disabled);
		return this;
	}
	
	/**
	 * @return the disabled option
	 */
	public boolean isDisabled() {
		if(this.options.containsKey("disabled")){
			return this.options.getBoolean("disabled");
		}
		
		return false;
	}

	/*---- Events section ---*/
	
	/**Set's the callback before the datepicker is displayed.
	 * @param beforeShow
	 * @return instance of the current component
	 */
	public DatePicker<T> setBeforeShowEvent(JsScopeUiEvent beforeShow) {
		this.options.put("beforeShow", beforeShow);
		return this;
	}
	
	/**The function takes a date as a parameter and must return an array with [0] 
	 * equal to true/false indicating whether or not this date is selectable, [1] 
	 * equal to a CSS class name(s) or '' for the default presentation and [2] 
	 * an optional popup tooltip for this date. It is called for each day in 
	 * the datepicker before is it displayed.
	 * @param beforeShowDay
	 * @return instance of the current component
	 */
	public DatePicker<T> setBeforeShowDayEvent(JsScopeUiDatePickerEvent beforeShowDay) {
		this.options.put("beforeShowDay", beforeShowDay);
		return this;
	}
	
	/**Allows you to define your own event when the datepicker moves to a new 
	 * month and/or year. The function receives the selected year, month (1-12), 
	 * and the datepicker instance as parameters. this refers to the associated 
	 * input field.
	 * @param onChangeMonthYear
	 * @return instance of the current component
	 */
	public DatePicker<T> setOnChangeMonthYearEvent(JsScopeUiDatePickerOnChangeEvent onChangeMonthYear) {
		this.options.put("onChangeMonthYear", onChangeMonthYear);
		return this;
	}
	
	/**Allows you to define your own event when the datepicker is closed, whether 
	 * or not a date is selected. The function receives the selected date as text 
	 * and the datepicker instance as parameters. this refers to the associated 
	 * input field.
	 * @param onClose
	 * @return instance of the current component
	 */
	public DatePicker<T> setOnCloseEvent(JsScopeUiDatePickerDateTextEvent onClose) {
		this.options.put("onClose", onClose);
		return this;
	}
	
	/**Allows you to define your own event when the datepicker is selected. The 
	 * function receives the selected date as text and the datepicker instance 
	 * as parameters. this refers to the associated input field.
	 * @param onSelect
	 * @return instance of the current component
	 */
	public DatePicker<T> setOnSelectEvent(JsScopeUiDatePickerDateTextEvent onSelect) {
		this.options.put("onSelect", onSelect);
		return this;
	}
	
	/*---- Methods section ---*/
	/**Method to destroy the datepicker
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(this).$().chain("datepicker", "'destroy'");
	}

	/**Method to destroy the datepicker within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.destroy().render().toString());
	}
	
	/**Method to disable the datepicker
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return new JsQuery(this).$().chain("datepicker", "'disable'");
	}

	/**Method to disable the datepicker within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.disable().render().toString());
	}
	
	/**Method to enable the datepicker
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return new JsQuery(this).$().chain("datepicker", "'enable'");
	}

	/**Method to enable the datepicker within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.enable().render().toString());
	}
	
	/**Method returning the current date for the datepicker
	 * @return the associated JsStatement
	 */
	public JsStatement getDate() {
		return new JsQuery(this).$().chain("datepicker", "'getDate'");
	}
	
	/**Method to hide the datepicker
	 * @param speed The speed at which to close the date picker.
	 * @return the associated JsStatement
	 */
	public JsStatement hide(short speed) {
		return new JsQuery(this).$().chain("datepicker", "'hide'", Short.toString(speed));
	}

	/**Method to hide the datepicker within the ajax request
	 * @param ajaxRequestTarget
	 * @param speed The speed at which to close the date picker.
	 */
	public void hide(AjaxRequestTarget ajaxRequestTarget, short speed) {
		ajaxRequestTarget.appendJavascript(this.hide(speed).render().toString());
	}
	
	/**Method to hide the datepicker
	 * @return the associated JsStatement
	 */
	public JsStatement hide() {
		return new JsQuery(this).$().chain("datepicker", "'hide'");
	}

	/**Method to hide the datepicker within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void hide(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.hide().render().toString());
	}
	
	/**Method to set the date of the datepicker
	 * @param dateOption Date to set
	 * @return the associated JsStatement
	 */
	public JsStatement setDate(DateOption dateOption) {
		return new JsQuery(this).$().chain("datepicker", "'setDate'",
				dateOption.getJavascriptOption());
	}

	/**Method to set the date of the datepicker within the ajax request
	 * @param ajaxRequestTarget
	 * @param dateOption Date to set
	 */
	public void setDate(AjaxRequestTarget ajaxRequestTarget, DateOption dateOption) {
		ajaxRequestTarget.appendJavascript(this.setDate(dateOption).render().toString());
	}
	
	/**Method to show the datepicker
	 * @return the associated JsStatement
	 */
	public JsStatement show() {
		return new JsQuery(this).$().chain("datepicker", "'show'");
	}

	/**Method to show the datepicker within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void show(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.show().render().toString());
	}
	
	/**Method to returns the .ui-datepicker  element
	 * @return the associated JsStatement
	 */
	public JsStatement widget() {
		return new JsQuery(this).$().chain("datepicker", "'widget'");
	}

	/**Method to returns the .ui-datepicker element within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.widget().render().toString());
	}
}
