/**
 * 
 */
package org.odlabs.wiquery.core.options;

/**
 * Represents an typed option.
 * 
 * @author Ernesto Reinaldo Barreiro
 */
public interface ITypedOption<T> extends IListItemOption
{

	/**
	 * @return Returns the value or null if no value is set.
	 */
	public T getValue();

}
