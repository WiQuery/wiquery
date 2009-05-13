package org.odlabs.wiquery.core.options;

import java.io.Serializable;

public interface IOptionsRenderer extends Serializable {

	void renderBefore(StringBuilder stringBuilder);

	CharSequence renderOption(String name, Object value, boolean isLast);
	
	void renderAfter(StringBuilder stringBuilder);

}
