/*
 * Copyright (c) 2010 WiQuery team
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
package org.wicketstuff.wiquery.tester;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.wicketstuff.wiquery.tester.matchers.ComponentMatcher;
import org.wicketstuff.wiquery.tester.matchers.ComponentTypeMatcher;

public class WiQueryTester extends WicketTester
{
	public <X extends Component> X first(final MarkupContainer root, ComponentMatcher matcher)
	{
		CollectingVisitor<X> visitor = new CollectingVisitor<X>(matcher, true);
		root.visitChildren(visitor);
		return visitor.matchedComponents.isEmpty() ? null : visitor.matchedComponents.get(0);
	}

	public <X extends Component> List<X> all(final MarkupContainer root, ComponentMatcher matcher)
	{
		CollectingVisitor<X> visitor = new CollectingVisitor<X>(matcher);
		root.visitChildren(visitor);
		return visitor.matchedComponents;
	}

	public <X extends Component> X first(final MarkupContainer root, Class<X> componentType)
	{
		CollectingVisitor<X> visitor =
			new CollectingVisitor<X>(new ComponentTypeMatcher(componentType), true);
		root.visitChildren(visitor);
		return visitor.matchedComponents.get(0);
	}

	public <X extends Component> List<X> all(MarkupContainer root, Class<X> componentType)
	{
		ComponentMatcher matcher = new ComponentTypeMatcher(componentType);
		CollectingVisitor<X> visitor = new CollectingVisitor<X>(matcher);
		root.visitChildren(componentType, visitor);
		return visitor.matchedComponents;
	}

	/**
	 * Sets the value on the input control.
	 */
	public void setValue(FormComponent< ? > input, String value)
	{
		getLastRequest().setParameter(input.getInputName(), value);
	}

	public RepeatingView getRepeatingView(String path)
	{
		Page renderedPage = getLastRenderedPage();
		assertComponent(path, RepeatingView.class);
		RepeatingView rv = (RepeatingView) renderedPage.get(path);
		return rv;
	}

	public ListView< ? > getListView(String path)
	{
		Page renderedPage = getLastRenderedPage();
		assertComponent(path, ListView.class);
		ListView< ? > rv = (ListView< ? >) renderedPage.get(path);
		return rv;
	}

	public List<IHeaderContributor> getHeaderContributors()
	{
		Page renderedPage = getLastRenderedPage();
		final List<IHeaderContributor> contributors = new ArrayList<IHeaderContributor>();

		renderedPage.visitChildren(new IVisitor<Component, Void>()
		{
			@Override
			public void component(Component component, IVisit<Void> visit)
			{
				for (Behavior behavior : component.getBehaviors())
					if (behavior instanceof IHeaderContributor)
						contributors.add((IHeaderContributor) behavior);

			}
		});

		return contributors;
	}
}