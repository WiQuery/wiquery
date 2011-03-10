package org.odlabs.wiquery.ui.accordion;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.ui.accordion.Accordion.AccordionTriggerEvent;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonSerialize(include = Inclusion.NON_NULL)
public class AccordionOptions implements Serializable {
	private static final long serialVersionUID = 1L;

	private AccordionAnimated animated = new AccordionAnimated("slide");

	private Boolean autoHeight;

	private AccordionTriggerEvent event = AccordionTriggerEvent.CLICK;

	private Boolean fillSpace;

	private Boolean disabled;

	private AccordionHeader header;

	private boolean clearStyle;

	private boolean collapsible;

	private boolean navigation;

	private JsScope navigationFilter;

	private AccordionIcon icons;

	private AccordionActive active;

	private JsScopeUiEvent change;

	private JsScopeUiEvent changestart;

	public AccordionAnimated getAnimated() {
		return animated;
	}

	public AccordionOptions setAnimated(AccordionAnimated animated) {
		this.animated = animated;
		return this;
	}

	public Boolean getAutoHeight() {
		return autoHeight;
	}

	public AccordionOptions setAutoHeight(Boolean autoHeight) {
		this.autoHeight = autoHeight;
		return this;
	}

	public AccordionTriggerEvent getEvent() {
		return event;
	}

	public AccordionOptions setEvent(AccordionTriggerEvent event) {
		this.event = event;
		return this;
	}

	public Boolean getFillSpace() {
		return fillSpace;
	}

	public AccordionOptions setFillSpace(Boolean fillSpace) {
		this.fillSpace = fillSpace;
		return this;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public AccordionOptions setDisabled(Boolean disabled) {
		this.disabled = disabled;
		return this;
	}

	public AccordionHeader getHeader() {
		return header;
	}

	public AccordionOptions setHeader(AccordionHeader header) {
		this.header = header;
		return this;
	}

	public boolean isClearStyle() {
		return clearStyle;
	}

	public AccordionOptions setClearStyle(boolean clearStyle) {
		this.clearStyle = clearStyle;
		return this;
	}

	public boolean isCollapsible() {
		return collapsible;
	}

	public AccordionOptions setCollapsible(boolean collapsible) {
		this.collapsible = collapsible;
		return this;
	}

	public boolean isNavigation() {
		return navigation;
	}

	public AccordionOptions setNavigation(boolean navigation) {
		this.navigation = navigation;
		return this;
	}

	public JsScope getNavigationFilter() {
		return navigationFilter;
	}

	public AccordionOptions setNavigationFilter(JsScope navigationFilter) {
		this.navigationFilter = navigationFilter;
		return this;
	}

	public AccordionIcon getIcons() {
		return icons;
	}

	public AccordionOptions setIcons(AccordionIcon icons) {
		this.icons = icons;
		return this;
	}

	public AccordionActive getActive() {
		return active;
	}

	public AccordionOptions setActive(AccordionActive active) {
		this.active = active;
		return this;
	}

	public JsScopeUiEvent getChange() {
		return change;
	}

	public AccordionOptions setChange(JsScopeUiEvent change) {
		this.change = change;
		return this;
	}

	public JsScopeUiEvent getChangestart() {
		return changestart;
	}

	public AccordionOptions setChangestart(JsScopeUiEvent changestart) {
		this.changestart = changestart;
		return this;
	}
}
