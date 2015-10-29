package org.wicketstuff.wiquery.sortable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.wiquery.sortable.model.SimpleSortableElement;
import org.wicketstuff.wiquery.template.MainTemplate;

/**
 * @author Stephane Gleizes
 */
public class SortablePage extends MainTemplate {

	private static final long serialVersionUID = 1L;
	
	private List<SimpleSortableElement> elementList = Arrays.asList(
			new SimpleSortableElement(1, "Lorem ipsum"),
			new SimpleSortableElement(2, "dolor sit"),
			new SimpleSortableElement(3, "consectetur"),
			new SimpleSortableElement(4, "adipiscing elit"),
			new SimpleSortableElement(5, "Etiam ante"),
			new SimpleSortableElement(6, "erat, lacinia"),
			new SimpleSortableElement(7, "in consectetur et"),
			new SimpleSortableElement(8, "faucibus quis"),
			new SimpleSortableElement(9, "neque"));

	public SortablePage(final PageParameters parameters) {
		super(parameters);

		IModel<List<SimpleSortableElement>> listModel = new LoadableDetachableModel<List<SimpleSortableElement>>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<SimpleSortableElement> load() {
				Collections.sort(elementList, new Comparator<SimpleSortableElement>() {
					@Override
					public int compare(SimpleSortableElement o1, SimpleSortableElement o2) {
						return o1.getPosition().compareTo(o2.getPosition());
					}
				});
				return elementList;
			}
		};
		
		add(new SortablePanel("panel", listModel));
	}

	@Override
	protected Class<? extends WebPage> getMenuPage() {
		return SortablePage.class;
	}
}
