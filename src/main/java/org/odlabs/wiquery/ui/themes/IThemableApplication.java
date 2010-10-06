package org.odlabs.wiquery.ui.themes;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.Session;

/**
 * Allows to mark your WEB application as a provider of,
 * session based, UI themes. Example 
 * 
 *  <pre>
 *  public class WicketApplication extends WebApplication implements IThemableApplication {
 *  
 *  ...
 *  
 *  	public ResourceReference getTheme(Session session) {
 *  		if(session.getMetaData(WIQUERY_THEME_KEY) == null){
 *  			session.setMetaData(WIQUERY_THEME_KEY, new WiQueryCoreThemeResourceReference("fusion"));
 *  		}
 *  		return session.getMetaData(WIQUERY_THEME_KEY);
 *  	}
 *  }
 * </pre>
 *
 */
public interface IThemableApplication {

	/**
	 * 
	 * @param session The wicket session 
	 * @return A resource reference for the active theme 
	 *         for the given session.
	 */
	ResourceReference getTheme(Session session); 
	
}
