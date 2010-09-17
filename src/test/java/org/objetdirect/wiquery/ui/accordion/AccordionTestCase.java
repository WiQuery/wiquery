package org.objetdirect.wiquery.ui.accordion;

import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.apache.wicket.protocol.http.documentvalidation.Tag;
import org.apache.wicket.protocol.http.documentvalidation.TextContent;
import org.objetdirect.wiquery.commons.WickextTestCase;
import org.objetdirect.wiquery.commons.WickextTestPage;
import org.objetdirect.wiquery.ui.accordion.Accordion;

public class AccordionTestCase extends WickextTestCase {

	@Override
	protected void appendBody(Tag body) {
		// accordions are a bunch of UL, LI elements
		Tag accordionContainer = new Tag("ul");
		body.addExpectedChild(accordionContainer);
		accordionContainer
			.addExpectedChild(new Tag("li")
						.addExpectedChild(new Tag("h4")
							.addExpectedChild(new Tag("a")
								.addExpectedChild(new TextContent("Panel 1 title"))
							)
						)
						.addExpectedChild(new Tag("p")
								.addExpectedChild(new TextContent("Content1"))
						)
		);
		accordionContainer
			.addExpectedChild(new Tag("li")
					.addExpectedChild(new Tag("h4")
						.addExpectedChild(new Tag("a")
							.addExpectedChild(new TextContent("Panel 2 title"))
						)
					)
					.addExpectedChild(new Tag("p")
							.addExpectedChild(new TextContent("Content2"))
					)
		);		
	}

	@Override
	protected void appendNeededResources(Tag headTag) {
		addExpectedJavaScriptResource(headTag, new JavascriptResourceReference(
				Accordion.class, "ui.accordion.js"));
		
	}

	@Override
	protected Class<? extends WickextTestPage> getTestPage() {
		return AccordionTestCasePage.class;
	}

}
