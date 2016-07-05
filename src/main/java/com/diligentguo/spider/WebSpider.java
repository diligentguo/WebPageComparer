package com.diligentguo.spider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.diligentguo.queryinfo.QueryInfoProvider;

/**
 * Realize Web Spider based on Jsoup open source project.
 * See more in https://jsoup.org/
 * 
 * @author Administrator
 *
 */
public class WebSpider {
	
	private static final String AGENT = "Mozilla/5.0";
	private static final int TIMEOUT = 5000;
	
	
	public String parseDoc(QueryInfoProvider infoProvider,Document doc) {
		String ret = "";
		if (doc != null) {
			/**
			 * Jsoup provider a mechanism to get DOM element as DOM,CSS,
			 * Jquery-like methods.
			 * 
			 * BaiduQueryInfoProvider and BaiduQueryInfoProvider
			 * will has different parse key
			 */
			Elements resultList = doc.select(infoProvider.getParseKey());
			ret = resultList.text();			
		}
		return ret;
	}
	
	
	public String getWebContent(QueryInfoProvider infoProvider,String keyword) throws IOException {
		/**
		 * Construct the query URL, BaiduQueryInfoProvider and BaiduQueryInfoProvider
		 * will has different query addr
		 */
		String queryURL = infoProvider.getQueryAddrHeader() + keyword;
		
		/**
		 * Simulate browser to query URL, and jsoup will return webContent as DOM
		 */
		Document doc = Jsoup.connect(queryURL).userAgent(AGENT)
				.timeout(TIMEOUT).get();
		
		/**
		 * parse web content from DOM tree
		 */
		return parseDoc(infoProvider,doc);
	}
}
