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

import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;

/**
 * $Id$
 * <p>
 * 	Behavior using the position utilities
 * </p>
 * @author Julien Roche
 * @since 1.1
 */
public class PositionBehavior extends WiQueryAbstractBehavior {
	/**
	 * Enumeration of collision values
	 * @author Julien Roche
	 * @since 1.1
	 *
	 */
	public enum Collision {
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
		 * @param value
		 * @return
		 */
		public static Collision getCollision(String value) {
			return valueOf(value.toUpperCase().replace(" ", "_"));
		}

		/**
		 * {@inheritDoc}
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return super.toString().toLowerCase().replace("_", " ");
		}
	}
	
	/**
	 * Enumeration of position values
	 * @author Julien Roche
	 * @since 1.1
	 *
	 */
	public enum Position {
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
		 * @param value
		 * @return
		 */
		public static Position getPosition(String value) {
			return valueOf(value.toUpperCase().replace(" ", "_"));
		}

		/**
		 * {@inheritDoc}
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return super.toString().toLowerCase().replace("_", " ");
		}
	}
	
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 5096222396743839504L;
	
	// Properties
	private Options options;
	
	/**
	 * Default constructor
	 */
	public PositionBehavior() {
		super();
		options = new Options();
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		super.contribute(wiQueryResourceManager);
		wiQueryResourceManager.addJavaScriptResource(PositionJavascriptResourceReference.get());
	}
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain("position", options.getJavaScriptOptions());
	}
	
	/*---- Options section ---*/
	
	/**
	 * Defines which position on the target element to align the positioned 
	 * element against: "horizontal vertical" alignment. A single value such as 
	 * "right" will default to "right center", "top" will default to "center top" 
	 * (following CSS convention). Acceptable values: "top", "center", "bottom", 
	 * "left", "right". Example: "left top" or "center center"
	 * @param at
	 * @return the instance
	 */
	public PositionBehavior setAt(Position at) {
		options.putLiteral("at", at.toString());
		return this;
	}
	
	/**
	 * @return the at option
	 */
	public Position getAt() {
		String value = options.getLiteral("at");
		return value == null ? null : Position.getPosition(value);
	}
	
	/**
	 * Applies the bgiframe plugin when set to true. Only applies when bgiframe 
	 * is actually loaded, nothing happens otherwise.
	 * @param bgiframe
	 * @return the instance
	 */
	public PositionBehavior setBgiframe(boolean bgiframe) {
		options.put("bgiframe", bgiframe);
		return this;
	}
	
	/**
	 * @return the bgiframe option
	 */
	public boolean isBgiframe() {
		if(options.containsKey("bgiframe")){
			return options.getBoolean("bgiframe");
		}
		
		return true;
	}
	
	/**
	 * When the positioned element overflows the window in some direction, move 
	 * it to an alternative position. Similar to my and at, this accepts a 
	 * single value or a pair for horizontal/vertical, eg. "flip", "fit", 
	 * "fit flip", "fit none".
	 * <ul>
	 * <li>flip: to the opposite side and the collision detection is run again to 
	 * see if it will fit. If it won't fit in either position, the center option 
	 * should be used as a fall back.</li>
	 * <li>fit: so the element keeps in the desired direction, but is re-positioned 
	 * so it fits.</li>
	 * <li>none: not do collision detection.</li>
	 * </ul>
	 * @param collision
	 * @return the instance
	 */
	public PositionBehavior setCollision(Collision collision) {
		options.putLiteral("collision", collision.toString());
		return this;
	}
	
	/**
	 * @return the collision option
	 */
	public Collision getCollision() {
		String value = options.getLiteral("collision");
		return value == null ? null : Collision.getCollision(value);
	}
	
	/**
	 * Defines which position on the element being positioned to align with the 
	 * target element: "horizontal vertical" alignment. A single value such as 
	 * "right" will default to "right center", "top" will default to "center top" 
	 * (following CSS convention). Acceptable values: "top", "center", "bottom", 
	 * "left", "right". Example: "left top" or "center center"
	 * @param my
	 * @return the instance
	 */
	public PositionBehavior setMy(Position my) {
		options.putLiteral("my", my.toString());
		return this;
	}
	
	/**
	 * @return the my option
	 */
	public Position getMy() {
		String value = options.getLiteral("my");
		return value == null ? null : Position.getPosition(value);
	}
	
	/**
	 * Element to position against. You can use a browser event object contains 
	 * pageX and pageY values. Example: "#top-menu"
	 * @param of
	 * @return the instance
	 */
	public PositionBehavior setOf(String of) {
		options.putLiteral("of", of);
		return this;
	}
	
	/**
	 * @return the of option
	 */
	public String getOf() {
		return options.getLiteral("of");
	}
	
	/**
	 * Add these left-top values to the calculated position, eg. "50 50" (left top)
	 *  A single value such as "50" will apply to both.
	 * @param offset
	 * @return the instance
	 */
	public PositionBehavior setOffset(PositionOffset offset) {
		options.put("offset", offset);
		return this;
	}
	
	/**
	 * @return the offset option
	 */
	public PositionOffset getOffset() {
		return (PositionOffset) options.getComplexOption("offset");
	}
	
	/*---- Events section ---*/
	
	/**
	 * When specified the actual property setting is delegated to this callback. 
	 * Receives a single parameter which is a hash of top and left values for 
	 * the position that should be set.
	 * @param by
	 * @return the instance
	 */
	public PositionBehavior setBy(JsScopePositionEvent by) {
		options.put("by", by);
		return this;
	}
}
