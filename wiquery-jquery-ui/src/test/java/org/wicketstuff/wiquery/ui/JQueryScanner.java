package org.wicketstuff.wiquery.ui;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.tools.ToolErrorReporter;

public class JQueryScanner {

	public JQueryScanner(FileReader in) {
		final ErrorReporter reporter = new ToolErrorReporter(true);
		final CompilerEnvirons env = new CompilerEnvirons();
		final Parser parser = new Parser(env, reporter);

		try {
			parser.parse(in, null, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final void main(String[] args) {
		if (args.length == 0)
			return;

		File zipfile = new File(args[0]);
		if (zipfile == null || !zipfile.exists() || !zipfile.isDirectory())
			return;

		for (File file : zipfile.listFiles()) {
			if (file.getName().startsWith("ui.")
					&& file.getName().endsWith(".json")) {
				FileReader reader = null;
				try {
					reader = new FileReader(file);
					JQueryScanner scanner = new JQueryScanner(reader);

				} catch (Exception e) {

				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (Exception e) {
						}
					}

				}
			}
		}
	}
}
