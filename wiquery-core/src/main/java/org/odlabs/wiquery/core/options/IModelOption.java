/**
 * 
 */
package org.odlabs.wiquery.core.options;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * Common interface for all options accepting IModels.
 * 
 * @author Ernesto Reinaldo Barreiro
 */
public interface IModelOption<B>
{

	/**
	 * @return Return the model
	 */
	IModel<B> getModel();

	void setModel(IModel<B> model);

	B getValue();

	IModelOption<B> wrapOnAssignment(Component component);
}
