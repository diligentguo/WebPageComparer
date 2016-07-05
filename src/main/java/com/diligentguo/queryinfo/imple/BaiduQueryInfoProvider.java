package com.diligentguo.queryinfo.imple;

import com.diligentguo.queryinfo.QueryInfoProvider;

public class BaiduQueryInfoProvider implements QueryInfoProvider{

	/**
	 * This is the Baidu's query addr header
	 */
	private static final String QUERY_ADDR_HEADER = "http://www.baidu.com/s?wd=";
	
	/**
	 * This is the CSS class of result DIV tag in Baidu's query webpage
	 */
	private static final String PARSE_KEY = ".result";
	
	public String getQueryAddrHeader() {
		return QUERY_ADDR_HEADER;
	}
	
	public String getParseKey() {
		return PARSE_KEY;
	}

}
