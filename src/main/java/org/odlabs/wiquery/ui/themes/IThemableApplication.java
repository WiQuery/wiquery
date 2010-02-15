package org.odlabs.wiquery.ui.themes;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.Session;

public interface IThemableApplication {

	ResourceReference getTheme(Session session); 
	
}
