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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.InputTestPanel;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.datepicker.DatePicker.ShowOnEnum;
import org.odlabs.wiquery.ui.datepicker.DatePickerDuration.DurationEnum;
import org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerDateTextEvent;
import org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerEvent;
import org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerOnChangeEvent;

/**
 * Test on {@link DatePicker}
 * 
 * @author Julien Roche
 */
public class DatePickerTestCase extends WiQueryTestCase {
	// Properties
	private DatePicker<Date> datePicker;

	@Override
	@Before
	public void setUp() {
		super.setUp();

		tester.startPanel(new ITestPanelSource() {
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(String panelId) {
				Panel panel = new InputTestPanel(panelId);
				datePicker = new DatePicker<Date>("anId");
				datePicker.setMarkupId(datePicker.getId());
				panel.add(datePicker);
				return panel;
			}
		});
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#destroy()}.
	 */
	@Test
	public void testDestroy() {
		assertNotNull(datePicker.destroy());
		assertEquals(datePicker.destroy().render().toString(),
				"$('#anId').datepicker('destroy');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#disable()}.
	 */
	@Test
	public void testDisable() {
		assertNotNull(datePicker.disable());
		assertEquals(datePicker.disable().render().toString(),
				"$('#anId').datepicker('disable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#enable()}.
	 */
	@Test
	public void testEnable() {
		assertNotNull(datePicker.enable());
		assertEquals(datePicker.enable().render().toString(),
				"$('#anId').datepicker('enable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getAltField()}.
	 */
	@Test
	public void testGetAltField() {
		assertEquals(datePicker.getAltField(), "");
		datePicker.setAltField("a label");
		assertEquals(datePicker.getAltField(), "a label");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getAltFormat()}.
	 */
	@Test
	public void testGetAltFormat() {
		assertEquals(datePicker.getAltFormat(), "");
		datePicker.setAltFormat("a label");
		assertEquals(datePicker.getAltFormat(), "a label");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getAppendText()}.
	 */
	@Test
	public void testGetAppendText() {
		assertEquals(datePicker.getAppendText(), "");
		datePicker.setAppendText("a label");
		assertEquals(datePicker.getAppendText(), "a label");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getButtonImage()}.
	 */
	@Test
	public void testGetButtonImage() {
		assertEquals(datePicker.getButtonImage(), "");
		datePicker.setButtonImage("a label");
		assertEquals(datePicker.getButtonImage(), "a label");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getButtonText()}.
	 */
	@Test
	public void testGetButtonText() {
		assertEquals(datePicker.getButtonText(), "...");
		datePicker.setButtonText("a label");
		assertEquals(datePicker.getButtonText(), "a label");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getCloseText()}.
	 */
	@Test
	public void testGetCloseText() {
		assertEquals(datePicker.getCloseText(), "Done");
		datePicker.setCloseText("a label");
		assertEquals(datePicker.getCloseText(), "a label");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getCurrentText()}.
	 */
	@Test
	public void testGetCurrentText() {
		assertEquals(datePicker.getCurrentText(), "Today");
		datePicker.setCurrentText("a label");
		assertEquals(datePicker.getCurrentText(), "a label");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDate()}.
	 */
	@Test
	public void testGetDate() {
		assertNotNull(datePicker.getDate());
		assertEquals(datePicker.getDate().render().toString(),
				"$('#anId').datepicker('getDate');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDateFormat()}.
	 */
	@Test
	public void testGetDateFormat() {
		assertEquals(datePicker.getDateFormat(), "mm/dd/yy");
		datePicker.setDateFormat("dd/mm/yyyy");
		assertEquals(datePicker.getDateFormat(), "dd/mm/yyyy");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDayNames()}.
	 */
	@Test
	public void testGetDayNames() {
		assertNotNull(datePicker.getDayNames());
		assertEquals(datePicker.getDayNames().getJavascriptOption().toString(),
				"['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday']");
		datePicker.setDayNames(new ArrayOfDayNames("Su", "Mo", "Tu", "We",
				"Th", "Fr", "Sa"));
		assertEquals(datePicker.getDayNames().getJavascriptOption().toString(),
				"['Su','Mo','Tu','We','Th','Fr','Sa']");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDayNamesMin()}.
	 */
	@Test
	public void testGetDayNamesMin() {
		assertNotNull(datePicker.getDayNamesMin());
		assertEquals(datePicker.getDayNamesMin().getJavascriptOption()
				.toString(), "['Su','Mo','Tu','We','Th','Fr','Sa']");
		datePicker.setDayNamesMin(new ArrayOfDayNames("Sun", "Mon", "Tue",
				"Wed", "Thu", "Fri", "Sat"));
		assertEquals(datePicker.getDayNamesMin().getJavascriptOption()
				.toString(), "['Sun','Mon','Tue','Wed','Thu','Fri','Sat']");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDayNamesShort()}.
	 */
	@Test
	public void testGetDayNamesShort() {
		assertNotNull(datePicker.getDayNamesShort());
		assertEquals(datePicker.getDayNamesShort().getJavascriptOption()
				.toString(), "['Sun','Mon','Tue','Wed','Thu','Fri','Sat']");
		datePicker.setDayNamesShort(new ArrayOfDayNames("Su", "Mo", "Tu", "We",
				"Th", "Fr", "Sa"));
		assertEquals(datePicker.getDayNamesShort().getJavascriptOption()
				.toString(), "['Su','Mo','Tu','We','Th','Fr','Sa']");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDefaultDate()}.
	 */
	@Test
	public void testGetDefaultDate() {
		assertNull(datePicker.getDefaultDate());
		datePicker.setDefaultDate(new DateOption(new GregorianCalendar(2009,
				11, 1).getTime()));
		assertNotNull(datePicker.getDefaultDate());
		assertEquals(datePicker.getDefaultDate().getJavascriptOption()
				.toString(), "new Date(2009,11,1,0,0,0,0)");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getDuration()}.
	 */
	@Test
	public void testGetDuration() {
		assertNotNull(datePicker.getDuration());
		assertEquals(datePicker.getDuration().getJavascriptOption().toString(),
				"'normal'");
		datePicker.setDuration(new DatePickerDuration(DurationEnum.FAST));
		assertEquals(datePicker.getDuration().getJavascriptOption().toString(),
				"'fast'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getFirstDay()}.
	 */
	@Test
	public void testGetFirstDay() {
		assertEquals(datePicker.getFirstDay(), 0);
		datePicker.setFirstDay(new Short("1"));
		assertEquals(datePicker.getFirstDay(), 1);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getMaxDate()}.
	 */
	@Test
	public void testGetMaxDate() {
		assertNull(datePicker.getMaxDate());
		datePicker.setMaxDate(new DateOption(new GregorianCalendar(2009, 11, 1)
				.getTime()));
		assertNotNull(datePicker.getMaxDate());
		assertEquals(datePicker.getMaxDate().getJavascriptOption().toString(),
				"new Date(2009,11,1,0,0,0,0)");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getMinDate()}.
	 */
	@Test
	public void testGetMinDate() {
		assertNull(datePicker.getMinDate());
		datePicker.setMinDate(new DateOption(new GregorianCalendar(2009, 11, 1)
				.getTime()));
		assertNotNull(datePicker.getMinDate());
		assertEquals(datePicker.getMinDate().getJavascriptOption().toString(),
				"new Date(2009,11,1,0,0,0,0)");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getMonthNames()}.
	 */
	@Test
	public void testGetMonthNames() {
		assertNotNull(datePicker.getMonthNames());
		assertEquals(
				datePicker.getMonthNames().getJavascriptOption().toString(),
				"['January','February','March','April','May','June','July','August','September','October','November','December']");
		datePicker.setMonthNames(new ArrayOfMonthNames("Jan", "Feb", "Mar",
				"Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
		assertEquals(datePicker.getMonthNames().getJavascriptOption()
				.toString(),
				"['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec']");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getMonthNamesShort()}.
	 */
	@Test
	public void testGetMonthNamesShort() {
		assertNotNull(datePicker.getMonthNamesShort());
		assertEquals(datePicker.getMonthNamesShort().getJavascriptOption()
				.toString(),
				"['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec']");
		datePicker.setMonthNamesShort(new ArrayOfMonthNames("Ja", "Fe", "Ma",
				"Ap", "Ma", "Ju", "Ju", "Au", "Se", "Oc", "No", "De"));
		assertEquals(datePicker.getMonthNamesShort().getJavascriptOption()
				.toString(),
				"['Ja','Fe','Ma','Ap','Ma','Ju','Ju','Au','Se','Oc','No','De']");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getNextText()}.
	 */
	@Test
	public void testGetNextText() {
		assertEquals(datePicker.getNextText(), "Next");
		datePicker.setNextText("a label");
		assertEquals(datePicker.getNextText(), "a label");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getNumberOfMonths()}.
	 */
	@Test
	public void testGetNumberOfMonths() {
		assertNotNull(datePicker.getNumberOfMonths());
		assertEquals(datePicker.getNumberOfMonths().getJavascriptOption()
				.toString(), "1");
		datePicker.setNumberOfMonths(new DatePickerNumberOfMonths(
				new Short("2")));
		assertEquals(datePicker.getNumberOfMonths().getJavascriptOption()
				.toString(), "2");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		assertNotNull(datePicker.getOptions());
		assertEquals(datePicker.getOptions().getJavaScriptOptions().toString(),
				"{}");
		datePicker.setAltField("alt label");
		assertEquals(datePicker.getOptions().getJavaScriptOptions().toString(),
				"{altField: 'alt label'}");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getPrevText()}.
	 */
	@Test
	public void testGetPrevText() {
		assertEquals(datePicker.getPrevText(), "Prev");
		datePicker.setPrevText("a label");
		assertEquals(datePicker.getPrevText(), "a label");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getShortYearCutoff()}.
	 */
	@Test
	public void testGetShortYearCutoff() {
		assertNotNull(datePicker.getShortYearCutoff());
		assertEquals(datePicker.getShortYearCutoff().getJavascriptOption()
				.toString(), "'+10'");
		datePicker.setShortYearCutoff(new DatePickerShortYearCutOff("+100"));
		assertEquals(datePicker.getShortYearCutoff().getJavascriptOption()
				.toString(), "'+100'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getShowAnim()}.
	 */
	@Test
	public void testGetShowAnim() {
		assertEquals(datePicker.getShowAnim(), "show");
		datePicker.setShowAnim("slideToggle");
		assertEquals(datePicker.getShowAnim(), "slideToggle");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getShowCurrentAtPos()}
	 * .
	 */
	@Test
	public void testGetShowCurrentAtPos() {
		assertEquals(datePicker.getShowCurrentAtPos(), 0);
		datePicker.setShowCurrentAtPos(new Short("1"));
		assertEquals(datePicker.getShowCurrentAtPos(), 1);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getShowOn()}.
	 */
	@Test
	public void testGetShowOn() {
		assertEquals(datePicker.getShowOn(), ShowOnEnum.FOCUS);
		datePicker.setShowOn(ShowOnEnum.BOTH);
		assertEquals(datePicker.getShowOn(), ShowOnEnum.BOTH);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getShowOptions()}.
	 */
	@Test
	public void testGetShowOptions() {
		assertNull(datePicker.getShowOptions());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getStepMonths()}.
	 */
	@Test
	public void testGetStepMonths() {
		assertEquals(datePicker.getStepMonths(), 1);
		datePicker.setStepMonths(new Short("5"));
		assertEquals(datePicker.getStepMonths(), 5);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getWeekHeader()}.
	 */
	@Test
	public void testGetWeekHeader() {
		assertEquals(datePicker.getWeekHeader(), "WK");
		datePicker.setWeekHeader("W");
		assertEquals(datePicker.getWeekHeader(), "W");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getYearSuffix()}.
	 */
	@Test
	public void testGetYearRange() {
		assertNotNull(datePicker.getYearRange());
		assertEquals(
				datePicker.getYearRange().getJavascriptOption().toString(),
				"'c-10:c+10'");
		datePicker.setYearRange(new DatePickerYearRange(new Short("-10"),
				new Short("10")));
		assertNotNull(datePicker.getYearRange());
		assertEquals(
				datePicker.getYearRange().getJavascriptOption().toString(),
				"'-10:10'");
		datePicker.setYearRange(new DatePickerYearRange(new Short("-20"),
				new Short("20")));
		assertEquals(
				datePicker.getYearRange().getJavascriptOption().toString(),
				"'-20:20'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#getYearSuffix()}.
	 */
	@Test
	public void testGetYearSuffix() {
		assertEquals(datePicker.getYearSuffix(), "");
		datePicker.setYearSuffix("CE");
		assertEquals(datePicker.getYearSuffix(), "CE");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#hide()}.
	 */
	@Test
	public void testHide() {
		assertNotNull(datePicker.hide());
		assertEquals(datePicker.hide().render().toString(),
				"$('#anId').datepicker('hide');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#hide(short)}.
	 */
	@Test
	public void testHideShort() {
		assertNotNull(datePicker.hide(new Short("5")));
		assertEquals(datePicker.hide(new Short("5")).render().toString(),
				"$('#anId').datepicker('hide', 5);");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isAutoSize()}.
	 */
	@Test
	public void testIsAutoSize() {
		assertFalse(datePicker.isAutoSize());
		datePicker.setAutoSize(true);
		assertTrue(datePicker.isAutoSize());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isButtonImageOnly()}.
	 */
	@Test
	public void testIsButtonImageOnly() {
		assertFalse(datePicker.isButtonImageOnly());
		datePicker.setButtonImageOnly(true);
		assertTrue(datePicker.isButtonImageOnly());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isChangeMonth()}.
	 */
	@Test
	public void testIsChangeMonth() {
		assertFalse(datePicker.isChangeMonth());
		datePicker.setChangeMonth(true);
		assertTrue(datePicker.isChangeMonth());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isChangeYear()}.
	 */
	@Test
	public void testIsChangeYear() {
		assertFalse(datePicker.isChangeYear());
		datePicker.setChangeYear(true);
		assertTrue(datePicker.isChangeYear());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isConstrainInput()}.
	 */
	@Test
	public void testIsConstrainInput() {
		assertTrue(datePicker.isConstrainInput());
		datePicker.setConstrainInput(false);
		assertFalse(datePicker.isConstrainInput());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		assertFalse(datePicker.isDisabled());
		datePicker.setDisabled(true);
		assertTrue(datePicker.isDisabled());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isGotoCurrent()}.
	 */
	@Test
	public void testIsGotoCurrent() {
		assertFalse(datePicker.isGotoCurrent());
		datePicker.setGotoCurrent(true);
		assertTrue(datePicker.isGotoCurrent());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isHideIfNoPrevNext()}.
	 */
	@Test
	public void testIsHideIfNoPrevNext() {
		assertFalse(datePicker.isHideIfNoPrevNext());
		datePicker.setHideIfNoPrevNext(true);
		assertTrue(datePicker.isHideIfNoPrevNext());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isIsRTL()}.
	 */
	@Test
	public void testIsIsRTL() {
		assertFalse(datePicker.isIsRTL());
		datePicker.setIsRTL(true);
		assertTrue(datePicker.isIsRTL());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isNavigationAsDateFormat()}
	 * .
	 */
	@Test
	public void testIsNavigationAsDateFormat() {
		assertFalse(datePicker.isNavigationAsDateFormat());
		datePicker.setNavigationAsDateFormat(true);
		assertTrue(datePicker.isNavigationAsDateFormat());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isSelectOtherMonths()}
	 * .
	 */
	@Test
	public void testIsSelectOtherMonths() {
		assertFalse(datePicker.isSelectOtherMonths());
		datePicker.setSelectOtherMonths(true);
		assertTrue(datePicker.isSelectOtherMonths());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isShowButtonPanel()}.
	 */
	@Test
	public void testIsShowButtonPanel() {
		assertFalse(datePicker.isShowButtonPanel());
		datePicker.setShowButtonPanel(true);
		assertTrue(datePicker.isShowButtonPanel());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isShowMonthAfterYear()}
	 * .
	 */
	@Test
	public void testIsShowMonthAfterYear() {
		assertFalse(datePicker.isShowMonthAfterYear());
		datePicker.setShowMonthAfterYear(true);
		assertTrue(datePicker.isShowMonthAfterYear());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isShowOtherMonths()}.
	 */
	@Test
	public void testIsShowOtherMonths() {
		assertFalse(datePicker.isShowOtherMonths());
		datePicker.setShowOtherMonths(true);
		assertTrue(datePicker.isShowOtherMonths());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#isShowWeek()}.
	 */
	@Test
	public void testIsShowWeek() {
		assertFalse(datePicker.isShowWeek());
		datePicker.setShowWeek(true);
		assertTrue(datePicker.isShowWeek());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#setBeforeShowDayEvent(org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerEvent)}
	 * .
	 */
	@Test
	public void testSetBeforeShowDayEvent() {
		assertEquals(datePicker.statement().render().toString(),
				"$('#anId').datepicker({});");
		datePicker.setBeforeShowDayEvent(JsScopeUiDatePickerEvent
				.quickScope("alert('event');"));
		assertEquals(
				datePicker.statement().render().toString(),
				"$('#anId').datepicker({beforeShowDay: function(date) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#setBeforeShowEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetBeforeShowEvent() {
		assertEquals(datePicker.statement().render().toString(),
				"$('#anId').datepicker({});");
		datePicker.setBeforeShowEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(
				datePicker.statement().render().toString(),
				"$('#anId').datepicker({beforeShow: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#setDate(org.odlabs.wiquery.ui.datepicker.DateOption)}
	 * .
	 */
	@Test
	public void testSetDateDateOption() {
		assertNotNull(datePicker.setDate(new DateOption(new GregorianCalendar(
				2009, 11, 1).getTime())));
		assertEquals(
				datePicker
						.setDate(
								new DateOption(new GregorianCalendar(2009, 11,
										1).getTime())).render().toString(),
				"$('#anId').datepicker('setDate', new Date(2009,11,1,0,0,0,0));");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#setOnChangeMonthYearEvent(org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerOnChangeEvent)}
	 * .
	 */
	@Test
	public void testSetOnChangeMonthYearEvent() {
		assertEquals(datePicker.statement().render().toString(),
				"$('#anId').datepicker({});");
		datePicker.setOnChangeMonthYearEvent(JsScopeUiDatePickerOnChangeEvent
				.quickScope("alert('event');"));
		assertEquals(
				datePicker.statement().render().toString(),
				"$('#anId').datepicker({onChangeMonthYear: function(year, month, inst) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#setOnCloseEvent(org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerDateTextEvent)}
	 * .
	 */
	@Test
	public void testSetOnCloseEvent() {
		assertEquals(datePicker.statement().render().toString(),
				"$('#anId').datepicker({});");
		datePicker.setOnCloseEvent(JsScopeUiDatePickerDateTextEvent
				.quickScope("alert('event');"));
		assertEquals(
				datePicker.statement().render().toString(),
				"$('#anId').datepicker({onClose: function(dateText, inst) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#setOnSelectEvent(org.odlabs.wiquery.ui.datepicker.scope.JsScopeUiDatePickerDateTextEvent)}
	 * .
	 */
	@Test
	public void testSetOnSelectEvent() {
		assertEquals(datePicker.statement().render().toString(),
				"$('#anId').datepicker({});");
		datePicker.setOnSelectEvent(JsScopeUiDatePickerDateTextEvent
				.quickScope("alert('event');"));
		assertEquals(
				datePicker.statement().render().toString(),
				"$('#anId').datepicker({onSelect: function(dateText, inst) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#show()}.
	 */
	@Test
	public void testShow() {
		assertNotNull(datePicker.show());
		assertEquals(datePicker.show().render().toString(),
				"$('#anId').datepicker('show');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#statement()}.
	 */
	@Test
	public void testStatement() {
		assertNotNull(datePicker.statement());
		assertEquals(datePicker.statement().render().toString(),
				"$('#anId').datepicker({});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.datepicker.DatePicker#widget()}.
	 */
	@Test
	public void testWidget() {
		assertNotNull(datePicker.widget());
		assertEquals(datePicker.widget().render().toString(),
				"$('#anId').datepicker('widget');");
	}
}
