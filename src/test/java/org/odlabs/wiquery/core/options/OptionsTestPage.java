/**
 * 
 */
package org.odlabs.wiquery.core.options;

import org.apache.wicket.markup.html.WebPage;

/**
 * @author Ernesto
 *
 */
public class OptionsTestPage extends WebPage {

	private OptionsTestPanel panel;
	
	private Options options;
	
	/**
	 * 
	 */
	public OptionsTestPage(OptionsTestPanel panel, Options options) {
		this.options = options;
		add(panel);
	}

	public OptionsTestPanel getPanel() {
		return panel;
	}

	public void setPanel(OptionsTestPanel panel) {
		this.panel = panel;
	}

	public Options getOptions() {
		return options;
	}
	
	

}
