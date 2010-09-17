package org.objetdirect.wiquery.ui.dialog;

import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.apache.wicket.protocol.http.documentvalidation.Tag;
import org.apache.wicket.protocol.http.documentvalidation.TextContent;
import org.objetdirect.wiquery.commons.WickextTestCase;
import org.objetdirect.wiquery.commons.WickextTestPage;
import org.objetdirect.wiquery.ui.dialog.Window;
import org.objetdirect.wiquery.ui.draggable.DraggableJavaScriptResourceLocator;
import org.objetdirect.wiquery.ui.resizable.ResizableJavaScriptResourceReference;

public class WindowTestCase extends WickextTestCase {

	@Override
	protected void appendBody(Tag body) {
		 Tag windowDiv = new Tag("div");
		 body.addExpectedChild(windowDiv);
		 windowDiv.addExpectedChild(new TextContent("Some sample text"));
	}

	@Override
	protected void appendNeededResources(Tag headTag) {
		// Window UI Script import
		addExpectedJavaScriptResource(headTag, new JavascriptResourceReference(
				Window.class, "ui.dialog.js"));
		// Window UI dependencies (draggable and resizable)
		addExpectedJavaScriptResource(headTag,
				new DraggableJavaScriptResourceLocator());
		addExpectedJavaScriptResource(headTag,
				new ResizableJavaScriptResourceReference());
	}

	@Override
	protected Class<? extends WickextTestPage> getTestPage() {
		return WindowTestCasePage.class;
	}

}
