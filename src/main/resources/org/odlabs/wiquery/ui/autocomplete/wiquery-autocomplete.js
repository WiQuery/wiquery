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
	// Namespaces for wiquery
	$.ui.autocomplete.wiquery = {};
	
	/**
	 * Method setting the Wicket model id into the hidden field, on the close event
	 * @param ui UI object
	 * @param hiddenId Identifiant of the hidden field
	 */
	$.ui.autocomplete.wiquery.closeEvent = function(ui, hiddenId) {
		var val = $(ui).attr("value");
		var data = $(ui).data('autocomplete.selected');

		if(val == undefined || val.length <= 0){
			$(ui).data('autocomplete.selected', null);

		} else if(data == undefined) {
		  	var find = null;
		  	var source = $(ui).autocomplete('option', 'source');

		  	$.each(source, function(index, value) {
		  		if(value.label == val){
		  			find = value;
		  			return false;
		  		}
		  	});

		  	$(ui).data('autocomplete.selected', find);
		}
		
		data = $(ui).data('autocomplete.selected');
		$('#' + hiddenId).attr('value', data == undefined ? '' : data.valueId);
	};
	
	/**
	 * Method setting the Wicket model id into the hidden field, on the select event
	 * @param ui UI object
	 * @param hiddenId Identifiant of the hidden field
	 */
	$.ui.autocomplete.wiquery.selectEvent = function(ui, hiddenId) {
		$('#' + hiddenId).attr('value', ui.item.valueId);
		$(ui).data('autocomplete.selected', ui.item);
	};
})(jQuery);