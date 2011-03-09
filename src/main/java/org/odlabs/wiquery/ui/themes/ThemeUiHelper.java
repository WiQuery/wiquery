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
package org.odlabs.wiquery.ui.themes;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.Model;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;

/**
 * $Id$
 * <p>
 * 	Helper to decorate your Wicket component with the theme of the jQuery UI framework
 * </p>
 * @author Julien Roche
 * @since 1.0
 */
public abstract class ThemeUiHelper {
	/**
	 * Enumeration of all possible icons from the jQuery UI theme
	 * 
	 * All possible icons are displayed here : http://jqueryui.com/themeroller/#themeGallery
	 * 
	 * @author Julien roche
	 * @deprecated use UiIcon  instead
	 */
	@Deprecated
	public enum IconEnum {
		CARAT_1_NORTH								("carat-1-n"),
		CARAT_1_NORTH_EAST							("carat-1-ne"),
		CARAT_1_EAST								("carat-1-e"),
		CARAT_1_SOUTH_EAST							("carat-1-se"),
		CARAT_1_SOUTH								("carat-1-s"),
		CARAT_1_SOUTH_WEST							("carat-1-sw"),
		CARAT_1_WEST								("carat-1-w"),
		CARAT_1_NORTH_WEST							("carat-1-nw"),
		CARAT_2_NORTH_SOUTH							("carat-2-n-s"),
		CARAT_2_EAST_WEST							("carat-2-e-w"),
		TRIANGLE_1_NORTH							("carat-1-n"),
		TRIANGLE_1_NORTH_EAST						("triangle-1-ne"),
		TRIANGLE_1_EAST								("triangle-1-e"),
		TRIANGLE_1_SOUTH_EAST						("triangle-1-se"),
		TRIANGLE_1_SOUTH							("triangle-1-s"),
		TRIANGLE_1_SOUTH_WEST						("triangle-1-sw"),
		TRIANGLE_1_WEST								("triangle-1-w"),
		TRIANGLE_1_NORTH_WEST						("triangle-1-nw"),
		TRIANGLE_2_NORTH_SOUTH						("triangle-2-n-s"),
		TRIANGLE_2_EAST_WEST						("triangle-2-e-w"),
		ARROW_1_NORTH								("arrow-1-n"),
		ARROW_1_NORTH_EAST							("arrow-1-ne"),
		ARROW_1_EAST								("arrow-1-e"),
		ARROW_1_SOUTH_EAST							("arrow-1-se"),
		ARROW_1_SOUTH								("arrow-1-s"),
		ARROW_1_SOUTH_WEST							("arrow-1-sw"),
		ARROW_1_WEST								("arrow-1-w"),
		ARROW_1_NORTH_WEST							("arrow-1-nw"),
		ARROW_2_NORTH_SOUTH							("arrow-2-n-s"),
		ARROW_2_NORTH_EAST_SOUTH_WEST				("arrow-2-ne-sw"),
		ARROW_2_EAST_WEST							("arrow-2-e-w"),
		ARROW_2_SOUTH_EAST_NORTH_WEST				("arrow-2-se-nw"),
		ARROWSTOP_1_NORTH							("arrowstop-1-n"),
		ARROWSTOP_1_EAST							("arrowstop-1-e"),
		ARROWSTOP_1_SOUTH							("arrowstop-1-s"),
		ARROWSTOP_1_WEST							("arrowstop-1-w"),
		ARROWTHICK_1_NORTH							("arrowthick-1-n"),
		ARROWTHICK_1_NORTH_EAST						("arrowthick-1-ne"),
		ARROWTHICK_1_EAST							("arrowthick-1-e"),
		ARROWTHICK_1_SOUTH_EAST						("arrowthick-1-se"),
		ARROWTHICK_1_SOUTH							("arrowthick-1-s"),
		ARROWTHICK_1_SOUTH_WEST						("arrowthick-1-sw"),
		ARROWTHICK_1_WEST							("arrowthick-1-w"),
		ARROWTHICK_1_NORTH_WEST						("arrowthick-1-nw"),
		ARROWTHICK_2_NORTH_SOUTH					("arrowthick-2-n-s"),
		ARROWTHICK_2_NORTH_EAST_SOUTH_WEST			("arrowthick-2-ne-sw"),
		ARROWTHICK_2_EAST_WEST						("arrowthick-2-e-w"),
		ARROWTHICK_2_SOUTH_EAST_NORTH_WEST			("arrowthick-2-se-nw"),
		ARROWTHICKSTOP_1_NORTH						("arrowthickstop-1-n"),
		ARROWTHICKSTOP_1_EAST						("arrowthickstop-1-e"),
		ARROWTHICKSTOP_1_SOUTH						("arrowthickstop-1-s"),
		ARROWTHICKSTOP_1_WEST						("arrowthickstop-1-w"),
		ARROWRETURNTHICK_1_NORTH					("arrowreturnthick-1-n"),
		ARROWRETURNTHICK_1_EAST						("arrowreturnthick-1-e"),
		ARROWRETURNTHICK_1_SOUTH					("arrowreturnthick-1-s"),
		ARROWRETURNTHICK_1_WEST						("arrowreturnthick-1-w"),
		ARROWRETURN_1_NORTH							("arrowreturn-1-n"),
		ARROWRETURN_1_EAST							("arrowreturn-1-e"),
		ARROWRETURN_1_SOUTH							("arrowreturn-1-s"),
		ARROWRETURN_1_WEST							("arrowreturn-1-w"),
		ARROWREFRESH_1_NORTH						("arrowrefresh-1-n"),
		ARROWREFRESH_1_EAST							("arrowrefresh-1-e"),
		ARROWREFRESH_1_SOUTH						("arrowrefresh-1-s"),
		ARROWREFRESH_1_WEST							("arrowrefresh-1-w"),
		ARROW_4										("arrow-4"),
		ARROW_4_DIAG								("arrow-4-diag"),
		EXTLINK										("extlink"),
		NEWWIN										("newwin"),
		REFRESH										("refresh"),
		SHUFFLE										("shuffle"),
		TRANSFER_E_W								("transfer-e-w"),
		TRANSFERTHICK_E_W							("transferthick-e-w"),
		FOLDER_COLLAPSED							("folder-collapsed"),
		FOLDER_OPEN									("folder-open"),
		DOCUMENT									("document"),
		DOCUMENT_B									("document-b"),
		NOTE										("note"),
		MAIL_CLOSED									("mail-closed"),
		MAIL_OPEN									("mail-open"),
		SUITCASE									("suitcase"),
		COMMENT										("comment"),
		PERSON										("person"),
		PRINT										("print"),
		TRASH										("trash"),
		LOCKED										("locked"),
		UNLOCKED									("unlocked"),
		BOOKMARK									("bookmark"),
		TAG											("tag"),
		HOME										("home"),
		FLAG										("flag"),
		CALENDAR									("calendar"),
		CART										("cart"),
		PENCIL										("pencil"),
		CLOCK										("clock"),
		DISK										("disk"),
		CALCULATOR									("calculator"),
		ZOOMIN										("zoomin"),
		ZOOMOUT										("zoomout"),
		SEARCH										("search"),
		WRENCH										("wrench"),
		GEAR										("gear"),
		HEART										("heart"),
		STAR										("star"),
		LINK										("link"),
		CANCEL										("cancel"),
		PLUS										("plus"),
		PLUSTHICK									("plusthick"),
		MINUS										("minus"),
		MINUSTHICK									("minusthick"),
		CLOSE										("close"),
		CLOSETHICK									("closethick"),
		KEY											("key"),
		LIGHTBULB									("lightbulb"),
		SCISSORS									("scissors"),
		CLIPBOARD									("clipboard"),
		COPY										("copy"),
		CONTACT										("contact"),
		IMAGE										("image"),
		VIDEO										("video"),
		SCRIPT										("script"),
		ALERT										("alert"),
		NOTICE										("notice"),
		HELP										("help"),
		CHECK										("check"),
		BULLET										("bullet"),
		RADIO_OFF									("radio-off"),
		RADIO_ON									("radio-on"),
		PIN_WEST									("pin-w"),
		PIN_SOUTH									("pin-s"),
		PLAY										("play"),
		PAUSE										("pause"),
		SEEK_NEXT									("seek-next"),
		SEEK_PREV									("seek-prev"),
		SEEK_END									("seek-end"),
		SEEK_FIRST									("seek-first"),
		STOP										("stop"),
		EJECT										("eject"),
		VOLUME_OFF									("volume-off"),
		VOLUME_ON									("volume-on"),
		POWER										("power"),
		SIGNAL_DIAG									("signal-diag"),
		SIGNAL										("signal"),
		BATTERY_0									("battery-0"),
		BATTERY_1									("battery-1"),
		BATTERY_2									("battery-2"),
		BATTERY_3									("battery-3"),
		CIRCLE_PLUS									("circle-plus"),
		CIRCLE_MINUS								("circle-minus"),
		CIRCLE_CLOSE								("circle-close"),
		CIRCLE_ZOOMIN								("circle-zoomin"),
		CIRCLE_ZOOMOUT								("circle-zoomout"),
		CIRCLE_CHECK								("circle-check"),
		CIRCLE_TRIANGLE_NORTH						("circle-triangle-n"),
		CIRCLE_TRIANGLE_EAST						("circle-triangle-e"),
		CIRCLE_TRIANGLE_SOUTH						("circle-triangle-s"),
		CIRCLE_TRIANGLE_WEST						("circle-triangle-w"),
		CIRCLE_ARROW_NORTH							("circle-arrow-n"),
		CIRCLE_ARROW_EAST							("circle-arrow-e"),
		CIRCLE_ARROW_SOUTH							("circle-arrow-s"),
		CIRCLE_ARROW_WEST							("circle-arrow-w"),
		CIRCLESMALL_PLUS							("circlesmall-plus"),
		CIRCLESMALL_MINUS							("circlesmall-minus"),
		CIRCLESMALL_CLOSE							("circlesmall-close"),
		SQUARESMALL_PLUS							("squaresmall-plus"),
		SQUARESMALL_MINUS							("squaresmall-minus"),
		SQUARESMALL_CLOSE							("squaresmall-close"),
		GRIP_DOTTED_VERTICAL						("grip-dotted-vertical"),
		GRIP_DOTTED_HORIZONTAL						("grip-dotted-horizontal"),
		GRIP_SOLID_VERTICAL							("grip-solid-vertical"),
		GRIP_SOLID_HORIZONTAL						("grip-solid-horizontal"),
		GRIPSMALL_DIAGONAL_SE						("gripsmall-diagonal-se"),
		GRIP_DIAGONAL_SE							("grip-diagonal-se"),
		INFO										("info");
		
		//--------
		// Properties
		private String cssClass;
		
		/**
		 * Constructor
		 * @param cssClass Associated CSS class
		 */
		IconEnum(String cssClass){
			this.cssClass = cssClass;
		}
		
		/**
		 * @return the associated CSS class
		 */
		public String getCssClass() {
			return "ui-icon-" + cssClass;
		}
	}
	
	/**
	 * Method to display into your component an error text
	 * @param component Wicket component
	 */
	public static void errorText(Component component) {
		component.add(
				new AttributeAppender(
						"class", 
						true, 
						new Model<String>("ui-state-error ui-corner-all"), " "));
	}
	
	/**
	 * Method to decorate your button
	 * @param component Wicket component
	 */
	public static void buttonRounded(Component component) {
		component.add(
				new AttributeAppender(
						"class", 
						true, 
						new Model<String>("ui-state-default ui-corner-all"), " "));
	}
	
	/**
	 * Method to decorate your button
	 * @param component Wicket component
	 */
	public static void buttonRoundedFocused(Component component) {
		component.add(
				new AttributeAppender(
						"class", 
						true, 
						new Model<String>("ui-state-default ui-corner-all ui-state-focus"), " "));
	}
	
	/**
	 * Method to decorate your container (div, span ...)
	 * @param component Wicket component
	 */
	public static void componentRounded(Component component) {
		component.add(
				new AttributeAppender(
						"class", 
						true, 
						new Model<String>("ui-widget ui-widget-content ui-corner-all"), " "));
	}
	
	/**
	 * Method to display into your component a highlighted text
	 * @param component Wicket component
	 */
	public static void highlightedText(Component component) {
		component.add(
				new AttributeAppender(
						"class", 
						true, 
						new Model<String>("ui-state-highlight ui-corner-all"), " "));
	}
	
	/**
	 * Method to get the {@link JsStatement} to insert the hover style on your
	 * {@link Component}
	 * @param component Wicket component
	 * @return the JsStatement
	 */
	public static JsStatement hover(Component component) {
		component.setOutputMarkupId(true);
		return new JsQuery(component).$().chain(
				"hover", 
				"function() { $(this).addClass('ui-state-hover'); }",
				"function() { $(this).removeClass('ui-state-hover'); }");
	}
	
	/**
	 * Method to insert the the hover style into the Ajax transaction
	 * @param component Wicket component
	 * @param ajaxRequestTarget Ajax transaction
	 */
	public static void hover(Component component, AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavaScript(hover(component).render().toString());
	}
	
	/**
	 * Method to display your composant as an icon
	 * @param component Wicket component
	 * @param iconEnum Icon to display
	 */
	public static void iconComponent(Component component, IconEnum iconEnum) {
		component.add(
				new AttributeAppender(
						"class", 
						true, 
						new Model<String>("ui-icon " + iconEnum.getCssClass()), " "));
	}
	
	/**
	 * Method to display your composant as an icon
	 * @param component Wicket component
	 * @param iconEnum Icon to display
	 */
	public static void iconComponent(Component component, UiIcon icon) {
		component.add(
				new AttributeAppender(
						"class", 
						true, 
						new Model<String>("ui-icon " + icon.getCssClass()), " "));
	}
	
	/**
	 * Method to display a component as an overlay
	 * @param component Wicket component
	 */
	public static void overlayComponent(Component component) {
		component.add(
				new AttributeAppender(
						"class", 
						true, 
						new Model<String>("ui-overlay ui-widget-overlay ui-widget-shadow ui-corner-all"), " "));
	}
	
	/**
	 * Method to display a component as a title
	 * @param component Wicket component
	 */
	public static void titleComponent(Component component) {
		component.add(
				new AttributeAppender(
						"class", 
						true, 
						new Model<String>("ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"), " "));
	}
	
	/**
	 * Styles the given {@link Component} as a jQuery UI header.
	 */
	public static void headerComponent(Component component) {
		component.add(
				new AttributeAppender(
						"class", 
						true, 
						new Model<String>("ui-widget-header"), " "));		
	}
	
	/**
	 * Styles the given {@link Component} as a jQuery UI shadow.
	 */
	public static void shadowComponent(Component component) {
		component.add(
				new AttributeAppender(
						"class", 
						true, 
						new Model<String>("ui-widget-shadow"), " "));
	}
	
}
