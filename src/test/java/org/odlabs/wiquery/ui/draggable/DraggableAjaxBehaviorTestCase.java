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
package org.odlabs.wiquery.ui.draggable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;

/**
 * Test on {@link DraggableAjaxBehavior}
 * 
 * @author Julien Roche
 */
public class DraggableAjaxBehaviorTestCase extends WiQueryTestCase {
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggaable.DraggableAjaxBehavior#statement()}
	 * .
	 */
	@Test
	@Ignore("This testcase tests if the wicket team decides to change the AJAX JS or not")
	public void testStatement() {
		InnerDraggableAjaxBehavior draggableAjaxBehavior = new InnerDraggableAjaxBehavior();
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(draggableAjaxBehavior);

		WebPage webPage = new InnerPage();
		webPage.add(component);
		String genrateAjaxStatment = draggableAjaxBehavior.statement().render()
				.toString();
		String expectedAjaxStatement = "$('#anId').draggable({stop: function(event, ui) {\n\t"
				+ "var isInvalid = $.ui.draggable._dragElementDroppedWasInvalid(this);var wcall=wicketAjaxGet('?wicket:interface=:0:anId::IActivePageBehaviorListener:0:&wicket:ignoreIfNotActive=true&dragType=stop&dragStatus='+isInvalid,function() { }.bind(this),function() { }.bind(this), function() {return Wicket.$('anId') != null;}.bind(this));\n"
				+ "}, start: function(event, ui) {\n\t"
				+ "var wcall=wicketAjaxGet('?wicket:interface=:0:anId::IActivePageBehaviorListener:0:&wicket:ignoreIfNotActive=true&dragType=start',function() { }.bind(this),function() { }.bind(this), function() {return Wicket.$('anId') != null;}.bind(this));\n"
				+ "}, drag: function(event, ui) {\n\t"
				+ "var wcall=wicketAjaxGet('?wicket:interface=:0:anId::IActivePageBehaviorListener:0:&wicket:ignoreIfNotActive=true&dragType=drag',function() { }.bind(this),function() { }.bind(this), function() {return Wicket.$('anId') != null;}.bind(this));\n"
				+ "}, revert: function(dropped) {\n\t"
				+ "return $.ui.draggable._dragElementWasDropped(this, dropped);\n"
				+ "}});";
		assertNotNull(draggableAjaxBehavior.getDraggableBehavior());
		assertEquals(genrateAjaxStatment, expectedAjaxStatement);
	}

	private class InnerDraggableAjaxBehavior extends DraggableAjaxBehavior {
		private static final long serialVersionUID = 1L;

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.odlabs.wiquery.ui.draggable.DraggableAjaxBehavior#onDrag(org.apache.wicket.Component,
		 *      org.apache.wicket.ajax.AjaxRequestTarget)
		 */
		@Override
		public void onDrag(Component component,
				AjaxRequestTarget ajaxRequestTarget) {
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.odlabs.wiquery.ui.draggable.DraggableAjaxBehavior#onStart(org.apache.wicket.Component,
		 *      org.apache.wicket.ajax.AjaxRequestTarget)
		 */
		@Override
		public void onStart(Component component,
				AjaxRequestTarget ajaxRequestTarget) {
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.odlabs.wiquery.ui.draggable.DraggableAjaxBehavior#onStop(org.apache.wicket.Component,
		 *      org.apache.wicket.ajax.AjaxRequestTarget)
		 */
		@Override
		public void onStop(Component draggedComponent,
				AjaxRequestTarget ajaxRequestTarget) {
		}
	}

	private class InnerPage extends WebPage {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	}
}
