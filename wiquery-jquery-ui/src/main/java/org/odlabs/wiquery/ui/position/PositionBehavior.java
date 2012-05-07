/*
 * Copyright (c) 2009 WiQuery team
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
package org.odlabs.wiquery.ui.position;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractAjaxBehavior;
import org.odlabs.wiquery.core.javascript.JsQuery;

/**
 * $Id$
 * <p>
 * Behavior using the position utilities
 * </p>
 * 
 * Missing functionalities:
 * <ul>
 * <li>using</li>
 * </ul>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class PositionBehavior extends WiQueryAbstractAjaxBehavior
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 5096222396743839504L;

	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);
		response
			.render(JavaScriptHeaderItem.forReference(PositionJavaScriptResourceReference.get()));
		response.render(OnDomReadyHeaderItem.forScript(new JsQuery(getComponent()).$()
			.chain("position", options.getJavaScriptOptions()).render()));
	}

	/*---- Options section ---*/

	/**
	 * Defines which position on the target element to align the positioned element
	 * against: "horizontal vertical" alignment. A single value such as "right" will
	 * default to "right center", "top" will default to "center top" (following CSS
	 * convention). Acceptable values: "top", "center", "bottom", "left", "right".
	 * Example: "left top" or "center center"
	 * 
	 * @param at
	 * @return the instance
	 */
	public PositionBehavior setAt(PositionRelation at)
	{
		options.putLiteral("at", at.toString());
		return this;
	}

	/**
	 * @return the at option
	 */
	public PositionRelation getAt()
	{
		String value = options.getLiteral("at");
		return value == null ? null : PositionRelation.getPosition(value);
	}

	/**
	 * Applies the bgiframe plugin when set to true. Only applies when bgiframe is
	 * actually loaded, nothing happens otherwise.
	 * 
	 * @param bgiframe
	 * @return the instance
	 */
	public PositionBehavior setBgiframe(boolean bgiframe)
	{
		options.put("bgiframe", bgiframe);
		return this;
	}

	/**
	 * @return the bgiframe option
	 */
	public boolean isBgiframe()
	{
		if (options.containsKey("bgiframe"))
		{
			return options.getBoolean("bgiframe");
		}

		return true;
	}

	/**
	 * When the positioned element overflows the window in some direction, move it to an
	 * alternative position. Similar to my and at, this accepts a single value or a pair
	 * for horizontal/vertical, eg. "flip", "fit", "fit flip", "fit none".
	 * <ul>
	 * <li>flip: to the opposite side and the collision detection is run again to see if
	 * it will fit. If it won't fit in either position, the center option should be used
	 * as a fall back.</li>
	 * <li>fit: so the element keeps in the desired direction, but is re-positioned so it
	 * fits.</li>
	 * <li>none: not do collision detection.</li>
	 * </ul>
	 * 
	 * @param collision
	 * @return the instance
	 */
	public PositionBehavior setCollision(PositionCollision collision)
	{
		options.putLiteral("collision", collision.toString());
		return this;
	}

	/**
	 * @return the collision option
	 */
	public PositionCollision getCollision()
	{
		String value = options.getLiteral("collision");
		return value == null ? null : PositionCollision.getCollision(value);
	}

	/**
	 * Defines which position on the element being positioned to align with the target
	 * element: "horizontal vertical" alignment. A single value such as "right" will
	 * default to "right center", "top" will default to "center top" (following CSS
	 * convention). Acceptable values: "top", "center", "bottom", "left", "right".
	 * Example: "left top" or "center center"
	 * 
	 * @param my
	 * @return the instance
	 */
	public PositionBehavior setMy(PositionRelation my)
	{
		options.putLiteral("my", my.toString());
		return this;
	}

	/**
	 * @return the my option
	 */
	public PositionRelation getMy()
	{
		String value = options.getLiteral("my");
		return value == null ? null : PositionRelation.getPosition(value);
	}

	/**
	 * Element to position against. You can use a browser event object contains pageX and
	 * pageY values. Example: "#top-menu"
	 * 
	 * @param of
	 * @return the instance
	 */
	public PositionBehavior setOf(String of)
	{
		options.putLiteral("of", of);
		return this;
	}

	/**
	 * @return the of option
	 */
	public String getOf()
	{
		return options.getLiteral("of");
	}

	/**
	 * Add these left-top values to the calculated position, eg. "50 50" (left top) A
	 * single value such as "50" will apply to both.
	 * 
	 * @param offset
	 * @return the instance
	 */
	public PositionBehavior setOffset(PositionOffset offset)
	{
		options.put("offset", offset);
		return this;
	}

	/**
	 * @return the offset option
	 */
	public PositionOffset getOffset()
	{
		return (PositionOffset) options.getComplexOption("offset");
	}

	/*---- Events section ---*/

	/**
	 * When specified the actual property setting is delegated to this callback. Receives
	 * a single parameter which is a hash of top and left values for the position that
	 * should be set.
	 * 
	 * @param by
	 * @return the instance
	 */
	public PositionBehavior setBy(JsScopePositionEvent by)
	{
		options.put("by", by);
		return this;
	}
}
