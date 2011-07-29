package org.odlabs.wiquery.core;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;

public class WiQueryCoreInitializer implements IWiQueryInitializer {

	private static final long serialVersionUID = 1L;

	public void init(Application application, WiQuerySettings wiQuerySettings) {
		// add JQueryCoreRenderingListener which will automatically add the
		// jquery core to the page
		// duplicates will be ignored.
		wiQuerySettings.addListener(JQueryCoreRenderingListener.getInstance());

		// set HeaderResponseDecorator in order to render the jQuery statements
		// into the head of the page.
		application.setHeaderResponseDecorator(new IHeaderResponseDecorator() {

			private static final long serialVersionUID = 1L;

			public IHeaderResponse decorate(IHeaderResponse response) {
				return new WiQueryDecoratingHeaderResponse(response);
			}
		});
	}
}
