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
package org.odlabs.wiquery.ui.resizable;

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test on {@link ResizableAjaxBehavior}
 * @author Julien Roche
 *
 */
public class ResizableAjaxBehaviorTestCase extends TestCase {
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
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableAjaxBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		InnerResizableAjaxBehavior resizableAjaxBehavior = new InnerResizableAjaxBehavior();
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(resizableAjaxBehavior);
		
		WebPage webPage = new InnerPage();
		webPage.add(component);
		
		String genrateAjaxStatment = resizableAjaxBehavior.statement().render().toString();
		String expectedAjaxStatement = "$('#anId').resizable({stop: function(event, ui) {\n\t"+
	"var wcall=wicketAjaxGet('?wicket:interface=:0:anId::IActivePageBehaviorListener:0:&wicket:ignoreIfNotActive=true&resizedHeight='+ui.size.height+'&resizedWidth='+ui.size.width,function() { }.bind(this),function() { }.bind(this), function() {return Wicket.$('anId') != null;}.bind(this));\n"+
"}});";
		Assert.assertNotNull(resizableAjaxBehavior.getResizableBehavior());
		Assert.assertEquals(genrateAjaxStatment, expectedAjaxStatement);
	}

	private class InnerResizableAjaxBehavior extends ResizableAjaxBehavior {
		private static final long serialVersionUID = 1L;

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.resizable.ResizableAjaxBehavior#onResize(int, int, org.apache.wicket.ajax.AjaxRequestTarget)
		 */
		@Override
		public void onResize(int height, int width,
				AjaxRequestTarget ajaxRequestTarget) {
		}	
	}
	
	private class InnerPage extends WebPage {
		
	}
}
