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
package org.odlabs.wiquery.core.javascript.helper;

import org.odlabs.wiquery.core.events.EventLabel;
import org.odlabs.wiquery.core.events.FormEvent;
import org.odlabs.wiquery.core.events.KeyboardEvent;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.events.StateEvent;
import org.odlabs.wiquery.core.javascript.ChainableStatement;
import org.odlabs.wiquery.core.javascript.DefaultChainableStatement;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsUtils;

/**
 * $Id$
 * <p>
 * Helper to bind javascript scope and events
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0.2
 */
public abstract class EventsHelper {
	/**
	 * Binds a handler to one or more events (like click) for each matched element.
	 * @param eventLabel Event
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement bind(EventLabel eventLabel, JsScope jsScope) {
		return new DefaultChainableStatement("bind", 
				JsUtils.quotes(eventLabel.getEventLabel()), 
				jsScope.render());
	}
	
	/**
	 * Triggers the blur event of each matched element.
	 * @return the jQuery code
	 */
	public static ChainableStatement blur() {
		return new DefaultChainableStatement(StateEvent.BLUR.getEventLabel());
	}
	
	/**
	 * Bind a function to the blur event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement blur(JsScope jsScope) {
		return new DefaultChainableStatement(StateEvent.BLUR.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Triggers the change event of each matched element.
	 * @return the jQuery code
	 */
	public static ChainableStatement change() {
		return new DefaultChainableStatement(StateEvent.CHANGE.getEventLabel());
	}
	
	/**
	 * Bind a function to the change event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement change(JsScope jsScope) {
		return new DefaultChainableStatement(StateEvent.CHANGE.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Triggers the click event of each matched element.
	 * @return the jQuery code
	 */
	public static ChainableStatement click() {
		return new DefaultChainableStatement(MouseEvent.CLICK.getEventLabel());
	}
	
	/**
	 * Bind a function to the click event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement click(JsScope jsScope) {
		return new DefaultChainableStatement(MouseEvent.CLICK.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Triggers the dblclick event of each matched element.
	 * @return the jQuery code
	 */
	public static ChainableStatement dblclick() {
		return new DefaultChainableStatement(MouseEvent.DBLCLICK.getEventLabel());
	}
	
	/**
	 * Bind a function to the dblclick event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement dblclick(JsScope jsScope) {
		return new DefaultChainableStatement(MouseEvent.DBLCLICK.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * This does the opposite of live, it removes a bound live event.
	 * @param eventLabel Event
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement die(EventLabel eventLabel, JsScope jsScope) {
		return new DefaultChainableStatement("die", 
				JsUtils.quotes(eventLabel.getEventLabel()), 
				jsScope.render());
	}
	
	/**
	 * Triggers the error event of each matched element.
	 * @return the jQuery code
	 */
	public static ChainableStatement error() {
		return new DefaultChainableStatement(StateEvent.ERROR.getEventLabel());
	}
	
	/**
	 * Bind a function to the error event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement error(JsScope jsScope) {
		return new DefaultChainableStatement(StateEvent.ERROR.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Triggers the focus event of each matched element.
	 * @return the jQuery code
	 */
	public static ChainableStatement focus() {
		return new DefaultChainableStatement(StateEvent.FOCUS.getEventLabel());
	}
	
	/**
	 * Bind a function to the focus event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement focus(JsScope jsScope) {
		return new DefaultChainableStatement(StateEvent.FOCUS.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Simulates hovering (moving the mouse on, and off, an object). This is a 
	 * custom method which provides an 'in' to a frequent task.
	 * @param over Scope to use on "over"
	 * @param out Scope to use on "out"
	 * @return the jQuery code
	 */
	public static ChainableStatement hover(JsScope over, JsScope out) {
		return new DefaultChainableStatement("hover", over.render(), out.render());
	}
	
	/**
	 * Triggers the keydown event of each matched element.
	 * @return the jQuery code
	 */
	public static ChainableStatement keydown() {
		return new DefaultChainableStatement(KeyboardEvent.KEYDOWN.getEventLabel());
	}
	
	/**
	 * Bind a function to the keydown event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement keydown(JsScope jsScope) {
		return new DefaultChainableStatement(KeyboardEvent.KEYDOWN.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Triggers the keypress event of each matched element.
	 * @return the jQuery code
	 */
	public static ChainableStatement keypress() {
		return new DefaultChainableStatement(KeyboardEvent.KEYPRESS.getEventLabel());
	}
	
	/**
	 * Bind a function to the keypress event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement keypress(JsScope jsScope) {
		return new DefaultChainableStatement(KeyboardEvent.KEYPRESS.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Triggers the keyup event of each matched element.
	 * @return the jQuery code
	 */
	public static ChainableStatement keyup() {
		return new DefaultChainableStatement(KeyboardEvent.KEYUP.getEventLabel());
	}
	
	/**
	 * Bind a function to the keyup event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement keyup(JsScope jsScope) {
		return new DefaultChainableStatement(KeyboardEvent.KEYUP.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Binds a handler to an event (like click) for all current - and future - matched element.
	 * @param eventLabel Event
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement live(EventLabel eventLabel, JsScope jsScope) {
		return new DefaultChainableStatement("live", 
				JsUtils.quotes(eventLabel.getEventLabel()), 
				jsScope.render());
	}
	
	/**
	 * Bind a function to the load event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement load(JsScope jsScope) {
		return new DefaultChainableStatement(StateEvent.LOAD.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Bind a function to the mousedown event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement mousedown(JsScope jsScope) {
		return new DefaultChainableStatement(MouseEvent.MOUSEDOWN.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Bind a function to the mouseenter event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement mouseenter(JsScope jsScope) {
		return new DefaultChainableStatement(MouseEvent.MOUSEENTER.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Bind a function to the mouseleave event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement mouseleave(JsScope jsScope) {
		return new DefaultChainableStatement(MouseEvent.MOUSELEAVE.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Bind a function to the mousemove event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement mousemove(JsScope jsScope) {
		return new DefaultChainableStatement(MouseEvent.MOUSEMOVE.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Bind a function to the mouseout event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement mouseout(JsScope jsScope) {
		return new DefaultChainableStatement(MouseEvent.MOUSEOUT.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Bind a function to the mouseover event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement mouseover(JsScope jsScope) {
		return new DefaultChainableStatement(MouseEvent.MOUSEOVER.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Bind a function to the mouseup event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement mouseup(JsScope jsScope) {
		return new DefaultChainableStatement(MouseEvent.MOUSEUP.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Binds a handler to one or more events to be executed once for each matched element.
	 * @param eventLabel Event
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement one(EventLabel eventLabel, JsScope jsScope) {
		return new DefaultChainableStatement("one", 
				JsUtils.quotes(eventLabel.getEventLabel()), 
				jsScope.render());
	}
	
	/**
	 * Binds a function to be executed whenever the DOM is ready to be traversed and manipulated.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement ready(JsScope jsScope) {
		return new DefaultChainableStatement("ready", jsScope.render());
	}
	
	/**
	 * Bind a function to the scroll event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement scroll(JsScope jsScope) {
		return new DefaultChainableStatement(MouseEvent.SCROLL.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Triggers the select event of each matched element.
	 * @return the jQuery code
	 */
	public static ChainableStatement select() {
		return new DefaultChainableStatement(StateEvent.SELECT.getEventLabel());
	}
	
	/**
	 * Bind a function to the select event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement select(JsScope jsScope) {
		return new DefaultChainableStatement(StateEvent.SELECT.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Triggers the submit event of each matched element.
	 * @return the jQuery code
	 */
	public static ChainableStatement submit() {
		return new DefaultChainableStatement(FormEvent.SUBMIT.getEventLabel());
	}
	
	/**
	 * Bind a function to the submit event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement submit(JsScope jsScope) {
		return new DefaultChainableStatement(FormEvent.SUBMIT.getEventLabel(), 
				jsScope.render());
	}
	
	/**
	 * Toggle among two function calls every other click.
	 * @param jsScope Scope to use
	 * @param jsScope2 Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement toggle(JsScope jsScope, JsScope jsScope2) {
		return new DefaultChainableStatement("toggle", jsScope.render(), jsScope2.render());
	}
	
	/**
	 * Toggle among two or more function calls every other click.
	 * @param jsScope Scope to use
	 * @param jsScope2 Scope to use
	 * @param jsScopes Additional functions
	 * @return the jQuery code
	 */
	public static ChainableStatement toggle(JsScope jsScope, JsScope jsScope2, JsScope... jsScopes) {
		CharSequence[] args = null;
		
		if(jsScopes != null && jsScopes.length > 0){
			args = new CharSequence[jsScopes.length + 2];
			args[0] = jsScope.render();
			args[1] = jsScope2.render();
			
			Integer index = 2;
			for(JsScope scope : jsScopes){
				args[index] = scope.render();
				index++;
			}
			
			return new DefaultChainableStatement("toggle", args);
		}
		
		return toggle(jsScope, jsScope2);
	}
	
	/**
	 * Trigger an event on every matched element.
	 * @param eventLabel Event
	 * @return the jQuery code
	 */
	public static ChainableStatement trigger(EventLabel eventLabel) {
		return new DefaultChainableStatement("trigger", 
				JsUtils.quotes(eventLabel.getEventLabel()));
	}
	
	/**
	 * Trigger an event on every matched element.
	 * @param eventLabel Event
	 * @param data Data for the scope
	 * @return the jQuery code
	 */
	public static ChainableStatement trigger(EventLabel eventLabel, CharSequence... data) {
		return new DefaultChainableStatement("trigger", 
				JsUtils.quotes(eventLabel.getEventLabel()),
				JsUtils.array(data));
	}
	
	/**
	 * Triggers all bound event handlers on an element (for a specific event type) 
	 * WITHOUT executing the browser's default actions, bubbling, or live events.
	 * @param eventLabel Event
	 * @return the jQuery code
	 */
	public static ChainableStatement triggerHandler(EventLabel eventLabel) {
		return new DefaultChainableStatement("triggerHandler", 
				JsUtils.quotes(eventLabel.getEventLabel()));
	}
	
	/**
	 * Triggers all bound event handlers on an element (for a specific event type) 
	 * WITHOUT executing the browser's default actions, bubbling, or live events.
	 * @param eventLabel Event
	 * @param data Data for the scope
	 * @return the jQuery code
	 */
	public static ChainableStatement triggerHandler(EventLabel eventLabel, CharSequence... data) {
		return new DefaultChainableStatement("triggerHandler", 
				JsUtils.quotes(eventLabel.getEventLabel()),
				JsUtils.array(data));
	}
	
	/**
	 * This does the opposite of bind, it removes bound events from each of the matched elements.
	 * @param eventLabel Event
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement unbind(EventLabel eventLabel, JsScope jsScope) {
		return new DefaultChainableStatement("unbind", 
				JsUtils.quotes(eventLabel.getEventLabel()), 
				jsScope.render());
	}
	
	/**
	 * Bind a function to the unload event of each matched element.
	 * @param jsScope Scope to use
	 * @return the jQuery code
	 */
	public static ChainableStatement unload(JsScope jsScope) {
		return new DefaultChainableStatement(StateEvent.UNLOAD.getEventLabel(), 
				jsScope.render());
	}
}
