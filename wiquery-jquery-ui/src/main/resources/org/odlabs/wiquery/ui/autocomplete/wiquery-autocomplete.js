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
	$.ui.autocomplete.wiquery.changeEvent = function(event, ui, hiddenId, updateUrl) {
		if(ui.item){
			$('#' + hiddenId).val(ui.item.valueId);
			$(event.target).val(ui.item.label);
		}
		else{
			var val = $(event.target).val();
			var data;
	
			if(val == undefined || val.length <= 0){
				data = null;
	
			} else {
			  	var find = null;
			  	var source = $(event.target).autocomplete('option', 'source');
			  	
			  	var matcher = new RegExp( "^" + val + "$", "i" );
	
			  	$.each(source, function(index, value) {
			  		if(value && value.label && value.label.match( matcher )){
			  			find = value;
			  			$(event.target).val(value.label);
			  			return false;
			  		}
			  	});
	
			  	data = find;
			}
			$('#' + hiddenId).val(data == undefined ? '' : data.valueId);
		}
		if(updateUrl){
			var update = $(event.target).serialize() +"&"+ $('#'+hiddenId).serialize();
			var wcall = wicketAjaxPost(updateUrl, update);
		}
	};
})(jQuery);