/**
 * 
 */
package org.wicketstuff.wiquery.core.options;

import org.apache.wicket.markup.html.panel.Panel;
import org.wicketstuff.wiquery.core.options.Options;

/**
 * @author Ernesto Reinaldo Barreiro
 */
public class OptionsTestPanel extends Panel
{

	private static final long serialVersionUID = 1L;

	private Options options;

	public OptionsTestPanel(String id)
	{
		super(id);
		options = new Options(this);
	}

	public Options getOptions()
	{
		return options;
	}
}
