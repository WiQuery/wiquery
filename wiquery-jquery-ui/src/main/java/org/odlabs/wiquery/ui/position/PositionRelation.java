package org.odlabs.wiquery.ui.position;

/**
 * Enumeration of position values
 * 
 * @author Julien Roche
 * @since 1.1
 * 
 */
public enum PositionRelation
{
	BOTTOM,
	CENTER,
	LEFT,
	RIGHT,
	TOP;

	/**
	 * Method searching the Position value
	 * 
	 * @param value
	 * @return
	 */
	public static PositionRelation getPosition(String value)
	{
		return valueOf(value.toUpperCase());
	}

	@Override
	public String toString()
	{
		return super.toString().toLowerCase();
	}
}