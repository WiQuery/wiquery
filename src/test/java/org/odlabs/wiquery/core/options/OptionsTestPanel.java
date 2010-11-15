/**
 * 
 */
package org.odlabs.wiquery.core.options;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class OptionsTestPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private Options options;

	public OptionsTestPanel(String id) {
		super(id);
		options = new Options(this);
	}

	public Options getOptions() {
		return options;
	}
}
