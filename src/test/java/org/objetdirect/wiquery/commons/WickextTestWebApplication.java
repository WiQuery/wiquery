package org.objetdirect.wiquery.commons;

import org.apache.wicket.Page;
import org.objetdirect.wiquery.utils.WickextWebApplication;

public class WickextTestWebApplication extends WickextWebApplication {

	private Class<? extends Page> homePageToTest;
	
	public WickextTestWebApplication(Class<? extends Page> page) {
		super();
		this.homePageToTest = page;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return homePageToTest;
	}

}
