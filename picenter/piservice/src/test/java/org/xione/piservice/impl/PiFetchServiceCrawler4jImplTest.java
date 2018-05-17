package org.xione.piservice.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xione.piservice.PiFetchService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PiFetchServiceCrawler4jImplTest {

	@Autowired
	private PiFetchService piFetchService;

	@Test
	public void test() {
		piFetchService.fetchPictureByKey("");
	}

}
