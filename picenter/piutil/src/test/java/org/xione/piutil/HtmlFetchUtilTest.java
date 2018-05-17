package org.xione.piutil;

import org.junit.Test;

public class HtmlFetchUtilTest {

	@Test
	public void testFetchUrlContent() {
		String str = HtmlFetchUtil.fetchUrlContent("http://wwww.baidu.com", "", 0);
		System.out.println(str);
	}

}
