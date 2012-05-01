package org.odlabs.wiquery.ui.position;

/**
 * Enumeration of collision values
 * 
 * @author Julien Roche
 * @since 1.1
 * 
 */
public enum PositionCollision
{
	FIT,
	FIT_FIT,
	FIT_FLIP,
	FIT_NONE,
	FLIP,
	FLIP_FIT,
	FLIP_FLIP,
	FLIP_NONE,
	NONE,
	NONE_FLIT,
	NONE_FLIP,
	NONE_NONE;

	/**
	 * Method searching the collision value
	 * 
	 * @param value
	 * @return
	 */
	public static PositionCollision getCollision(String value)
	{
		return valueOf(value.toUpperCase().replace(" ", "_"));
	}

	@Override
	public String toString()
	{
		return super.toString().toLowerCase().replace("_", " ");
	}
}