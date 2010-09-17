package org.objetdirect.wiquery.commons;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.protocol.http.documentvalidation.HtmlDocumentValidator;
import org.apache.wicket.protocol.http.documentvalidation.Tag;
import org.apache.wicket.protocol.http.documentvalidation.TextContent;
import org.apache.wicket.util.tester.WicketTester;
import org.objetdirect.wiquery.core.commons.CoreJavaScriptResourceReference;
import org.objetdirect.wiquery.core.commons.WickextPlugin;
import org.objetdirect.wiquery.core.javascript.JsQuery;
import org.objetdirect.wiquery.core.javascript.JsScope;
import org.objetdirect.wiquery.ui.commons.WickextUIPlugin;
import org.objetdirect.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.objetdirect.wiquery.ui.dialog.WindowTestCasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public abstract class WickextTestCase {

	protected static final Logger log = LoggerFactory
			.getLogger(WickextTestCase.class);

	protected WicketTester wicketTester;

	protected WickextTestWebApplication wickextTestWebApplication;

	@BeforeClass
	public void setUp() {
		wickextTestWebApplication = new WickextTestWebApplication(
				WindowTestCasePage.class);
		wicketTester = new WicketTester(wickextTestWebApplication);
	}

	protected abstract Class<? extends WickextTestPage> getTestPage();

	protected abstract void appendNeededResources(Tag headTag);

	protected abstract void appendBody(Tag body);

	@Test
	public void testWickextPlugin() throws Exception {
		log.info("Starting Wickext plugin test ------------------------------");
		// starts the wicket tester
		wicketTester.startPage(getTestPage());
		// getting generated response
		String document = wicketTester.getServletResponse().getDocument();
		log.info("Wicket output: --------------------------------------------");
		log.info(document);

		// using wicket HTML validation tools to validate that all needed
		// JavaScript, CSS resources are imported and that the JavaScript code
		// was well generated
		HtmlDocumentValidator validator = new HtmlDocumentValidator();
		Tag html = new Tag("html");
		Tag head = new Tag("head");
		html.addExpectedChild(head);

		// --- shared JavaScript resources import check ---
		WickextPlugin wickextPlugin = ((WickextTestPage) wicketTester
				.getLastRenderedPage()).getPluginToTest();

		// WickeXt core JavaScript is mandatory
		addExpectedJavaScriptResource(head, CoreJavaScriptResourceReference
				.get());
		if (wickextPlugin.getClass().isAnnotationPresent(WickextUIPlugin.class)) {
			// WickeXt UI core JavaScript is mandatory if the tested component
			// is part of the UI components
			addExpectedJavaScriptResource(head,
					CoreUIJavaScriptResourceReference.get());
			// WickeXt UI CSS theme reference
			addExpectedCssResource(head, wickextTestWebApplication
					.getThemeResource());
		}

		// component's specific resources check
		appendNeededResources(head);

		// Wickext component's JavaScript statement verification
		Tag scriptTag = new Tag("script");
		head.addExpectedChild(scriptTag);

		JsQuery query = new JsQuery();
		String statement = query.document().ready(
			JsScope.quickScope(wickextPlugin.statement().render())
		).render()
		 .toString().replace("\n", "").replace("{", "[{]").replace("}",
						"[}]").replace("(", "[(]").replace("'", "[']").replace(
						"$", "[$]").replace("#", "[#]").replace(")", "[)]");

		log.info("Statement to check: ---------------------------------------");
		log.info(statement);

		scriptTag.addExpectedChild(new TextContent(statement));

		// component HTML structure to check
		Tag body = new Tag("body");
		html.addExpectedChild(body);
		appendBody(body);

		validator.addRootElement(html);
		log.info("Starting document validation: -----------------------------");
		boolean documentValid = validator.isDocumentValid(document);
		Assert.assertTrue(documentValid);
	}

	protected void addExpectedJavaScriptResource(Tag head,
			ResourceReference reference) {
		Tag scriptTag = new Tag("script");
		String expectedPathSuffix = ".*"
				+ reference.getScope().getCanonicalName() + "/"
				+ reference.getName();
		scriptTag.addExpectedAttribute("src", expectedPathSuffix);
		head.addExpectedChild(scriptTag);
	}

	protected void addExpectedCssResource(Tag head, ResourceReference reference) {
		Tag linkTag = new Tag("link");
		String expectedPathSuffix = ".*"
				+ reference.getScope().getCanonicalName() + "/"
				+ reference.getName();
		linkTag.addExpectedAttribute("href", expectedPathSuffix);
		head.addExpectedChild(linkTag);
	}

	protected Component getComponent() {
		WickextPlugin wickextPlugin = ((WickextTestPage) wicketTester
				.getLastRenderedPage()).getPluginToTest();
		return (Component) wickextPlugin;
	}

}
