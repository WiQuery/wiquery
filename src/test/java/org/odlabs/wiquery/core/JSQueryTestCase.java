package org.odlabs.wiquery.core;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

public class JSQueryTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			JSQueryTestCase.class);

	@Test
	public void testJSQuerySyntax() {
		JsQuery jsq = new JsQuery();
		JsStatement jst = jsq.$(".sample").chain("css", "'foo'", "'bar'");
		String expected = "$('.sample').css('foo', 'bar');";
		String generated = jst.render().toString();
		log.info(expected);
		log.info(generated);
		Assert.assertEquals(generated, expected);
		
		jsq = new JsQuery();
		expected = "$('.sample').ready(function() {\n\talert('foo');\n\talert('bar');\n});";
		jst = jsq.$(".sample").ready(new JsScope() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void execute(JsScopeContext scopeContext) {
				scopeContext.append("alert('foo')");
				scopeContext.append("alert('bar')");
			}
		
		});
		generated = jst.render().toString();
		log.info(expected);
		log.info(generated);
		Assert.assertEquals(generated, expected);

		expected = "$('.sample').each(function() {\n" +
			"\t$(this).css('foo');\n" +
			"\t$(this).css('bar');\n" +
			"});";
		
		jsq = new JsQuery();
		jst = jsq.$(".sample").each(new JsScope() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void execute(JsScopeContext scopeContext) {
				scopeContext.self().chain("css", "'foo'");
				scopeContext.self().chain("css", "'bar'");
			}
		
		});
		generated = jst.render().toString();
		log.info(expected);
		log.info(generated);
		Assert.assertEquals(generated, expected);
		
		// statements chaining
		jsq = new JsQuery();
		jsq.$(".foo").chain("css", "bar");
		JsStatement jss = new JsStatement();
		jss.append(jsq.getStatement().render());
		log.info(jss.render().toString());
	}
}
