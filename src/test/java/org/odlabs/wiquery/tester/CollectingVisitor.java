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
package org.odlabs.wiquery.tester;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.odlabs.wiquery.tester.matchers.ComponentMatcher;

public class CollectingVisitor<X extends Component> implements
		IVisitor<Component, Void> {
	private ComponentMatcher matcher;

	private boolean findFirst = false;

	List<X> matchedComponents = new ArrayList<X>();

	public CollectingVisitor(ComponentMatcher matcher) {
		this.matcher = matcher;
	}

	public CollectingVisitor(ComponentMatcher matcher, boolean findFirst) {
		this.matcher = matcher;
		this.findFirst = findFirst;
	}

	public List<X> getMatchedComponents() {
		return matchedComponents;
	}

	@SuppressWarnings("unchecked")
	public void component(Component component, IVisit<Void> visit) {
		if (matcher.matches(component)) {
			matchedComponents.add((X) component);
			if(findFirst)
				visit.stop();
		}
	}
}
