/*
 * Copyright (c) 2010 WiQuery team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.wicketstuff.wiquery.ui.position;

import org.wicketstuff.wiquery.core.options.IComplexOption;
import org.wicketstuff.wiquery.core.options.LiteralOption;

/**
 * $Id PositionAlignmentOptions.java$
 *
 * <p>
 * Complex option to store the possible values for the position my/at options.
 * </p>
 *
 * @author Stephane Gleizes
 * @since 6.9.2
 */
public class PositionAlignmentOptions implements IComplexOption
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 5092239231164529443L;

	// Properties
	private PositionRelation horizontalAlignment;

	private int offsetLeft;

	private boolean offsetLeftPa = false;

	private PositionRelation verticalAlignment;

	private int offsetTop;

	private boolean offsetTopPa = false;

	/**
	 * Default constructor
	 */
	public PositionAlignmentOptions()
	{
		super();
	}

	/**
	 * Constructor with single alignment.
	 *
	 * @param alignment
	 */
	public PositionAlignmentOptions(PositionRelation alignment)
	{
		super();
		this.horizontalAlignment = alignment;
	}

	/**
	 * Constructor with both horizontal and vertical alignment.
	 *
	 * @param horizontalAlignment
	 * @param verticalAlignment
	 */
	public PositionAlignmentOptions(PositionRelation horizontalAlignment,
		PositionRelation verticalAlignment)
	{
		this(horizontalAlignment, 0, false, verticalAlignment, 0, false);
	}

	/**
	 * Constructor with horizontal, vertical alignment and offsets.
	 *
	 * @param horizontalAlignment
	 * @param offsetLeft
	 * @param verticalAlignment
	 * @param offsetTop
	 */
	public PositionAlignmentOptions(PositionRelation horizontalAlignment, int offsetLeft,
		PositionRelation verticalAlignment, int offsetTop)
	{
		this(horizontalAlignment, offsetLeft, false, verticalAlignment, offsetTop, false);
	}

	/**
	 * Constructor with horizontal, vertical alignment, offsets and offsetLeft percentage
	 *
	 * @param horizontalAlignment
	 * @param offsetLeft
	 * @param offsetLeftPa
	 * @param verticalAlignment
	 * @param offsetTop
	 */
	public PositionAlignmentOptions(PositionRelation horizontalAlignment, int offsetLeft,
		boolean offsetLeftPa, PositionRelation verticalAlignment, int offsetTop)
	{
		this(horizontalAlignment, offsetLeft, offsetLeftPa, verticalAlignment, offsetTop, false);
	}

	/**
	 * Constructor with horizontal, vertical alignment, offsets and offsetTop percentage
	 *
	 * @param horizontalAlignment
	 * @param offsetLeft
	 * @param verticalAlignment
	 * @param offsetTop
	 * @param offsetTopPa
	 */
	public PositionAlignmentOptions(PositionRelation horizontalAlignment, int offsetLeft,
		PositionRelation verticalAlignment, int offsetTop, boolean offsetTopPa)
	{
		this(horizontalAlignment, offsetLeft, false, verticalAlignment, offsetTop, offsetTopPa);
	}

	/**
	 * Constructor with horizontal, vertical alignment, offsets and offsetsPercentage
	 *
	 * @param horizontalAlignment
	 * @param offsetLeft
	 * @param offsetLeftPa
	 * @param verticalAlignment
	 * @param offsetTop
	 * @param offsetTopPa
	 */
	public PositionAlignmentOptions(PositionRelation horizontalAlignment, int offsetLeft,
		boolean offsetLeftPa, PositionRelation verticalAlignment, int offsetTop,
		boolean offsetTopPa)
	{
		super();
		this.horizontalAlignment = horizontalAlignment;
		this.offsetLeft = offsetLeft;
		this.offsetLeftPa = offsetLeftPa;
		this.verticalAlignment = verticalAlignment;
		this.offsetTop = offsetTop;
		this.offsetTopPa = offsetTopPa;
	}

	@Override
	public CharSequence getJavascriptOption()
	{
		StringBuilder sb = new StringBuilder();

		if (horizontalAlignment != null)
		{
			sb.append(horizontalAlignment.toString());
			if (offsetLeft != 0)
			{
				sb.append(offsetLeft > 0 ? "+" + offsetLeft : offsetLeft)
					.append(offsetLeftPa ? "%" : "");
			}
		}

		if (verticalAlignment != null)
		{
			if (sb.length() > 0)
			{
				sb.append(' ');
			}
			sb.append(verticalAlignment.toString());
			if (offsetTop != 0)
			{
				sb.append(offsetTop > 0 ? "+" + offsetTop : offsetTop)
					.append(offsetTopPa ? "%" : "");
			}
		}

		return new LiteralOption(sb.toString()).toString();
	}

	/**
	 * @return the horizontalAlignment property
	 */
	public PositionRelation getHorizontalAlignment()
	{
		return horizontalAlignment;
	}

	/**
	 * Set the horizontalAlignment property. One of LEFT, CENTER, RIGHT.
	 *
	 * @param horizontalAlignment
	 * @return the instance
	 */
	public PositionAlignmentOptions setHorizontalAlignment(PositionRelation horizontalAlignment)
	{
		return setHorizontalAlignment(horizontalAlignment, 0);
	}

	/**
	 * Set the horizontalAlignment property with an offset. One of LEFT, CENTER, RIGHT.
	 *
	 * @param horizontalAlignment
	 * @param offsetLeft
	 * @return the instance
	 */
	public PositionAlignmentOptions setHorizontalAlignment(PositionRelation horizontalAlignment,
		int offsetLeft)
	{
		switch (horizontalAlignment)
		{
			case LEFT :
			case CENTER :
			case RIGHT :
				break;
			default :
				throw new IllegalArgumentException(
					"Illegal value for the horizontal alignment property");
		}

		this.horizontalAlignment = horizontalAlignment;
		this.offsetLeft = offsetLeft;
		return this;
	}

	/**
	 * @return the offsetLeft property
	 */
	public int getOffsetLeft()
	{
		return offsetLeft;
	}

	/**
	 * Set the offsetLeft property.
	 *
	 * @param offsetLeft
	 * @return the instance
	 */
	public PositionAlignmentOptions setOffsetLeft(int offsetLeft)
	{
		this.offsetLeft = offsetLeft;
		return this;
	}

	/**
	 * @return the offsetLeftPa property
	 */
	public boolean isOffsetLeftPa()
	{
		return offsetLeftPa;
	}


	/**
	 * Set the offsetLeftPa property.
	 *
	 * @param offsetLeftPa
	 *            use offset left in percentage
	 * @return the instance
	 */
	public PositionAlignmentOptions setOffsetLeftPa(boolean offsetLeftPa)
	{
		this.offsetLeftPa = offsetLeftPa;
		return this;
	}

	/**
	 * @return the verticalAlignment property
	 */
	public PositionRelation getVerticalAlignment()
	{
		return verticalAlignment;
	}

	/**
	 * Set the verticalAlignment property. One of TOP, CENTER, BOTTOM.
	 *
	 * @param verticalAlignment
	 * @return the instance
	 */
	public PositionAlignmentOptions setVerticalAlignment(PositionRelation verticalAlignment)
	{
		return setVerticalAlignment(verticalAlignment, 0);
	}

	/**
	 * Set the verticalAlignment property with an offset. One of TOP, CENTER, BOTTOM.
	 *
	 * @param verticalAlignment
	 * @param offsetTop
	 * @return the instance
	 */
	public PositionAlignmentOptions setVerticalAlignment(PositionRelation verticalAlignment,
		int offsetTop)
	{
		switch (verticalAlignment)
		{
			case TOP :
			case CENTER :
			case BOTTOM :
				break;
			default :
				throw new IllegalArgumentException(
					"Illegal value for the vertical alignment property");
		}

		this.verticalAlignment = verticalAlignment;
		this.offsetTop = offsetTop;
		return this;
	}

	/**
	 * @return the offsetTop property
	 */
	public int getOffsetTop()
	{
		return offsetTop;
	}

	/**
	 * Set the offsetTop property.
	 *
	 * @param offsetTop
	 * @return the instance
	 */
	public PositionAlignmentOptions setOffsetTop(int offsetTop)
	{
		this.offsetTop = offsetTop;
		return this;
	}

	/**
	 * @return the offsetTopPa property
	 */
	public boolean isOffsetTopPa()
	{
		return offsetTopPa;
	}

	/**
	 * Set the offsetTop property.
	 *
	 * @param offsetTopPa
	 *            use offset top in percentage
	 * @return the instance
	 */
	public PositionAlignmentOptions setOffsetTopPa(boolean offsetTopPa)
	{
		this.offsetTopPa = offsetTopPa;
		return this;
	}
}
