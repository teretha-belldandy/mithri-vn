package org.xione.piutil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlFetchUtil {

	private static final Logger LOG = LoggerFactory.getLogger(HtmlFetchUtil.class);

	public static String fetchUrlContent(String strUrl, String strPostRequest, int maxLength) {
		StringBuffer buffer = new StringBuffer();
		System.setProperty("sun.net.client.defaultConnectTimeout", "5000");
		System.setProperty("sun.net.client.defaultReadTimeout", "5000");
		try {
			URL newUrl = new URL(strUrl);
			HttpURLConnection hConnect = (HttpURLConnection) newUrl.openConnection();
			// POST方式的额外数据
			if (strPostRequest.length() > 0) {
				hConnect.setDoOutput(true);
				OutputStreamWriter out = new OutputStreamWriter(hConnect.getOutputStream());
				out.write(strPostRequest);
				out.flush();
				out.close();
			}
			// 读取内容
			BufferedReader rd = new BufferedReader(new InputStreamReader(hConnect.getInputStream()));
			int ch;
			for (int length = 0; (ch = rd.read()) > -1 && (maxLength <= 0 || length < maxLength); length++)
				buffer.append((char) ch);
			String s = buffer.toString();
			s.replaceAll("//&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");

			rd.close();
			hConnect.disconnect();
			
		} catch (Exception e) {
			return null; // return "错误:读取网页失败！";
		}
		return buffer.toString().trim();
	}

}
