package org.wicketstuff.wiquery.autocomplete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.wiquery.core.options.ArrayItemOptions;
import org.wicketstuff.wiquery.core.options.IListItemOption;
import org.wicketstuff.wiquery.core.options.LiteralOption;
import org.wicketstuff.wiquery.ui.autocomplete.Autocomplete;
import org.wicketstuff.wiquery.ui.autocomplete.AutocompleteAjaxComponent;
import org.wicketstuff.wiquery.ui.autocomplete.AutocompleteSource;

/**
 * @author Stephane Gleizes
 */
public class AutocompletePanel extends Panel
{

	private static final long serialVersionUID = 1L;

	private static final IListItemOption[] AUTOCOMPLETE_SOURCE = new IListItemOption[] {
			new LiteralOption("January"), new LiteralOption("February"), new LiteralOption("March"),
			new LiteralOption("April"), new LiteralOption("May"), new LiteralOption("June"),
			new LiteralOption("July"), new LiteralOption("August"), new LiteralOption("September"),
			new LiteralOption("October"), new LiteralOption("November"),
			new LiteralOption("December") };

	private final IModel<String> autocompleteFieldModel = new Model<>(null);

	private final IModel<String> autocompleteAjaxFieldModel = new Model<>(null);

	public AutocompletePanel(String id)
	{
		super(id);

		final Form<Void> form = new Form<>("form");
		add(form);

		// Autocomplete source
		ArrayItemOptions<IListItemOption> source = new ArrayItemOptions<>();
		source.addAll(Arrays.asList(AUTOCOMPLETE_SOURCE));

		// Autocomplete field
		final Autocomplete<String> autocompleteField = new Autocomplete<>("autocompleteField",
			autocompleteFieldModel);
		autocompleteField.setAutoFocus(true);
		autocompleteField.setSource(new AutocompleteSource(source));
		autocompleteField.setLabel(Model.of("Autocomplete on months"));
		form.add(autocompleteField);

		// Month model label
		form.add(new Label("monthLabel", autocompleteFieldModel));

		// AJAX Autocomplete field
		final AutocompleteAjaxComponent<String> autocompleteAjaxField = new AutocompleteAjaxComponent<String>(
			"autocompleteAjaxField", autocompleteAjaxFieldModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public List<String> getValues(String term)
			{
				List<String> values = new ArrayList<>(AUTOCOMPLETE_SOURCE.length);
				for (IListItemOption option : AUTOCOMPLETE_SOURCE)
				{
					if (option instanceof LiteralOption)
					{
						LiteralOption literal = (LiteralOption)option;
						if (literal.getValue().toLowerCase().contains(term.toLowerCase()))
						{
							values.add(literal.getValue());
						}
					}
				}
				return values;
			}

			@Override
			public String getValueOnSearchFail(String input)
			{
				return null;
			}
		};
		autocompleteAjaxField.setLabel(Model.of("AJAX autocomplete on months"));
		form.add(autocompleteAjaxField);

		// AJAX month model label
		form.add(new Label("ajaxMonthLabel", autocompleteAjaxFieldModel));
	}

	@Override
	protected void onDetach()
	{
		super.onDetach();
		autocompleteFieldModel.detach();
		autocompleteAjaxFieldModel.detach();
	}
}
