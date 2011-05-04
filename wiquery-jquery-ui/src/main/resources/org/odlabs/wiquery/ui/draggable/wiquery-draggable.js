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

(function($) {
	/**
	 * Function to determine what to do after an element was dropped :
	 * must we revert the position or must we let the element ?
	 * @param draggableElement Element that is dragged
	 * @param dropped Element was dropped or not ? If false, that means an invalid drop.
	 */
	$.ui.draggable._dragElementWasDropped = function(draggableElement, dropped) {
		var revert = $(draggableElement).data('draggable').wiRevert;
		 $(draggableElement).data('draggable').wiIsInvalid = 
			 dropped != undefined && !dropped;
		
		return (revert == "invalid" && !dropped) 
			|| (revert == "valid" && dropped) 
			|| revert === true
			|| ($.isFunction(revert) && revert.call(draggableElement, dropped));
	};
	
	/**
	 * Function to determine if the dropped was invalid
	 * @param draggableElement Element that is dragged
	 */
	$.ui.draggable._dragElementDroppedWasInvalid = function(draggableElement) {
		var wiIsInvalid = $(draggableElement).data('draggable').wiIsInvalid;
		
		return wiIsInvalid != undefined && wiIsInvalid;
	};
})(jQuery);
