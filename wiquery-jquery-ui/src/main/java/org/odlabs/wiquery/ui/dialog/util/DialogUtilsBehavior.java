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

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractAjaxBehavior;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.ui.dialog.Dialog;
import org.odlabs.wiquery.ui.dialog.DialogJavaScriptResourceReference;

/**
 * $Id: DialogResourcesBehavior.java
 * 
 * Behavior to load all needed resources for the {@link Dialog} Contains some utilities
 * method to create very lightweight {@link Dialog}
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class DialogUtilsBehavior extends WiQueryAbstractAjaxBehavior
{
	/**
	 * Enumeration of possibles alternatives languages for the Dailog (Default local : EN)
	 * 
	 * @author Julien Roche
	 * 
	 */
	public enum DialogUtilsLanguages
	{
		ALBANIAN("sq"),
		ARABIC("ar"),
		BELARUSIAN("be"),
		BULGARIAN("bg"),
		CATALAN("ca"),
		CROATIAN("hr"),
		DANISH("da"),
		DEUTSCH(Locale.GERMAN),
		ENGLISH(Locale.ENGLISH),
		ESTONIAN("et"),
		FINNISH("fi"),
		FRENCH(Locale.FRENCH),
		GREEK("el"),
		HEBREW("iw"),
		HINDI("hi", "IN"),
		HUNGARIAN("hu"),
		ICELANDIC("is"),
		INDONESIAN("in"),
		ITALIAN("it"),
		JAPANESE("ja"),
		KOREAN(Locale.KOREAN),
		LITHUANIAN("lt"),
		NORVEGIAN("no"),
		POLISH("pl"),
		PORTUGUESE("pt"),
		ROUMANIAN("ro"),
		RUSSIAN("ru"),
		SERBIAN("sr"),
		SIMPLIFIED_CHINESE(Locale.SIMPLIFIED_CHINESE),
		SLOVAK("sk"),
		SLOVENIAN("sl"),
		SPANISH("es"),
		SWEDISH("sv"),
		THAI("th"),
		TRADITIONAL_CHINESE(Locale.TRADITIONAL_CHINESE),
		TURKISH("tr"),
		UKRAINIAN("uk"),
		VIETNAMESE("vi");

		/**
		 * Try to find the most appropriate value in the enumeration
		 * 
		 * @param locale
		 *            Local to search
		 * @return the value
		 */
		public static DialogUtilsLanguages getDialogUtilsLanguages(Locale locale)
		{
			if (locale == null)
			{
				return DialogUtilsLanguages.ENGLISH;
			}

			Locale tmpLocale = null;
			String language = locale.getLanguage();
			String country = locale.getCountry();
			String variant = locale.getVariant();
			String empty = "";

			country = country == null || country.trim().length() <= 0 ? null : country;
			variant = variant == null || variant.trim().length() <= 0 ? null : variant;

			// Equals on language-country-variant
			if (variant != null)
			{
				for (DialogUtilsLanguages l : values())
				{
					tmpLocale = l.getLocale();

					if (tmpLocale.getLanguage().equals(language)
						&& tmpLocale.getCountry().equals(country)
						&& tmpLocale.getVariant().equals(variant))
					{
						return l;
					}
				}
			}

			// Equals on language-country
			if (country != null)
			{
				for (DialogUtilsLanguages l : values())
				{
					tmpLocale = l.getLocale();

					if (tmpLocale.getLanguage().equals(language)
						&& tmpLocale.getCountry().equals(country)
						&& tmpLocale.getVariant().equals(empty))
					{
						return l;
					}
				}
			}

			// Equals on language
			for (DialogUtilsLanguages l : values())
			{
				tmpLocale = l.getLocale();

				if (tmpLocale.getLanguage().equals(language)
					&& tmpLocale.getCountry().equals(empty) && tmpLocale.getVariant().equals(empty))
				{
					return l;
				}
			}

			return DialogUtilsLanguages.ENGLISH;
		}

		/**
		 * Method calculating the literal language
		 * 
		 * @param language
		 * @return the literal
		 */
		public static CharSequence getDialogUtilsLiteral(DialogUtilsLanguages language)
		{
			Locale locale = language.getLocale();

			StringBuilder buffer = new StringBuilder();
			buffer.append(locale.getLanguage());

			if (locale.getCountry() != null && !locale.getCountry().equals(""))
			{
				buffer.append('_').append(locale.getCountry());

				if (locale.getVariant() != null && !locale.getVariant().equals(""))
				{
					buffer.append('_').append(locale.getVariant());
				}
			}

			return JsUtils.quotes(buffer.toString());
		}

		/**
		 * Method calculating the {@link JavaScriptResourceReference} language
		 * 
		 * @param language
		 * @return the resource
		 */
		public static JavaScriptResourceReference getDialogUtilsResource(
				DialogUtilsLanguages language)
		{
			Locale locale = language.getLocale();

			StringBuilder buffer = new StringBuilder();
			buffer.append("i18n/");
			buffer.append("wiquery-dialog_");
			buffer.append(locale.getLanguage());

			if (locale.getCountry() != null && !locale.getCountry().equals(""))
			{
				buffer.append('_').append(locale.getCountry());

				if (locale.getVariant() != null && !locale.getVariant().equals(""))
				{
					buffer.append('_').append(locale.getVariant());
				}
			}

			buffer.append(".js");

			return new JavaScriptResourceReference(DialogUtilsBehavior.class, buffer.toString());
		}

		// Properties
		private final Locale locale;

		/**
		 * Constructor
		 * 
		 * @param locale
		 */
		DialogUtilsLanguages(Locale locale)
		{
			this.locale = locale;
		}

		/**
		 * Constructor
		 * 
		 * @param language
		 */
		DialogUtilsLanguages(String language)
		{
			this.locale = new Locale(language);
		}

		/**
		 * Constructor
		 * 
		 * @param language
		 * @param country
		 */
		DialogUtilsLanguages(String language, String country)
		{
			this.locale = new Locale(language, country);
		}

		/**
		 * Constructor
		 * 
		 * @param language
		 * @param country
		 * @param variant
		 */
		DialogUtilsLanguages(String language, String country, String variant)
		{
			this.locale = new Locale(language, country, variant);
		}

		/**
		 * @return the locale
		 */
		public Locale getLocale()
		{
			return locale;
		}
	}

	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 2337318416689834710L;

	/** Cancel image */
	private static final SharedResourceReference CANCEL_IMG = new SharedResourceReference(
		DialogUtilsBehavior.class, "cancel.png");

	/** Question image */
	private static final SharedResourceReference QUESTION_IMG = new SharedResourceReference(
		DialogUtilsBehavior.class, "questionmark.png");

	/** Wait image */
	private static final SharedResourceReference WAIT_IMG = new SharedResourceReference(
		DialogUtilsBehavior.class, "wait.gif");

	/** Warning image */
	private static final SharedResourceReference WARNING_IMG = new SharedResourceReference(
		DialogUtilsBehavior.class, "warning.png");

	/** Constant of wiQuery Dialog resource */
	public static final JavaScriptResourceReference WIQUERY_DIALOG_JS =
		new JavaScriptResourceReference(DialogUtilsBehavior.class, "wiquery-dialog.js");

	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);
		response.render(JavaScriptHeaderItem.forReference(DialogJavaScriptResourceReference.get()));

		response.render(JavaScriptHeaderItem.forReference(WIQUERY_DIALOG_JS));
		response.render(JavaScriptHeaderItem.forReference(DialogUtilsLanguages
			.getDialogUtilsResource(DialogUtilsLanguages.getDialogUtilsLanguages(getLocale()))));
	}

	/**
	 * Method creating an error Dialog
	 * 
	 * @param message
	 *            Message
	 * @return a {@link JsStatement}
	 */
	public JsStatement errorDialog(String message)
	{
		JsStatement statement = new JsStatement();
		statement.append("$.ui.dialog.wiquery.errorDialog(");
		statement.append("" + Session.get().nextSequenceValue() + ", ");
		statement.append(DialogUtilsLanguages.getDialogUtilsLiteral(DialogUtilsLanguages
			.getDialogUtilsLanguages(getLocale())) + ", ");
		statement.append(JsUtils.doubleQuotes(message, true) + ", ");
		statement.append(JsUtils.quotes(RequestCycle.get().urlFor(CANCEL_IMG, null)) + ")");

		return statement;
	}

	/**
	 * @return the used local
	 */
	public Locale getLocale()
	{
		Locale l = getComponent() == null ? null : getComponent().getLocale();
		return l == null ? Locale.ENGLISH : l;
	}

	/**
	 * Method creating a question Dialog
	 * 
	 * @param message
	 *            Message
	 * @return a {@link JsStatement}
	 */
	public JsStatement questionDialog(String message)
	{
		JsStatement statement = new JsStatement();
		statement.append("$.ui.dialog.wiquery.questionDialog(");
		statement.append("" + Session.get().nextSequenceValue() + ", ");
		statement.append(DialogUtilsLanguages.getDialogUtilsLiteral(DialogUtilsLanguages
			.getDialogUtilsLanguages(getLocale())) + ", ");
		statement.append(JsUtils.doubleQuotes(message, true) + ", ");
		statement.append(JsUtils.quotes(RequestCycle.get().urlFor(QUESTION_IMG, null)) + ")");

		return statement;
	}

	/**
	 * Method creating a simple dialog
	 * 
	 * @param title
	 *            Title
	 * @param message
	 *            Message
	 * @return the required {@link JsStatement}
	 */
	public JsStatement simpleDialog(String title, String message)
	{
		JsStatement statement = new JsStatement();
		statement.append("$.ui.dialog.wiquery.simpleDialog(");
		statement.append("" + Session.get().nextSequenceValue() + ", ");
		statement.append(DialogUtilsLanguages.getDialogUtilsLiteral(DialogUtilsLanguages
			.getDialogUtilsLanguages(getLocale())) + ", ");
		statement.append(JsUtils.quotes(title, true) + ", ");
		statement.append(JsUtils.doubleQuotes(message, true) + ")");

		return statement;
	}

	/**
	 * Method creating a waiting dialog
	 * 
	 * @return a {@link WaitDialogStatements}
	 */
	public WaitDialogStatements waitDialog()
	{
		Integer id = Session.get().nextSequenceValue();

		JsStatement statement = new JsStatement();
		statement.append("$.ui.dialog.wiquery.waitDialog(");
		statement.append(id.toString() + ", ");
		statement.append(DialogUtilsLanguages.getDialogUtilsLiteral(DialogUtilsLanguages
			.getDialogUtilsLanguages(getLocale())) + ", ");
		statement.append(JsUtils.quotes(RequestCycle.get().urlFor(WAIT_IMG, null)) + ")");

		WaitDialogStatements wait = new WaitDialogStatements();
		wait.setOpen(statement);
		wait.setClose(new JsStatement().$(null, "#dialog" + id).chain("dialog",
			JsUtils.quotes("close")));

		return wait;
	}

	/**
	 * Method creating a warning Dialog
	 * 
	 * @param message
	 *            Message
	 * @return a {@link JsStatement}
	 */
	public JsStatement warningDialog(String message)
	{
		JsStatement statement = new JsStatement();
		statement.append("$.ui.dialog.wiquery.warningDialog(");
		statement.append("" + Session.get().nextSequenceValue() + ", ");
		statement.append(DialogUtilsLanguages.getDialogUtilsLiteral(DialogUtilsLanguages
			.getDialogUtilsLanguages(getLocale())) + ", ");
		statement.append(JsUtils.doubleQuotes(message, true) + ", ");
		statement.append(JsUtils.quotes(RequestCycle.get().urlFor(WARNING_IMG, null)) + ")");

		return statement;
	}
}
