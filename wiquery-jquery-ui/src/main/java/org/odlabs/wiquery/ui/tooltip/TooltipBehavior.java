package org.odlabs.wiquery.ui.tooltip;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.core.util.string.JavaScriptUtils;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.options.EffectOptionObject;
import org.odlabs.wiquery.ui.position.PositionOptions;
import org.odlabs.wiquery.ui.tooltip.TooltipJavaScriptResourceReference;

public class TooltipBehavior extends Behavior
{
	private static final long serialVersionUID = 1L;

	protected Options options = new Options();

	private Component component;

	/**
	 * Default constructor
	 */
	public TooltipBehavior()
	{
		super();
		options = new Options();
	}

	@Override
	public void bind(Component _component)
	{
		this.component = _component;
		this.component.setOutputMarkupId(true);
	}

	@Override
	public void detach(Component component)
	{
		super.detach(component);
		options.detach();
	}

	public TooltipBehavior setContent(String content)
	{
		options.put("content", "\"" + JavaScriptUtils.escapeQuotes(content).toString() + "\"");
		return this;
	}

	public TooltipBehavior setDisabled(boolean disabled)
	{
		options.put("disabled", disabled);
		return this;
	}

	public TooltipBehavior setHide(boolean hide)
	{
		options.put("hide", hide);
		return this;
	}

	public TooltipBehavior setHide(int duration)
	{
		options.put("hide", duration);
		return this;
	}

	public TooltipBehavior setHide(String effect)
	{
		options.put("hide", effect);
		return this;
	}

	public TooltipBehavior setHide(EffectOptionObject effectOptions)
	{
		options.put("hide", effectOptions);
		return this;
	}

	public TooltipBehavior setItems(String items)
	{
		options.put("items", "\"" + items + "\"");
		return this;
	}

	public TooltipBehavior setPositionOptions(PositionOptions positionOptions)
	{
		options.put("position", positionOptions);
		return this;
	}

	public TooltipBehavior setShow(boolean show)
	{
		options.put("show", show);
		return this;
	}

	public TooltipBehavior setShow(int duration)
	{
		options.put("show", duration);
		return this;
	}

	public TooltipBehavior setShow(String effect)
	{
		options.put("show", effect);
		return this;
	}

	public TooltipBehavior setShow(EffectOptionObject effectOptions)
	{
		options.put("show", effectOptions);
		return this;
	}

	public TooltipBehavior setTooltipClass(String tooltipClass)
	{
		options.put("tooltipClass", "\"" + tooltipClass + "\"");
		return this;
	}

	public TooltipBehavior setTrack(boolean track)
	{
		options.put("track", track);
		return this;
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);
		response
			.render(JavaScriptHeaderItem.forReference(TooltipJavaScriptResourceReference.get()));
		response.render(OnDomReadyHeaderItem.forScript(statement().render()));
	}

	private JsStatement statement()
	{
		if (options.isEmpty())
		{
			return new JsStatement().$(component).chain("tooltip");
		}

		if (options.containsKey("content") && !options.containsKey("items"))
		{
			setItems("#" + component.getMarkupId());
		}

		return new JsStatement().$(component).chain("tooltip", options.getJavaScriptOptions());
	}
}