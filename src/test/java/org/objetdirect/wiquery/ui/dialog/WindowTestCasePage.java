package org.objetdirect.wiquery.ui.dialog;

import org.objetdirect.wiquery.commons.WickextTestPage;
import org.objetdirect.wiquery.core.commons.WickextPlugin;
import org.objetdirect.wiquery.ui.dialog.Window;

public class WindowTestCasePage extends WickextTestPage {

	private Window window;
	
	public WindowTestCasePage() {
		super();
		window = new Window("window");
		add(window);
	}

	public Window getWindow() {
		return window;
	}

	@Override
	public WickextPlugin getPluginToTest() {
		return window;
	}
	
}
