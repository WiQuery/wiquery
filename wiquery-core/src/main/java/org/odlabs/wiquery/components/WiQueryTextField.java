package org.odlabs.wiquery.components;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;
import org.odlabs.wiquery.core.IWiQueryPlugin;
import org.odlabs.wiquery.core.javascript.JsStatement;

@SuppressWarnings("deprecation")
public abstract class WiQueryTextField<T> extends TextField<T> implements IWiQueryPlugin
{
	private static final long serialVersionUID = 1L;

	public WiQueryTextField(String id)
	{
		super(id);
	}

	public WiQueryTextField(String id, Class<T> type)
	{
		super(id, type);
	}

	public WiQueryTextField(String id, IModel<T> model)
	{
		super(id, model);
	}

	public WiQueryTextField(String id, IModel<T> model, Class<T> type)
	{
		super(id, model, type);
	}

	/**
	 * <p>
	 * Since wicket 6.0 {@link #statement()} is no longer needed, nearly all of WiQuery
	 * core's inner workings have been ported to Wicket 6.0. Use
	 * {@link #renderHead(Component, IHeaderResponse)} to render your statement.
	 * </p>
	 * <p>
	 * For backward compatibility we render the output of this function in an
	 * {@link OnDomReadyHeaderItem} if it is not empty. For your convenience this abstract
	 * class returns null so that nothing is rendered.
	 * <p>
	 */
	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);

		JsStatement statement = statement();
		if (statement != null)
		{
			String statementString = statement.render().toString();
			if (!Strings.isEmpty(statementString))
			{
				response.render(OnDomReadyHeaderItem.forScript(statementString));
			}
		}
	}

	/**
	 * <p>
	 * Since wicket 6.0 this function is no longer needed, nearly all of WiQuery core's
	 * inner workings have been ported to Wicket 6.0. Use
	 * {@link #renderHead(IHeaderResponse)} to render your statement.
	 * </p>
	 * <p>
	 * For backward compatibility we render the output of this function in an
	 * {@link OnDomReadyHeaderItem} if it is not empty. For your convenience this abstract
	 * class returns null so that nothing is rendered.
	 * <p>
	 * <p>
	 * If you decide to use this class and to override this function then make sure you do
	 * call this class' {@link #renderHead(IHeaderResponse)} otherwise no statement will
	 * be rendered.
	 * <p>
	 * 
	 * @return The {@link JsStatement} corresponding to this component.
	 * @deprecated use {@link #renderHead(IHeaderResponse)} to render your statement.
	 */
	@Deprecated
	@Override
	public JsStatement statement()
	{
		return null;
	}
}
