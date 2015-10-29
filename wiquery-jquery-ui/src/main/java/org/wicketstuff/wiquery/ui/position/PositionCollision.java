package org.wicketstuff.wiquery.ui.position;

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
	FIT_FLIPFIT,
	FIT_NONE,
	FLIP,
	FLIP_FIT,
	FLIP_FLIP,
	FLIP_FLIPFIT,
	FLIP_NONE,
	FLIPFIT,
	FLIPFIT_FIT,
	FLIPFIT_FLIP,
	FLIPFIT_FLIPFIT,
	FLIPFIT_NONE,
	NONE,
	NONE_FLIT,
	NONE_FLIP,
	NONE_FLIPFIT,
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