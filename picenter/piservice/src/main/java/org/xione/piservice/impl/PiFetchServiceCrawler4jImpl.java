package org.xione.piservice.impl;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.xione.piservice.PiFetchService;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

@Service
public class PiFetchServiceCrawler4jImpl extends WebCrawler implements PiFetchService {

	private static final Logger LOG = LoggerFactory.getLogger(PiFetchServiceCrawler4jImpl.class);

	private static final String HOST = "http://www.baidu.com/";
	private static final Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");

	@Override
	public void fetchPictureByKey(String key) {
		LOG.info("PiFetchServiceCrawler4jImpl_fetchPictureByKey:{}", key);
		try {
			fetchAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void fetchPictureByKey(String url, String key) {
		// TODO Auto-generated method stub

	}

	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase(); // 得到小写的url
		return !FILTERS.matcher(href).matches() // 正则匹配，过滤掉我们不需要的后缀文件
				&& href.startsWith(HOST); // url必须是http://www.java1234.com/开头，规定站点
	}

	private void fetchAll() throws Exception {
		String crawlStorageFolder = "./crawl/"; // 定义爬虫数据存储位置
		int numberOfCrawlers = 7; // 定义7个爬虫，也就是7个线程

		CrawlConfig config = new CrawlConfig(); // 定义爬虫配置
		config.setCrawlStorageFolder(crawlStorageFolder); // 设置爬虫文件存储位置

		PageFetcher pageFetcher = new PageFetcher(config); // 实例化页面获取器
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); // 实例化爬虫机器人配置,比如可以设置user-agent

		// 实例化爬虫机器人对目标服务器的配置，每个网站都有一个robots.txt文件,规定了该网站哪些页面可以爬，哪些页面禁止爬，该类是对robots.txt规范的实现
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		// 实例化爬虫控制器
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer); // 实例化爬虫控制器
		controller.addSeed(HOST); // 配置爬虫种子页面，就是规定的从哪里开始爬，可以配置多个种子页面

		controller.start(PiFetchServiceCrawler4jImpl.class, numberOfCrawlers); // 启动爬虫，爬虫从此刻开始执行爬虫任务，根据以上配置
	}

}
