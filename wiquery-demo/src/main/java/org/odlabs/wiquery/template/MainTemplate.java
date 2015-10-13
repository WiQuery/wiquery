package org.odlabs.wiquery.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.odlabs.wiquery.accordion.AccordionPage;
import org.odlabs.wiquery.autocomplete.AutocompletePage;
import org.odlabs.wiquery.button.ButtonPage;
import org.odlabs.wiquery.datepicker.DatePickerPage;
import org.odlabs.wiquery.dialog.DialogPage;
import org.odlabs.wiquery.home.HomePage;
import org.odlabs.wiquery.progressbar.ProgressBarPage;
import org.odlabs.wiquery.resizable.ResizePage;
import org.odlabs.wiquery.slider.SliderPage;
import org.odlabs.wiquery.sortable.SortablePage;
import org.odlabs.wiquery.tab.TabsPage;
import org.odlabs.wiquery.template.style.BootstrapCdnCssResourceReference;
import org.odlabs.wiquery.template.style.StylesCssResourceReference;

public abstract class MainTemplate extends WebPage {

	private static final long serialVersionUID = -1312989780696228848L;
	
	private static final IModel<String> DEMO_TITLE_MODEL = Model.of("WiQuery-demo");
	
	public MainTemplate(PageParameters parameters) {
		super(parameters);
		
		MarkupContainer htmlRootElement = new TransparentWebMarkupContainer("htmlRootElement");
		htmlRootElement.add(AttributeAppender.append("lang", WebSession.get().getLocale().getLanguage()));
		add(htmlRootElement);
		
		MarkupContainer bodyElement = new TransparentWebMarkupContainer("bodyElement");
		add(bodyElement);
		
		add(new Label("headPageTitle", DEMO_TITLE_MODEL));
		
		Link<Void> homePageLink = new BookmarkablePageLink<Void>("homePageLink", HomePage.class);
		homePageLink.setBody(DEMO_TITLE_MODEL);
		add(homePageLink);
		
		// Main navigation list
		add(new ListView<NavigationCategory>("navigation", Arrays.asList(NavigationCategory.values())) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<NavigationCategory> item) {
				final NavigationCategory category = item.getModelObject();
				item.add(new Label("navHeader", category.getLabel()));
				
				item.add(new NavigationMenuItemListView("categoryMenuItems", NavigationMenuItem.getByCategory(category)));
			}
		});
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		
		response.render(CssHeaderItem.forReference(BootstrapCdnCssResourceReference.get()));
		response.render(CssHeaderItem.forReference(StylesCssResourceReference.get()));
	}

	protected abstract Class<? extends WebPage> getMenuPage();
	
	private class NavigationMenuItemListView extends ListView<NavigationMenuItem> {

		private static final long serialVersionUID = -7806328643699956632L;

		public NavigationMenuItemListView(String id, List<NavigationMenuItem> list) {
			super(id, list);
		}
		
		@Override
		protected void populateItem(ListItem<NavigationMenuItem> item) {
			NavigationMenuItem navItem = item.getModelObject();
			Class<? extends Page> navItemPageClass = navItem.getPageClass();
			
			BookmarkablePageLink<Void> navLink = new BookmarkablePageLink<Void>("navLink", navItemPageClass,
					navItem.getPageParameters());
			navLink.add(new Label("navLabel", navItem.getLabelModel()));
			
			if (navItemPageClass.equals(MainTemplate.this.getMenuPage())) {
				item.add(new AttributeAppender("class", "active"));
			}
			
			item.add(navLink);
		}
		
		@Override
		protected void onConfigure() {
			super.onConfigure();
			setVisible(getViewSize() > 0);
		}
	}

	private enum NavigationCategory {
		INTERACTIONS("Interactions"),
		WIDGETS("Widgets"),
		EFFECTS("Effects");
		
		private final String label;
		
		private NavigationCategory(String label) {
			this.label = label;
		}
		
		public String getLabel() {
			return label;
		}
	}
	
	private enum NavigationMenuItem {
		// Interactions
		SORTABLE("Sortable", NavigationCategory.INTERACTIONS, SortablePage.class),
		// Widgets
		ACCORDION("Accordion", NavigationCategory.WIDGETS, AccordionPage.class),
		AUTOCOMPLETE("Autocomplete", NavigationCategory.WIDGETS, AutocompletePage.class),
		BUTTON("Button", NavigationCategory.WIDGETS, ButtonPage.class),
		DATEPICKER("DatePicker", NavigationCategory.WIDGETS, DatePickerPage.class),
		DIALOG("Dialog", NavigationCategory.WIDGETS, DialogPage.class),
		PROGRESSBAR("Progress bar", NavigationCategory.WIDGETS, ProgressBarPage.class),
		RESIZE("Resize", NavigationCategory.WIDGETS, ResizePage.class),
		SLIDER("Slider", NavigationCategory.WIDGETS, SliderPage.class),
		TABS("Tabs", NavigationCategory.WIDGETS, TabsPage.class);
		
		private final IModel<String> labelModel;
		
		private final NavigationCategory category;
		
		private final Class<? extends WebPage> pageClass;
		
		private final PageParameters pageParameters;
		
		private NavigationMenuItem(String label, NavigationCategory category, Class<? extends WebPage> pageClass) {
			this(label, category, pageClass, null);
		}
		
		private NavigationMenuItem(String label, NavigationCategory category, Class<? extends WebPage> pageClass,
				PageParameters pageParameters) {
			this.labelModel = Model.of(label);
			this.pageClass = pageClass;
			this.category = category;
			this.pageParameters = pageParameters;
		}
		
		public IModel<String> getLabelModel() {
			return labelModel;
		}
		
		public Class<? extends WebPage> getPageClass() {
			return pageClass;
		}
		
		public PageParameters getPageParameters() {
			return pageParameters;
		}
		
		public static final List<NavigationMenuItem> getByCategory(NavigationCategory category) {
			List<NavigationMenuItem> results = new ArrayList<NavigationMenuItem>();
			for (NavigationMenuItem menuItem : values()) {
				if (category.equals(menuItem.category)) {
					results.add(menuItem);
				}
			}
			return results;
		}
	}
}