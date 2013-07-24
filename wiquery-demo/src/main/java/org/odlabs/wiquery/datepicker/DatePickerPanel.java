package org.odlabs.wiquery.datepicker;

import java.util.Calendar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.odlabs.wiquery.ui.datepicker.DateOption;
import org.odlabs.wiquery.ui.datepicker.DatePicker;
import org.odlabs.wiquery.ui.datepicker.DatePickerYearRange;

/**
 * @author Stephane Gleizes
 */
public class DatePickerPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	private final IModel<String> rangeDateModel = new Model<String>(null);
	private final IModel<String> withDropDownDateModel = new Model<String>(null);

	public DatePickerPanel(String id) {
		super(id);
		
		final Form<Void> form = new Form<Void>("form");
		add(form);
		
		// Range date picker
		final DatePicker<String> rangeDatePicker = new DatePicker<String>("rangeDatePicker", rangeDateModel);
		//	>	Min date
		Calendar thirtyDaysAgo = Calendar.getInstance();
		thirtyDaysAgo.add(Calendar.DAY_OF_MONTH, -30);
		rangeDatePicker.setMinDate(new DateOption(thirtyDaysAgo.getTime()));
		
		//	>	Max date
		Calendar thirtyDaysFromNow = Calendar.getInstance();
		thirtyDaysFromNow.add(Calendar.DAY_OF_MONTH, 30);
		rangeDatePicker.setMaxDate(new DateOption(thirtyDaysFromNow.getTime()));
		
		//	>	Label
		rangeDatePicker.setLabel(Model.of("Min/max"));
		form.add(rangeDatePicker);
		
		final Label rangeDateLabel = new Label("rangeDateLabel", new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return rangeDateModel.getObject();
			}
		});
		rangeDateLabel.setOutputMarkupId(true);
		form.add(rangeDateLabel);
		
		rangeDatePicker.add(new AjaxFormSubmitBehavior(form, "onchange") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				target.add(rangeDateLabel);
			}
		});
		
		final DatePicker<String> withDropDownDatePicker = new DatePicker<String>("withDropDownDatePicker", withDropDownDateModel);
		withDropDownDatePicker.setChangeMonth(true);
		withDropDownDatePicker.setChangeYear(true);
		withDropDownDatePicker.setYearRange(new DatePickerYearRange(new Short("-20"), new Short("20"), true));
		
		// Label
		withDropDownDatePicker.setLabel(Model.of("Year/month dropdown"));
		form.add(withDropDownDatePicker);
		
		final Label withDropDownDateLabel = new Label("withDropDownDateLabel", new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return withDropDownDateModel.getObject();
			}
		});
		withDropDownDateLabel.setOutputMarkupId(true);
		form.add(withDropDownDateLabel);
		
		withDropDownDatePicker.add(new AjaxFormSubmitBehavior(form, "onchange") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				target.add(withDropDownDateLabel);
			}
		});
	}
	
	@Override
	protected void onDetach() {
		super.onDetach();
		rangeDateModel.detach();
		withDropDownDateModel.detach();
	}
}
