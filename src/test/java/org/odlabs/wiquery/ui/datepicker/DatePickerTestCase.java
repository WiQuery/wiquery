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

import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.datepicker.DatePicker.ShowOnEnum;
import org.odlabs.wiquery.ui.datepicker.DatePickerDuration.DurationEnum;
import org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerDateTextEvent;
import org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerEvent;
import org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerOnChangeEvent;
import org.odlabs.wiquery.utils.WiQueryWebApplication;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test on {@link DatePicker}
 * @author Julien Roche
 *
 */
public class DatePickerTestCase extends TestCase {
	// Properties
	private DatePicker<Date> datePicker;

	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
		new WicketTester(new WiQueryWebApplication() {
			@Override
			public Class<? extends Page> getHomePage() {
				return null;
			}
		});
		
		datePicker = new DatePicker<Date>("anId");
		datePicker.setMarkupId(datePicker.getId());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(datePicker.destroy());
		Assert.assertEquals(datePicker.destroy().render().toString(), 
				"$('#anId').datepicker('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(datePicker.disable());
		Assert.assertEquals(datePicker.disable().render().toString(), 
				"$('#anId').datepicker('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(datePicker.enable());
		Assert.assertEquals(datePicker.enable().render().toString(), 
				"$('#anId').datepicker('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getAltField()}.
	 */
	@Test
	public void testGetAltField() {
		Assert.assertEquals(datePicker.getAltField(), "");
		datePicker.setAltField("a label");
		Assert.assertEquals(datePicker.getAltField(), "a label");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getAltFormat()}.
	 */
	@Test
	public void testGetAltFormat() {
		Assert.assertEquals(datePicker.getAltFormat(), "");
		datePicker.setAltFormat("a label");
		Assert.assertEquals(datePicker.getAltFormat(), "a label");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getAppendText()}.
	 */
	@Test
	public void testGetAppendText() {
		Assert.assertEquals(datePicker.getAppendText(), "");
		datePicker.setAppendText("a label");
		Assert.assertEquals(datePicker.getAppendText(), "a label");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getButtonImage()}.
	 */
	@Test
	public void testGetButtonImage() {
		Assert.assertEquals(datePicker.getButtonImage(), "");
		datePicker.setButtonImage("a label");
		Assert.assertEquals(datePicker.getButtonImage(), "a label");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getButtonText()}.
	 */
	@Test
	public void testGetButtonText() {
		Assert.assertEquals(datePicker.getButtonText(), "...");
		datePicker.setButtonText("a label");
		Assert.assertEquals(datePicker.getButtonText(), "a label");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getCloseText()}.
	 */
	@Test
	public void testGetCloseText() {
		Assert.assertEquals(datePicker.getCloseText(), "Done");
		datePicker.setCloseText("a label");
		Assert.assertEquals(datePicker.getCloseText(), "a label");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getCurrentText()}.
	 */
	@Test
	public void testGetCurrentText() {
		Assert.assertEquals(datePicker.getCurrentText(), "Today");
		datePicker.setCurrentText("a label");
		Assert.assertEquals(datePicker.getCurrentText(), "a label");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDate()}.
	 */
	@Test
	public void testGetDate() {
		Assert.assertNotNull(datePicker.getDate());
		Assert.assertEquals(datePicker.getDate().render().toString(), 
				"$('#anId').datepicker('getDate');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDateFormat()}.
	 */
	@Test
	public void testGetDateFormat() {
		Assert.assertEquals(datePicker.getDateFormat(), "mm/dd/yy");
		datePicker.setDateFormat("dd/mm/yyyy");
		Assert.assertEquals(datePicker.getDateFormat(), "dd/mm/yyyy");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDayNames()}.
	 */
	@Test
	public void testGetDayNames() {
		Assert.assertNotNull(datePicker.getDayNames());
		Assert.assertEquals(datePicker.getDayNames().getJavascriptOption().toString(), 
				"['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday']");
		datePicker.setDayNames(new ArrayOfDayNames("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"));
		Assert.assertEquals(datePicker.getDayNames().getJavascriptOption().toString(), 
			"['Su','Mo','Tu','We','Th','Fr','Sa']");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDayNamesMin()}.
	 */
	@Test
	public void testGetDayNamesMin() {
		Assert.assertNotNull(datePicker.getDayNamesMin());
		Assert.assertEquals(datePicker.getDayNamesMin().getJavascriptOption().toString(), 
				"['Su','Mo','Tu','We','Th','Fr','Sa']");
		datePicker.setDayNamesMin(new ArrayOfDayNames("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"));
		Assert.assertEquals(datePicker.getDayNamesMin().getJavascriptOption().toString(), 
			"['Sun','Mon','Tue','Wed','Thu','Fri','Sat']");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDayNamesShort()}.
	 */
	@Test
	public void testGetDayNamesShort() {
		Assert.assertNotNull(datePicker.getDayNamesShort());
		Assert.assertEquals(datePicker.getDayNamesShort().getJavascriptOption().toString(), 
				"['Sun','Mon','Tue','Wed','Thu','Fri','Sat']");
		datePicker.setDayNamesShort(new ArrayOfDayNames("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"));
		Assert.assertEquals(datePicker.getDayNamesShort().getJavascriptOption().toString(), 
			"['Su','Mo','Tu','We','Th','Fr','Sa']");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDefaultDate()}.
	 */
	@Test
	public void testGetDefaultDate() {
		Assert.assertNull(datePicker.getDefaultDate());
		datePicker.setDefaultDate(new DateOption(new GregorianCalendar(2009, 11, 1).getTime()));
		Assert.assertNotNull(datePicker.getDefaultDate());
		Assert.assertEquals(datePicker.getDefaultDate().getJavascriptOption().toString(), 
				"new Date(2009,11,1,0,0,0,0)");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDuration()}.
	 */
	@Test
	public void testGetDuration() {
		Assert.assertNotNull(datePicker.getDuration());
		Assert.assertEquals(datePicker.getDuration().getJavascriptOption().toString(), "'normal'");
		datePicker.setDuration(new DatePickerDuration(DurationEnum.FAST));
		Assert.assertEquals(datePicker.getDuration().getJavascriptOption().toString(), "'fast'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getFirstDay()}.
	 */
	@Test
	public void testGetFirstDay() {
		Assert.assertEquals(datePicker.getFirstDay(), 0);
		datePicker.setFirstDay(new Short("1"));
		Assert.assertEquals(datePicker.getFirstDay(), 1);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getMaxDate()}.
	 */
	@Test
	public void testGetMaxDate() {
		Assert.assertNull(datePicker.getMaxDate());
		datePicker.setMaxDate(new DateOption(new GregorianCalendar(2009, 11, 1).getTime()));
		Assert.assertNotNull(datePicker.getMaxDate());
		Assert.assertEquals(datePicker.getMaxDate().getJavascriptOption().toString(), 
				"new Date(2009,11,1,0,0,0,0)");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getMinDate()}.
	 */
	@Test
	public void testGetMinDate() {
		Assert.assertNull(datePicker.getMinDate());
		datePicker.setMinDate(new DateOption(new GregorianCalendar(2009, 11, 1).getTime()));
		Assert.assertNotNull(datePicker.getMinDate());
		Assert.assertEquals(datePicker.getMinDate().getJavascriptOption().toString(), 
				"new Date(2009,11,1,0,0,0,0)");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getMonthNames()}.
	 */
	@Test
	public void testGetMonthNames() {
		Assert.assertNotNull(datePicker.getMonthNames());
		Assert.assertEquals(datePicker.getMonthNames().getJavascriptOption().toString(), 
				"['January','February','March','April','May','June','July','August','September','October','November','December']");
		datePicker.setMonthNames(new ArrayOfMonthNames("Jan", "Feb", "Mar", "Apr", 
				"May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
		Assert.assertEquals(datePicker.getMonthNames().getJavascriptOption().toString(), 
			"['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec']");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getMonthNamesShort()}.
	 */
	@Test
	public void testGetMonthNamesShort() {
		Assert.assertNotNull(datePicker.getMonthNamesShort());
		Assert.assertEquals(datePicker.getMonthNamesShort().getJavascriptOption().toString(), 
				"['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec']");
		datePicker.setMonthNamesShort(new ArrayOfMonthNames("Ja", "Fe", "Ma", "Ap", 
				"Ma", "Ju", "Ju", "Au", "Se", "Oc", "No", "De"));
		Assert.assertEquals(datePicker.getMonthNamesShort().getJavascriptOption().toString(), 
			"['Ja','Fe','Ma','Ap','Ma','Ju','Ju','Au','Se','Oc','No','De']");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getNextText()}.
	 */
	@Test
	public void testGetNextText() {
		Assert.assertEquals(datePicker.getNextText(), "Next");
		datePicker.setNextText("a label");
		Assert.assertEquals(datePicker.getNextText(), "a label");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getNumberOfMonths()}.
	 */
	@Test
	public void testGetNumberOfMonths() {
		Assert.assertNotNull(datePicker.getNumberOfMonths());
		Assert.assertEquals(datePicker.getNumberOfMonths().getJavascriptOption().toString(), "1");
		datePicker.setNumberOfMonths(new DatePickerNumberOfMonths(new Short("2")));
		Assert.assertEquals(datePicker.getNumberOfMonths().getJavascriptOption().toString(), "2");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(datePicker.getOptions());
		Assert.assertEquals(datePicker.getOptions().getJavaScriptOptions().toString(), 
				"{}");
		datePicker.setAltField("alt label");
		Assert.assertEquals(datePicker.getOptions().getJavaScriptOptions().toString(), 
			"{altField: 'alt label'}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getPrevText()}.
	 */
	@Test
	public void testGetPrevText() {
		Assert.assertEquals(datePicker.getPrevText(), "Prev");
		datePicker.setPrevText("a label");
		Assert.assertEquals(datePicker.getPrevText(), "a label");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getShortYearCutoff()}.
	 */
	@Test
	public void testGetShortYearCutoff() {
		Assert.assertNotNull(datePicker.getShortYearCutoff());
		Assert.assertEquals(datePicker.getShortYearCutoff().getJavascriptOption().toString(), "'+10'");
		datePicker.setShortYearCutoff(new DatePickerShortYearCutOff("+100"));
		Assert.assertEquals(datePicker.getShortYearCutoff().getJavascriptOption().toString(), "'+100'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getShowAnim()}.
	 */
	@Test
	public void testGetShowAnim() {
		Assert.assertEquals(datePicker.getShowAnim(), "show");
		datePicker.setShowAnim("slideToggle");
		Assert.assertEquals(datePicker.getShowAnim(), "slideToggle");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getShowCurrentAtPos()}.
	 */
	@Test
	public void testGetShowCurrentAtPos() {
		Assert.assertEquals(datePicker.getShowCurrentAtPos(), 0);
		datePicker.setShowCurrentAtPos(new Short("1"));
		Assert.assertEquals(datePicker.getShowCurrentAtPos(), 1);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getShowOn()}.
	 */
	@Test
	public void testGetShowOn() {
		Assert.assertEquals(datePicker.getShowOn(), ShowOnEnum.FOCUS);
		datePicker.setShowOn(ShowOnEnum.BOTH);
		Assert.assertEquals(datePicker.getShowOn(), ShowOnEnum.BOTH);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getShowOptions()}.
	 */
	@Test
	public void testGetShowOptions() {
		Assert.assertNull(datePicker.getShowOptions());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getStepMonths()}.
	 */
	@Test
	public void testGetStepMonths() {
		Assert.assertEquals(datePicker.getStepMonths(), 1);
		datePicker.setStepMonths(new Short("5"));
		Assert.assertEquals(datePicker.getStepMonths(), 5);
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getWeekHeader()}.
	 */
	@Test
	public void testGetWeekHeader() {
		Assert.assertEquals(datePicker.getWeekHeader(), "WK");
		datePicker.setWeekHeader("W");
		Assert.assertEquals(datePicker.getWeekHeader(), "W");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getYearSuffix()}.
	 */
	@Test
	public void testGetYearRange() {
		datePicker.setYearRange(new DatePickerYearRange(new Short("-10"), new Short("10")));
		Assert.assertNotNull(datePicker.getYearRange());
		Assert.assertEquals(datePicker.getYearRange().getJavascriptOption().toString(), "'-10:+10'");
		datePicker.setYearRange(new DatePickerYearRange(new Short("-20"), new Short("20")));
		Assert.assertEquals(datePicker.getYearRange().getJavascriptOption().toString(), "'-20:+20'");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getYearSuffix()}.
	 */
	@Test
	public void testGetYearSuffix() {
		Assert.assertEquals(datePicker.getYearSuffix(), "");
		datePicker.setYearSuffix("CE");
		Assert.assertEquals(datePicker.getYearSuffix(), "CE");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#hide()}.
	 */
	@Test
	public void testHide() {
		Assert.assertNotNull(datePicker.hide());
		Assert.assertEquals(datePicker.hide().render().toString(), 
				"$('#anId').datepicker('hide');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#hide(short)}.
	 */
	@Test
	public void testHideShort() {
		Assert.assertNotNull(datePicker.hide(new Short("5")));
		Assert.assertEquals(datePicker.hide(new Short("5")).render().toString(), 
				"$('#anId').datepicker('hide', 5);");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isAutoSize()}.
	 */
	@Test
	public void testIsAutoSize() {
		Assert.assertFalse(datePicker.isAutoSize());
		datePicker.setAutoSize(true);
		Assert.assertTrue(datePicker.isAutoSize());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isButtonImageOnly()}.
	 */
	@Test
	public void testIsButtonImageOnly() {
		Assert.assertFalse(datePicker.isButtonImageOnly());
		datePicker.setButtonImageOnly(true);
		Assert.assertTrue(datePicker.isButtonImageOnly());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isChangeMonth()}.
	 */
	@Test
	public void testIsChangeMonth() {
		Assert.assertFalse(datePicker.isChangeMonth());
		datePicker.setChangeMonth(true);
		Assert.assertTrue(datePicker.isChangeMonth());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isChangeYear()}.
	 */
	@Test
	public void testIsChangeYear() {
		Assert.assertFalse(datePicker.isChangeYear());
		datePicker.setChangeYear(true);
		Assert.assertTrue(datePicker.isChangeYear());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isConstrainInput()}.
	 */
	@Test
	public void testIsConstrainInput() {
		Assert.assertTrue(datePicker.isConstrainInput());
		datePicker.setConstrainInput(false);
		Assert.assertFalse(datePicker.isConstrainInput());
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		Assert.assertFalse(datePicker.isDisabled());
		datePicker.setDisabled(true);
		Assert.assertTrue(datePicker.isDisabled());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isGotoCurrent()}.
	 */
	@Test
	public void testIsGotoCurrent() {
		Assert.assertFalse(datePicker.isGotoCurrent());
		datePicker.setGotoCurrent(true);
		Assert.assertTrue(datePicker.isGotoCurrent());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isHideIfNoPrevNext()}.
	 */
	@Test
	public void testIsHideIfNoPrevNext() {
		Assert.assertFalse(datePicker.isHideIfNoPrevNext());
		datePicker.setHideIfNoPrevNext(true);
		Assert.assertTrue(datePicker.isHideIfNoPrevNext());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isIsRTL()}.
	 */
	@Test
	public void testIsIsRTL() {
		Assert.assertFalse(datePicker.isIsRTL());
		datePicker.setIsRTL(true);
		Assert.assertTrue(datePicker.isIsRTL());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isNavigationAsDateFormat()}.
	 */
	@Test
	public void testIsNavigationAsDateFormat() {
		Assert.assertFalse(datePicker.isNavigationAsDateFormat());
		datePicker.setNavigationAsDateFormat(true);
		Assert.assertTrue(datePicker.isNavigationAsDateFormat());
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isSelectOtherMonths()}.
	 */
	@Test
	public void testIsSelectOtherMonths() {
		Assert.assertFalse(datePicker.isSelectOtherMonths());
		datePicker.setSelectOtherMonths(true);
		Assert.assertTrue(datePicker.isSelectOtherMonths());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isShowButtonPanel()}.
	 */
	@Test
	public void testIsShowButtonPanel() {
		Assert.assertFalse(datePicker.isShowButtonPanel());
		datePicker.setShowButtonPanel(true);
		Assert.assertTrue(datePicker.isShowButtonPanel());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isShowMonthAfterYear()}.
	 */
	@Test
	public void testIsShowMonthAfterYear() {
		Assert.assertFalse(datePicker.isShowMonthAfterYear());
		datePicker.setShowMonthAfterYear(true);
		Assert.assertTrue(datePicker.isShowMonthAfterYear());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isShowOtherMonths()}.
	 */
	@Test
	public void testIsShowOtherMonths() {
		Assert.assertFalse(datePicker.isShowOtherMonths());
		datePicker.setShowOtherMonths(true);
		Assert.assertTrue(datePicker.isShowOtherMonths());
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isShowWeek()}.
	 */
	@Test
	public void testIsShowWeek() {
		Assert.assertFalse(datePicker.isShowWeek());
		datePicker.setShowWeek(true);
		Assert.assertTrue(datePicker.isShowWeek());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#setBeforeShowDayEvent(org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerEvent)}.
	 */
	@Test
	public void testSetBeforeShowDayEvent() {
		Assert.assertEquals(datePicker.statement().render().toString(), 
			"$('#anId').datepicker({});");
		datePicker.setBeforeShowDayEvent(JsScopeUiDatePickerEvent.quickScope("alert('event');"));
		Assert.assertEquals(datePicker.statement().render().toString(), 
			"$('#anId').datepicker({beforeShowDay: function(date) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#setBeforeShowEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetBeforeShowEvent() {
		Assert.assertEquals(datePicker.statement().render().toString(), 
			"$('#anId').datepicker({});");
		datePicker.setBeforeShowEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(datePicker.statement().render().toString(), 
			"$('#anId').datepicker({beforeShow: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#setDate(org.odlabs.wiquery.ui.datepicker.DateOption)}.
	 */
	@Test
	public void testSetDateDateOption() {
		Assert.assertNotNull(datePicker.setDate(
				new DateOption(new GregorianCalendar(2009, 11, 1).getTime())));
		Assert.assertEquals(datePicker.setDate(
				new DateOption(new GregorianCalendar(2009, 11, 1).getTime())).render().toString(), 
				"$('#anId').datepicker('setDate', new Date(2009,11,1,0,0,0,0));");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#setOnChangeMonthYearEvent(org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerOnChangeEvent)}.
	 */
	@Test
	public void testSetOnChangeMonthYearEvent() {
		Assert.assertEquals(datePicker.statement().render().toString(), 
			"$('#anId').datepicker({});");
		datePicker.setOnChangeMonthYearEvent(JsScopeUiDatePickerOnChangeEvent.quickScope("alert('event');"));
		Assert.assertEquals(datePicker.statement().render().toString(), 
			"$('#anId').datepicker({onChangeMonthYear: function(year, month, inst) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#setOnCloseEvent(org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerDateTextEvent)}.
	 */
	@Test
	public void testSetOnCloseEvent() {
		Assert.assertEquals(datePicker.statement().render().toString(), 
			"$('#anId').datepicker({});");
		datePicker.setOnCloseEvent(JsScopeUiDatePickerDateTextEvent.quickScope("alert('event');"));
		Assert.assertEquals(datePicker.statement().render().toString(), 
			"$('#anId').datepicker({onClose: function(dateText, inst) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#setOnSelectEvent(org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerDateTextEvent)}.
	 */
	@Test
	public void testSetOnSelectEvent() {
		Assert.assertEquals(datePicker.statement().render().toString(), 
			"$('#anId').datepicker({});");
		datePicker.setOnSelectEvent(JsScopeUiDatePickerDateTextEvent.quickScope("alert('event');"));
		Assert.assertEquals(datePicker.statement().render().toString(), 
			"$('#anId').datepicker({onSelect: function(dateText, inst) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#show()}.
	 */
	@Test
	public void testShow() {
		Assert.assertNotNull(datePicker.show());
		Assert.assertEquals(datePicker.show().render().toString(), 
				"$('#anId').datepicker('show');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(datePicker.statement());
		Assert.assertEquals(datePicker.statement().render().toString(), "$('#anId').datepicker({});");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.datepicker.DatePicker#widget()}.
	 */
	@Test
	public void testWidget() {
		Assert.assertNotNull(datePicker.widget());
		Assert.assertEquals(datePicker.widget().render().toString(), 
				"$('#anId').datepicker('widget');");
	}
}
