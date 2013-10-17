package org.odlabs.wiquery.slider;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.IntegerItemOptions;
import org.odlabs.wiquery.ui.slider.AjaxSlider;
import org.odlabs.wiquery.ui.slider.Slider.Orientation;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 * 
 */
public class SliderPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private Label coordinates;

	private int x = 10;

	private int y = 10;

	private int[] values = { 1, 30, 80 };

	private Label label;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param id
	 */
	public SliderPanel(String id) {
		super(id);
		AjaxSlider slider = new AjaxSlider("slider", 1, 30);
		slider.setAnimate(true);
		slider.setStep(1);
		slider.setValue(x);
		slider.setAjaxStopEvent(new AjaxSlider.ISliderAjaxEvent() {

			private static final long serialVersionUID = 1L;

			@Override
			public void onEvent(AjaxRequestTarget target, AjaxSlider slider,
					int value, int[] values) {
				SliderPanel.this.x = value;
				target.add(SliderPanel.this.coordinates);
			}

		});
		add(slider);

		AjaxSlider slider1 = new AjaxSlider("slider1", 1, 30);
		slider1.setAnimate(true);
		slider1.setStep(1);
		slider1.setValue(y);
		slider1.setAjaxStopEvent(new AjaxSlider.ISliderAjaxEvent() {

			private static final long serialVersionUID = 1L;

			@Override
			public void onEvent(AjaxRequestTarget target, AjaxSlider slider,
					int value, int[] values) {
				SliderPanel.this.y = value;
				target.add(SliderPanel.this.coordinates);
			}

		});
		slider1.setOrientation(Orientation.VERTICAL);
		add(slider1);

		coordinates = new Label("coordinates",
				new AbstractReadOnlyModel<String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String getObject() {
						return x + "," + y;
					}
				});
		coordinates.setOutputMarkupId(true);

		add(coordinates);

		AjaxSlider multiSlider = new AjaxSlider("multiSlider", 1, 100);
		ArrayItemOptions<IntegerItemOptions> values = new ArrayItemOptions<IntegerItemOptions>();
		values.add(new IntegerItemOptions(1));
		values.add(new IntegerItemOptions(30));
		values.add(new IntegerItemOptions(80));
		multiSlider.setValues(values);

		multiSlider.setAjaxStopEvent(new AjaxSlider.ISliderAjaxEvent() {

			private static final long serialVersionUID = 1L;

			@Override
			public void onEvent(AjaxRequestTarget target, AjaxSlider slider,
					int value, int[] values) {
				SliderPanel.this.values = values;
				target.add(SliderPanel.this.label);
			}

		});
		add(multiSlider);

		label = new Label("values", new AbstractReadOnlyModel<String>() {

			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return SliderPanel.this.values[0] + ","
						+ SliderPanel.this.values[1] + ","
						+ SliderPanel.this.values[2];
			}

		});
		label.setOutputMarkupId(true);
		add(label);
	}
}
