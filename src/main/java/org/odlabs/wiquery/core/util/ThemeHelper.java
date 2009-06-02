package org.odlabs.wiquery.core.util;

import org.apache.wicket.Application;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.odlabs.wiquery.core.commons.WiQueryInstantiationListener;

public class ThemeHelper {

	/**
	 * Sets the theme to use.
	 * 
	 * @param themeResourceReference
	 *            The theme as a {@link ResourceReference}
	 */
	public static void setTheme(ResourceReference themeResourceReference) {
		Application application = Application.get();
		application.getSessionStore().setAttribute(
				RequestCycle.get().getRequest(), "wickery-theme",
				themeResourceReference);
	}

	/**
	 * Returns the current theme as a {@link ResourceReference}.
	 */
	public static ResourceReference getTheme() {
		Application application = Application.get();
		Object theme = application.getSessionStore().getAttribute(
				RequestCycle.get().getRequest(), "wickery-theme");
		if (theme == null) {
			// setting default theme
			setTheme(WiQueryInstantiationListener.DEFAULT_THEME);
		}
		theme = application.getSessionStore().getAttribute(
				RequestCycle.get().getRequest(), "wickery-theme");
		return (ResourceReference) theme;
	}

}
