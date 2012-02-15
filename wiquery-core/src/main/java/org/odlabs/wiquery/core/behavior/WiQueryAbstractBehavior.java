package org.odlabs.wiquery.core.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.string.Strings;
import org.odlabs.wiquery.core.IWiQueryPlugin;
import org.odlabs.wiquery.core.javascript.JsStatement;

// abstract entry point for all wiquery behaviors
@SuppressWarnings("deprecation")
public abstract class WiQueryAbstractBehavior extends Behavior implements IWiQueryPlugin
{
	private static final long serialVersionUID = 6498661892490365888L;

	private Component component;

	public WiQueryAbstractBehavior()
	{
	}

	/**
	 * Bind this handler to the given component.
	 * 
	 * @param hostComponent
	 *            the component to bind to
	 */
	@Override
	public final void bind(final Component hostComponent)
	{
		if (hostComponent == null)
		{
			throw new IllegalArgumentException("Argument hostComponent must be not null");
		}

		if (component != null)
		{
			throw new IllegalStateException("this kind of handler cannot be attached to "
				+ "multiple components; it is already attached to component " + component
				+ ", but component " + hostComponent + " wants to be attached too");
		}

		component = hostComponent;

		// call the callback
		onBind();
	}

	/**
	 * Called when the component was bound to it's host component. You can get the bound
	 * host component by calling getComponent.
	 */
	protected void onBind()
	{
		getComponent().setOutputMarkupId(true);
	}

	/**
	 * @return the binded component
	 */
	public Component getComponent()
	{
		return component;
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
	public void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);

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
	 * {@link #renderHead(Component, IHeaderResponse)} to render your statement.
	 * </p>
	 * <p>
	 * For backward compatibility we render the output of this function in an
	 * {@link OnDomReadyHeaderItem} if it is not empty. For your convenience this abstract
	 * class returns null so that nothing is rendered.
	 * <p>
	 * <p>
	 * If you decide to use this class and to override this function then make sure you do
	 * call this class' {@link #renderHead(Component, IHeaderResponse)} otherwise no
	 * statement will be rendered.
	 * <p>
	 * 
	 * @return The {@link JsStatement} corresponding to this component.
	 * @deprecated use {@link #renderHead(Component, IHeaderResponse)} to render your
	 *             statement.
	 */
	@Deprecated
	@Override
	public JsStatement statement()
	{
		return null;
	}
}