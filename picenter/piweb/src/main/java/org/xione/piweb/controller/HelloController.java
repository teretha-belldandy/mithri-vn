package org.xione.piweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

	@RequestMapping("/hello")
	public String sayHi() {
		LOG.info("helloCOntroller_sayHi");
		return "Hello mv" + System.currentTimeMillis();
	}
	
}
