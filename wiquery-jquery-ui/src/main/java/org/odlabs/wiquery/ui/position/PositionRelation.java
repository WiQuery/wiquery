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
	CENTER_BOTTOM,
	CENTER_CENTER,
	CENTER_TOP,
	LEFT,
	LEFT_BOTTOM,
	LEFT_CENTER,
	LEFT_TOP,
	RIGHT_BOTTOM,
	RIGHT_CENTER,
	RIGHT_TOP,
	TOP;

	/**
	 * Method searching the Position value
	 * 
	 * @param value
	 * @return
	 */
	public static PositionRelation getPosition(String value)
	{
		return valueOf(value.toUpperCase().replace(" ", "_"));
	}

	@Override
	public String toString()
	{
		return super.toString().toLowerCase().replace("_", " ");
	}
}