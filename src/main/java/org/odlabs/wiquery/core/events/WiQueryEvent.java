package org.odlabs.wiquery.core.events;

public enum WiQueryEvent implements EventLabel {

	READY;

	public String getEventLabel() {
		return "wiquery:ready";
	}
	
}
