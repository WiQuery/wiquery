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
package org.odlabs.wiquery.ui.droppable;

import junit.framework.TestCase;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test on {@link DroppableAjaxBehavior}
 * @author Julien Roche
 *
 */
public class DroppableAjaxBehaviorTestCase extends TestCase {
	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
		new WicketTester(new WebApplication() {
			@Override
			public Class<? extends Page> getHomePage() {
				return null;
			}
		});
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableAjaxBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		InnerDroppableAjaxBehavior droppableAjaxBehavior = new InnerDroppableAjaxBehavior();
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(droppableAjaxBehavior);
		
		WebPage webPage = new InnerPage();
		webPage.add(component);
		
		String ajaxCallResult = droppableAjaxBehavior.statement().render().toString();
		String expectedResult = "$('#anId').droppable({drop: function(event, ui) {\n\t" +
				"var wcall=wicketAjaxGet('?wicket:interface=:0:anId::IActivePageBehaviorListener:0:&wicket:ignoreIfNotActive=true&droppedId='+ui.draggable[0].id,function() { }.bind(this),function() { }.bind(this), function() {return Wicket.$('anId') != null;}.bind(this));\n"
				+"}});";
		Assert.assertNotNull(droppableAjaxBehavior.getDroppableBehavior());
		Assert.assertEquals(ajaxCallResult, expectedResult);
	}

	private class InnerDroppableAjaxBehavior extends DroppableAjaxBehavior<Component> {
		private static final long serialVersionUID = 1L;

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.droppable.DroppableAjaxBehavior#onDrop(org.apache.wicket.Component, org.apache.wicket.ajax.AjaxRequestTarget)
		 */
		@Override
		public void onDrop(Component droppedComponent,
				AjaxRequestTarget ajaxRequestTarget) {
		}	
	}
	
	private class InnerPage extends WebPage {
		
	}
}
