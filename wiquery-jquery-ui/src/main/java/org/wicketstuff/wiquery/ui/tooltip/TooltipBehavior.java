package org.wicketstuff.wiquery.ui.tooltip;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.core.util.string.JavaScriptUtils;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;
import org.wicketstuff.wiquery.core.javascript.JsStatement;
import org.wicketstuff.wiquery.core.options.Options;
import org.wicketstuff.wiquery.ui.JQueryUIJavaScriptResourceReference;
import org.wicketstuff.wiquery.ui.options.EffectOptionObject;
import org.wicketstuff.wiquery.ui.position.PositionOptions;

public class TooltipBehavior extends Behavior
{
	private static final long serialVersionUID = 1L;

	protected IModel<String> model;

	protected Options options = new Options();

	/**
	 * Default constructor
	 */
	public TooltipBehavior()
	{
		super();
		options = new Options();
	}

	@Override
	public void bind(Component component)
	{
		component.setOutputMarkupId(true);
	}

	@Override
	public void detach(Component component)
	{
		super.detach(component);
		options.detach();
	}

	public TooltipBehavior setContent(String content)
	{
		options.put("content", "\"" + formatContent(content) + "\"");
		return this;
	}

	public TooltipBehavior setContent(IModel<String> model)
	{
		this.model = model;
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
			.render(JavaScriptHeaderItem.forReference(JQueryUIJavaScriptResourceReference.get()));
		response.render(OnDomReadyHeaderItem.forScript(statement(component).render()));
	}

	private JsStatement statement(Component component)
	{
		if (model != null)
		{
			setContent(model.getObject());
		}

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

	private String formatContent(String content)
	{
		String formattedContent = "";
		if (content != null && !content.isEmpty())
		{
			formattedContent = JavaScriptUtils
				.escapeQuotes(Strings.toMultilineMarkup(Strings.toEscapedUnicode(content)))
				.toString();
		}
		return formattedContent;
	}
}