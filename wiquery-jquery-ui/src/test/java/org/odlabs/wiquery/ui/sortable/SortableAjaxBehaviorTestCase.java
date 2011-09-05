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

import static org.junit.Assert.*;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;

/**
 * Test on {@link SortableAjaxBehavior}
 * 
 * @author Julien Roche
 */
public class SortableAjaxBehaviorTestCase extends WiQueryTestCase
{

	/**
	 * Test method for {@link SortableAjaxBehavior#statement()} .
	 */
	@Test
	public void testStatement()
	{
		InnerSortableAjaxBehavior sortableAjaxBehavior = new InnerSortableAjaxBehavior();
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(sortableAjaxBehavior);

		WebPage webPage = new InnerPage();
		webPage.add(component);
		String generateAjaxStatment = sortableAjaxBehavior.statement().render().toString();
		String expectedAjaxStatement =
			"$('#anId').sortable({receive: function(event, ui) {\n\t"
				+ "var wcall=wicketAjaxGet('wicket/page?0-0.IBehaviorListener.1-anId&sortedType=receive&sortedIndex='+$(this).find(':data(sortable-item)').index(ui.item)+'&sortedId='+ $(ui.item).attr('id')+'&sortedParentId='+ $(ui.sender).attr('id'),function() { }.bind(this),function() { }.bind(this), function() {return Wicket.$('anId') != null;}.bind(this));\n"
				+ "}, remove: function(event, ui) {\n\t"
				+ "var wcall=wicketAjaxGet('wicket/page?0-0.IBehaviorListener.1-anId&sortedType=remove&sortedId='+ $(ui.item).attr('id'),function() { }.bind(this),function() { }.bind(this), function() {return Wicket.$('anId') != null;}.bind(this));\n"
				+ "}, update: function(event, ui) {\n\t"
				+ "var wcall=wicketAjaxGet('wicket/page?0-0.IBehaviorListener.1-anId&sortedType=update&sortedIndex='+$(this).find(':data(sortable-item)').index(ui.item)+'&sortedId='+ $(ui.item).attr('id'),function() { }.bind(this),function() { }.bind(this), function() {return Wicket.$('anId') != null;}.bind(this));\n"
				+ "}});";
		assertNotNull(sortableAjaxBehavior.getSortableBehavior());
		assertEquals(expectedAjaxStatement, generateAjaxStatment);
	}

	private class InnerSortableAjaxBehavior extends SortableAjaxBehavior<Component>
	{
		private static final long serialVersionUID = 1L;

		@Override
		public void onReceive(Component sortedComponent, int index,
				Component parentSortedComponent, AjaxRequestTarget ajaxRequestTarget)
		{
		}

		@Override
		public void onRemove(Component sortedComponent, AjaxRequestTarget ajaxRequestTarget)
		{
		}

		@Override
		public void onUpdate(Component sortedComponent, int index,
				AjaxRequestTarget ajaxRequestTarget)
		{
		}
	}

	private class InnerPage extends WebPage
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	}
}
