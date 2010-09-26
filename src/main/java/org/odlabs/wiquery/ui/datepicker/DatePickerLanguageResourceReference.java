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

import java.util.Locale;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * $Id: DatePickerLanguageResourceReference.java 81 2009-05-28 20:05:12Z
 * lionel.armanet $
 * <p>
 * Provides a JavaScript reference to display the date picker labels for a given
 * local.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.6
 */
public class DatePickerLanguageResourceReference extends
		JavascriptResourceReference {
	/**
	 * Enumeration of possibles alternatives languages for the DatePicker
	 * (Default locale for the DatePicker : EN)
	 * @author Julien Roche
	 *
	 */
	public enum DatePickerLanguages {
		AFRIKAANS			("af"),
		ALBANIAN			("sq"),
		ARABIC				("ar"),
		ARMENIAN			("hy"),
		AZERBAIJANI			("az"),
		BOSNIAN				("bs"),
		BRAZILIAN			("pt", "BR"),
		BULGARIAN			("bg"),
		CATALAN				("ca"),
		CHINESE				("zh", "HK"),
		CROATIAN			("hr"),
		CZECH				("cs"),
		DANISH				("da"),
		DUTCH				("nl"),
		ESPERANTO			("eo"),
		ESTONIAN			("et"),
		EUSKARAKO			("eu"),
		FAROESE				("fo"),
		FINNISH				("fi"),
		FRENCH				(Locale.FRENCH),
		GERMAN				(Locale.GERMAN),
		GREEK				("el"),
		HEBREW				("he"),
		HUNGARIAN			("hu"),
		ICELANDIC			("is"),
		INDONESIAN			("id"),
		ITALIAN				(Locale.ITALIAN),
		JAPANESE			(Locale.JAPANESE),
		KAZAKH				("kz"),
		KOREAN				(Locale.KOREAN),
		LATVIAN				("lv"),
		LITHUANIAN			("lt"),
		MALAYSIAN			("ms"),
		NORVEGIAN			("no"),
		PERSIAN				("fa"),
		POLISH				("pl"),
		ROMANIAN			("ro"),
		RUSSIAN				("ru"),
		SERBIA				("sr", "SR"),
		SERBIAN				("sr"),
		SIMPLIFIED_CHINESE	(Locale.SIMPLIFIED_CHINESE),
		SLOVAK				("sk"),
		SLOVENIAN			("sl"),
		SPANISH				("es"),
		SWEDISH				("sv"),
		SWISS				("fr", "CH"),
		TAMIL				("ta"),
		THAI				("th"),
		TRADITIONAL_CHINESE	(Locale.TRADITIONAL_CHINESE),
		TURKISH				("tr"),
		UNITED_KINGDOM		(Locale.UK), // Default locale
		UKRAINIAN			("uk"),
		VIETNAMESE			("vi");
		
		// Properties
		private final Locale locale;
		
		/**
		 * Constructor
		 * @param locale
		 */
		DatePickerLanguages(Locale locale) {
			this.locale = locale;
		}
		
		/**
		 * Constructor
		 * @param language
		 */
		DatePickerLanguages(String language) {
			this.locale = new Locale(language);
		}
		
		/**
		 * Constructor
		 * @param language
		 * @param country
		 */
		DatePickerLanguages(String language, String country) {
			this.locale = new Locale(language, country);
		}
		
		/**
		 * Constructor
		 * @param language
		 * @param country
		 * @param variant
		 */
		DatePickerLanguages(String language, String country, String variant) {
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
		 * @param locale Locale to search
		 * @return the value
		 */
		public static DatePickerLanguages getDatePickerLanguages(Locale locale){
			if(locale == null || locale.getLanguage().equalsIgnoreCase(Locale.ENGLISH.getLanguage())){
				return DatePickerLanguages.UNITED_KINGDOM;
			}
			
			Locale tmpLocale = null;
			String language = locale.getLanguage();
			String country = locale.getCountry();
			String variant = locale.getVariant();
			String empty = "";
			
			country = country == null || country.trim().length() <= 0 ? null : country;
			variant = variant == null || variant.trim().length() <= 0 ? null : variant;
			
			// Equals on language-country-variant
			if(variant != null){
				for(DatePickerLanguages l : values()){
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
				for(DatePickerLanguages l : values()){
					tmpLocale = l.getLocale();
					
					if(tmpLocale.getLanguage().equals(language)
							&& tmpLocale.getCountry().equals(country)
							&& tmpLocale.getVariant().equals(empty)){
						return l;
					}
				}
			}
			
			// Equals on language
			for(DatePickerLanguages l : values()){
				tmpLocale = l.getLocale();
				
				if(tmpLocale.getLanguage().equals(language)
						&& tmpLocale.getCountry().equals(empty)
						&& tmpLocale.getVariant().equals(empty)){
					return l;
				}
			}
			
			return null;
		}
		
		/**
		 * Method calculating the name of the jQuery UI Locale file
		 * @param dpl Language to use
		 * @return the filename
		 */
		public static CharSequence getJsFileName(DatePickerLanguages dpl) {
			if(dpl == null){
				return null;
			}
			
			Locale locale = dpl.getLocale();
			String country = locale.getCountry();
			String variant = locale.getVariant();
			StringBuffer js = new StringBuffer();
			
			js.append("i18n/jquery.ui.datepicker-");
			js.append(locale.getLanguage());
			
			if(country != null && country.trim().length() > 0){
				js.append("-" + country);
				
				if(variant != null && variant.trim().length() > 0){
					js.append("-" + variant);
				}
			}
			
			js.append(".min.js");
			
			return js;
		}
	}
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 5955164494361831059L;

	/**
	 * Constructor
	 * @param locale Locale
	 */
	public DatePickerLanguageResourceReference(Locale locale) {
		super(DatePickerLanguageResourceReference.class, getJsFilename(locale));
	}

	/**
	 * Method to calculate the name of the javascript file
	 * @param locale Locale
	 * @return the name
	 */
	private static String getJsFilename(Locale locale) {
		DatePickerLanguages dpl = DatePickerLanguages.getDatePickerLanguages(locale);
		
		if(dpl == null){
			throw new WicketRuntimeException("The locale cannot load the required javascript locale file");
		}
		
		return DatePickerLanguages.getJsFileName(dpl).toString();
	}
}
