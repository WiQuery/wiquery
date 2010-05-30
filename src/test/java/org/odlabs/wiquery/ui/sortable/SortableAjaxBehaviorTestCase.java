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
package org.odlabs.wiquery.ui.sortable;

import junit.framework.TestCase;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.utils.WiQueryWebApplication;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test on {@link SortableAjaxBehavior}
 * @author Julien Roche
 *
 */
public class SortableAjaxBehaviorTestCase extends TestCase {
	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
		new WicketTester(new WiQueryWebApplication() {
			@Override
			public Class<? extends Page> getHomePage() {
				return null;
			}
		});
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.selectable.SortableAjaxBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		InnerSortableAjaxBehavior sortableAjaxBehavior = new InnerSortableAjaxBehavior();
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(sortableAjaxBehavior);
		
		WebPage webPage = new InnerPage();
		webPage.add(component);
		
		Assert.assertNotNull(sortableAjaxBehavior.getSortableBehavior());
		Assert.assertEquals(sortableAjaxBehavior.statement().render().toString(), 
				"$('#anId').sortable({receive: function(event, ui) {\n\twicketAjaxGet('?wicket:interface=:0:anId::IActivePageBehaviorListener:0:&wicket:ignoreIfNotActive=true&sortedType=receive&sortedIndex='+$(this).find(':data(sortable-item)').index(ui.item)+'&sortedId='+ $(ui.item).attr('id')+'&sortedParentId='+ $(ui.sender).attr('id'), null,null, function() {return true;});\n}, remove: function(event, ui) {\n\twicketAjaxGet('?wicket:interface=:0:anId::IActivePageBehaviorListener:0:&wicket:ignoreIfNotActive=true&sortedType=remove&sortedId='+ $(ui.item).attr('id'), null,null, function() {return true;});\n}, update: function(event, ui) {\n\twicketAjaxGet('?wicket:interface=:0:anId::IActivePageBehaviorListener:0:&wicket:ignoreIfNotActive=true&sortedType=update&sortedIndex='+$(this).find(':data(sortable-item)').index(ui.item)+'&sortedId='+ $(ui.item).attr('id'), null,null, function() {return true;});\n}});");
	}

	private class InnerSortableAjaxBehavior extends SortableAjaxBehavior<Component> {
		private static final long serialVersionUID = 1L;

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.sortable.SortableAjaxBehavior#onReceive(org.apache.wicket.Component, int, org.apache.wicket.Component, org.apache.wicket.ajax.AjaxRequestTarget)
		 */
		@Override
		public void onReceive(Component sortedComponent, int index,
				Component parentSortedComponent,
				AjaxRequestTarget ajaxRequestTarget) {
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.sortable.SortableAjaxBehavior#onRemove(org.apache.wicket.Component, org.apache.wicket.ajax.AjaxRequestTarget)
		 */
		@Override
		public void onRemove(Component sortedComponent,
				AjaxRequestTarget ajaxRequestTarget) {
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.sortable.SortableAjaxBehavior#onUpdate(org.apache.wicket.Component, int, org.apache.wicket.ajax.AjaxRequestTarget)
		 */
		@Override
		public void onUpdate(Component sortedComponent, int index,
				AjaxRequestTarget ajaxRequestTarget) {
		}	
	}
	
	private class InnerPage extends WebPage {
		
	}
}
