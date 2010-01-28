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
package org.odlabs.wiquery.ui.dialog.util;

import java.util.Locale;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.ui.dialog.Dialog;

/**
 * $Id: DialogUtils.java
 * 
 * Dialog utilities to create very lightweight {@link Dialog} 
 * 
 * Before executing the statements, you must use the {@link DialogResourcesBehavior}
 * 
 * @author Julien Roche
 * @since 1.1
 */
public abstract class DialogUtils {
	/**
	 * Enumeration of possibles alternatives languages for the Dailog
	 * (Default local : EN)
	 * @author Julien Roche
	 *
	 */
	public enum DialogUtilsLanguages {
		ENGLISH				(Locale.ENGLISH),
		FRENCH				(Locale.FRENCH);
		
		// Properties
		private final Locale locale;
		
		/**
		 * Constructor
		 * @param locale
		 */
		DialogUtilsLanguages(Locale locale) {
			this.locale = locale;
		}
		
		/**
		 * Constructor
		 * @param language
		 */
		DialogUtilsLanguages(String language) {
			this.locale = new Locale(language);
		}
		
		/**
		 * Constructor
		 * @param language
		 * @param country
		 */
		DialogUtilsLanguages(String language, String country) {
			this.locale = new Locale(language, country);
		}
		
		/**
		 * Constructor
		 * @param language
		 * @param country
		 * @param variant
		 */
		DialogUtilsLanguages(String language, String country, String variant) {
			this.locale = new Locale(language, country, variant);
		}
		
		/**
		 * @return the locale
		 */
		public Locale getLocale() {
			return locale;
		}
		
		/**
		 * Try to find the most appropriate value in the enumeration
		 * @param locale Local to search
		 * @return the value
		 */
		public static DialogUtilsLanguages getDialogUtilsLanguages(Locale locale){
			Locale tmpLocale = null;
			String language = locale.getLanguage();
			String country = locale.getCountry();
			String variant = locale.getVariant();
			String empty = "";
			
			country = country == null || country.trim().length() <= 0 ? null : country;
			variant = variant == null || variant.trim().length() <= 0 ? null : variant;
			
			// Equals on language-country-variant
			if(variant != null){
				for(DialogUtilsLanguages l : values()){
					tmpLocale = l.getLocale();
					
					if(tmpLocale.getLanguage().equals(language)
							&& tmpLocale.getCountry().equals(country)
							&& tmpLocale.getVariant().equals(variant)){
						return l;
					}
				}
			}
			
			// Equals on language-country
			if(country != null){
				for(DialogUtilsLanguages l : values()){
					tmpLocale = l.getLocale();
					
					if(tmpLocale.getLanguage().equals(language)
							&& tmpLocale.getCountry().equals(country)
							&& tmpLocale.getVariant().equals(empty)){
						return l;
					}
				}
			}
			
			// Equals on language
			for(DialogUtilsLanguages l : values()){
				tmpLocale = l.getLocale();
				
				if(tmpLocale.getLanguage().equals(language)
						&& tmpLocale.getCountry().equals(empty)
						&& tmpLocale.getVariant().equals(empty)){
					return l;
				}
			}
			
			return DialogUtilsLanguages.ENGLISH;
		}
		
		/**
		 * Method calculating the literal language
		 * @param language
		 * @return the literal
		 */
		public static CharSequence getDialogUtilsLiteral(DialogUtilsLanguages language) {
			Locale locale = language.getLocale();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append(locale.getLanguage());
			
			if(locale.getCountry() == null || locale.getCountry().equals("")){
				buffer.append("_" + locale.getCountry());
				
				if(locale.getVariant() == null || locale.getVariant().equals("")){
					buffer.append("_" + locale.getVariant());
				}
			}
			
			return JsUtils.quotes(buffer);
		}
		
		/**
		 * Method calculating the {@link JavascriptResourceReference} language
		 * @param language
		 * @return the resource
		 */
		public static JavascriptResourceReference getDialogUtilsResource(DialogUtilsLanguages language) {
			Locale locale = language.getLocale();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("wiquery-dialog_");
			buffer.append(locale.getLanguage());
			
			if(locale.getCountry() == null || locale.getCountry().equals("")){
				buffer.append("_" + locale.getCountry());
				
				if(locale.getVariant() == null || locale.getVariant().equals("")){
					buffer.append("_" + locale.getVariant());
				}
			}
			
			buffer.append(".min.js");
			
			return new JavascriptResourceReference(DialogUtils.class, "i18n/" + buffer);
		}
	}
	
	// Constants
	/** Cancel image */
	private static final ResourceReference CANCEL_IMG = 
		new ResourceReference(DialogUtils.class, "cancel.png");
	
	/** Question image */
	private static final ResourceReference QUESTION_IMG = 
		new ResourceReference(DialogUtils.class, "questionmark.png");
	
	/** Wait image */
	private static final ResourceReference WAIT_IMG = 
		new ResourceReference(DialogUtils.class, "wait.gif");
	
	/** Warning image */
	private static final ResourceReference WARNING_IMG = 
		new ResourceReference(DialogUtils.class, "warning.png");
	
	/**
	 * Method creating an error Dialog
	 * @param locale Local
	 * @param message Message
	 * @return a {@link JsStatement}
	 */
	public static JsStatement errorDialog(Locale locale, String message) {
		JsStatement statement = new JsStatement();
		statement.append("$.ui.dialog.wiquery.errorDialog(");
		statement.append("" + Session.get().nextSequenceValue() + ", ");
		statement.append(DialogUtilsLanguages.getDialogUtilsLiteral(DialogUtilsLanguages.getDialogUtilsLanguages(locale)) + ", ");
		statement.append(JsUtils.doubleQuotes(message, true) + ", ");
		statement.append(JsUtils.quotes(RequestCycle.get().urlFor(CANCEL_IMG)) + ")");
		
		return statement;
	}
	
	/**
	 * Method creating a question Dialog
	 * @param locale Local
	 * @param message Message
	 * @return a {@link JsStatement}
	 */
	public static JsStatement questionDialog(Locale locale, String message) {
		JsStatement statement = new JsStatement();
		statement.append("$.ui.dialog.wiquery.questionDialog(");
		statement.append("" + Session.get().nextSequenceValue() + ", ");
		statement.append(DialogUtilsLanguages.getDialogUtilsLiteral(DialogUtilsLanguages.getDialogUtilsLanguages(locale)) + ", ");
		statement.append(JsUtils.doubleQuotes(message, true) + ", ");
		statement.append(JsUtils.quotes(RequestCycle.get().urlFor(QUESTION_IMG)) + ")");
		
		return statement;
	}
	
	/**
	 * Method creating a simple dialog
	 * @param locale Local
	 * @param title Title
	 * @param message Message
	 * @return the required {@link JsStatement}
	 */
	public static JsStatement simpleDialog(Locale locale, String title, String message) {
		JsStatement statement = new JsStatement();
		statement.append("$.ui.dialog.wiquery.simpleDialog(");
		statement.append("" + Session.get().nextSequenceValue() + ", ");
		statement.append(DialogUtilsLanguages.getDialogUtilsLiteral(DialogUtilsLanguages.getDialogUtilsLanguages(locale)) + ", ");
		statement.append(JsUtils.quotes(title, true) + ", ");
		statement.append(JsUtils.doubleQuotes(message, true) + ")");
		
		return statement;
	}
	
	/**
	 * Method creating a waiting dialog
	 * @param locale Local
	 * @return a {@link WaitDialogStatements}
	 */
	public static WaitDialogStatements waitDialog(Locale locale) {
		Integer id = Session.get().nextSequenceValue();
		
		JsStatement statement = new JsStatement();
		statement.append("$.ui.dialog.wiquery.waitDialog(");
		statement.append(id.toString() + ", ");
		statement.append(DialogUtilsLanguages.getDialogUtilsLiteral(DialogUtilsLanguages.getDialogUtilsLanguages(locale)) + ", ");
		statement.append(JsUtils.quotes(RequestCycle.get().urlFor(WAIT_IMG)) + ")");
		
		WaitDialogStatements wait = new WaitDialogStatements();
		wait.setOpen(statement);
		wait.setClose(new JsStatement().$(null, "#dialog" + id).chain("dialog", 
				JsUtils.quotes("close")));
		
		return wait;
	}
	
	/**
	 * Method creating a warning Dialog
	 * @param locale Local
	 * @param message Message
	 * @return a {@link JsStatement}
	 */
	public static JsStatement warningDialog(Locale locale, String message) {
		JsStatement statement = new JsStatement();
		statement.append("$.ui.dialog.wiquery.warningDialog(");
		statement.append("" + Session.get().nextSequenceValue() + ", ");
		statement.append(DialogUtilsLanguages.getDialogUtilsLiteral(DialogUtilsLanguages.getDialogUtilsLanguages(locale)) + ", ");
		statement.append(JsUtils.doubleQuotes(message, true) + ", ");
		statement.append(JsUtils.quotes(RequestCycle.get().urlFor(WARNING_IMG)) + ")");
		
		return statement;
	}
}
