package org.odlabs.wiquery.core.events;

/**
 * @deprecated will be removed in 1.2
 * $Id$
 *             <p>
 *             TODO insert comments here
 *             </p>
 * @author lionel
 * @since 1.0
 */
public enum WiQueryEvent implements EventLabel {

	READY;

	public String getEventLabel() {
		return "wiquery:ready";
	}

}
