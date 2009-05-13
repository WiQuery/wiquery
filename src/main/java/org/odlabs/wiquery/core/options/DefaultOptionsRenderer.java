package org.odlabs.wiquery.core.options;

public class DefaultOptionsRenderer implements IOptionsRenderer {

	private static IOptionsRenderer instance;
	
	public static IOptionsRenderer get() {
		if (instance == null) {
			instance = new DefaultOptionsRenderer();
		}
		return instance;
	}

	private DefaultOptionsRenderer() {
		
	}
	
	public void renderAfter(StringBuilder stringBuilder) {
		stringBuilder.append("}");
	}

	public void renderBefore(StringBuilder stringBuilder) {
		stringBuilder.append("{");
	}

	public CharSequence renderOption(String name, Object value, boolean isLast) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append(name)
				.append(": ")
				.append(value);
		if (!isLast) {
			stringBuilder.append(", ");
		}
		return stringBuilder;
	}

}
