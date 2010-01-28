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
	$.ui.dialog.wiquery = {};
	
	// Creating translation map
	$.ui.dialog.wiquery.regional = {};
	
	/**
	 * Method to create a simple dialog (we create the HTML dynamically and remove it at the end)
	 * @param id Identifiant to use
	 * @param language Language to use
	 * @param title Title of the dialog
	 * @param message Message of the dialog
	 */
	$.ui.dialog.wiquery.simpleDialog = function(id, language, title, message) {
			$.i18n.properties({
					language: language, 
					mode: 'both', 
					name: 
					'wiquery-dialog',
					path: 'i18n/'
			});

			var dialogId = 'dialog' + id;
     		var buttonsMap = new Object();
     		buttonsMap[$.ui.dialog.wiquery.regional[language].okButton] = function(){ $(this).dialog('close'); };
 
     		$('body:first').append("<div id='" + dialogId + "'>" + message + "</div>");
     		$('#' + dialogId).dialog({
			 		title: title, 
					modal: true,
					width: 350,
					buttons: buttonsMap, 
					close: function(){ 
						   $(this).dialog('destroy');
						   $(this).children().remove();
						   $(this).remove(); 
					}
			});
	};
	
	/**
	 * Method to create a wait dialog (we create the HTML dynamically and remove it at the end)
	 * @param id Identifiant to use
	 * @param language Language to use
	 * @param waitImgPath Path for the waiting picture
	 */
	$.ui.dialog.wiquery.waitDialog = function(id, language, waitImgPath) {
			$.i18n.properties({
					language: language, 
					mode: 'both', 
					name: 
					'wiquery-dialog', 
					path: 'i18n/'
			});

			var dialogId = 'dialog' + id;
     		var buttonsMap = new Object();
     		buttonsMap[$.ui.dialog.wiquery.regional[language].okButton] = function(){ $(this).dialog('close'); };
 
     		$('body:first').append("<div id='" + dialogId + "'><table style='height: 100px; width: 100%;'><tbody><tr><td style='text-align: center; vertical-align: middle;'><img src='" + waitImgPath + "' /></td></tr></tbody></table></div>");
			$('#' + dialogId).dialog({
				  title: $.ui.dialog.wiquery.regional[language].waitTitle,
				  closeOnEscape: false, 
				  modal: true, 
				  width: 350, 
				  draggable: false, 
				  resizable: false, 
				  close: function(){ 
				  		 $(this).dialog('destroy');
						 $(this).children().remove(); 
						 $(this).remove(); 
				  },
				  open: function(){ 
				  		$(this).parent('.ui-dialog:first').find('.ui-dialog-titlebar-close').hide(); 
				  }
			});
	};
	
	/**
	 * Method to create a question dialog (we create the HTML dynamically and remove it at the end)
	 * @param id Identifiant to use
	 * @param language Language to use
	 * @param message Message of the dialog
	 * @param questionImgPath Path of the question picture
	 */
	$.ui.dialog.wiquery.questionDialog = function(id, language, message, questionImgPath) {
			$.i18n.properties({
					language: language, 
					mode: 'both', 
					name: 
					'wiquery-dialog', 
					path: 'i18n/'
			});

			var dialogId = 'dialog' + id;
     		var buttonsMap = new Object();
     		buttonsMap[$.ui.dialog.wiquery.regional[language].okButton] = function(){ $(this).dialog('close'); };
 
     		$('body:first').append("<div id='" + dialogId + "'><table style='width: 100%;'><tbody><tr><td style='text-align: center; vertical-align: middle; width: 25%;'><img src='" + questionImgPath + "' /></td><td style='vertical-align: top;'>" + message + "</td></tr></tbody></table></div>");
			$('#' + dialogId).dialog({
				  title: $.ui.dialog.wiquery.regional[language].questionTitle,
				  modal: true, 
				  width: 350, 
				  buttons: buttonsMap, 
				  close: function(){ 
				  		 $(this).dialog('destroy'); 
						 $(this).children().remove();
						 $(this).remove(); }
			});
	};
	
	/**
	 * Method to create an error dialog (we create the HTML dynamically and remove it at the end)
	 * @param id Identifiant to use
	 * @param language Language to use
	 * @param message Message of the dialog
	 * @param errorImgPath Path of the error picture
	 */
	$.ui.dialog.wiquery.errorDialog = function(id, language, message, errorImgPath) {
			$.i18n.properties({
					language: language, 
					mode: 'both', 
					name: 
					'wiquery-dialog', 
					path: 'i18n/'
			});

			var dialogId = 'dialog' + id;
     		var buttonsMap = new Object();
     		buttonsMap[$.ui.dialog.wiquery.regional[language].okButton] = function(){ $(this).dialog('close'); };
 
     		$('body:first').append("<div id='" + dialogId + "'><table style='width: 100%;'><tbody><tr><td style='text-align: center; vertical-align: middle; width: 25%;'><img src='" + errorImgPath + "' /></td><td style='vertical-align: top;'>" + message + "</td></tr></tbody></table></div>");
			$('#' + dialogId).dialog({
				  title: $.ui.dialog.wiquery.regional[language].errorTitle,
				  modal: true, 
				  width: 350, 
				  buttons: buttonsMap, 
				  close: function(){ 
				  		 $(this).dialog('destroy'); 
						 $(this).children().remove();
						 $(this).remove(); }
			});
	};
	
	/**
	 * Method to create a warning dialog (we create the HTML dynamically and remove it at the end)
	 * @param id Identifiant to use
	 * @param language Language to use
	 * @param message Message of the dialog
	 * @param warningImgPath Path of the warning picture
	 */
	$.ui.dialog.wiquery.warningDialog = function(id, language, message, warningImgPath) {
			$.i18n.properties({
					language: language, 
					mode: 'both', 
					name: 
					'wiquery-dialog', 
					path: 'i18n/'
			});

			var dialogId = 'dialog' + id;
     		var buttonsMap = new Object();
     		buttonsMap[$.ui.dialog.wiquery.regional[language].okButton] = function(){ $(this).dialog('close'); };
 
     		$('body:first').append("<div id='" + dialogId + "'><table style='width: 100%;'><tbody><tr><td style='text-align: center; vertical-align: middle; width: 25%;'><img src='" + warningImgPath + "' /></td><td style='vertical-align: top;'>" + message + "</td></tr></tbody></table></div>");
			$('#' + dialogId).dialog({
				  title: $.ui.dialog.wiquery.regional[language].warningTitle,
				  modal: true, 
				  width: 350, 
				  buttons: buttonsMap, 
				  close: function(){ 
				  		 $(this).dialog('destroy'); 
						 $(this).children().remove();
						 $(this).remove(); }
			});
	};
})(jQuery);