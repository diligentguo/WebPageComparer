package com.diligentguo.entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;

import com.diligentguo.comparer.Comparer;
import com.diligentguo.comparer.imple.DefaultComparer;
import com.diligentguo.queryinfo.QueryInfoProvider;
import com.diligentguo.queryinfo.imple.BaiduQueryInfoProvider;
import com.diligentguo.queryinfo.imple.GoogleQueryInfoProvider;
import com.diligentguo.segmenter.WordSegmenter;
import com.diligentguo.segmenter.imple.DefaultWordSegmenter;
import com.diligentguo.spider.WebSpider;

public class ComparerTester {
	
	Logger logger = Logger.getLogger(ComparerTester.class);

	private WebSpider webSpider = new WebSpider();
	private QueryInfoProvider baiduQueryInfoProvider = new BaiduQueryInfoProvider();
	private QueryInfoProvider googleQueryInfoProvider = new GoogleQueryInfoProvider();
	
	private WordSegmenter segmenter = new DefaultWordSegmenter();
	private Comparer comparer = new DefaultComparer();
	
	/**
	 * 
	 * @param keyword
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public double doCompare(String keyword) {
		try {
			/**
			 * Query different content from different Web Spider
			 */
			String baiduContent = webSpider.getWebContent(baiduQueryInfoProvider,keyword);
			
			String googleContent = webSpider.getWebContent(googleQueryInfoProvider,keyword);
			
			/**
			 * Get segment of queried content from segmenter. then
			 * we will receive queried content's vocabulary Set
			 */
			List baiduContentList = segmenter.getSegment(baiduContent);
			List googleContentList = segmenter.getSegment(googleContent);
			
			if (baiduContentList != null && googleContentList != null) {
				/**
				 * calculate similarity based on queried content's vocabulary Set
				 */
				return comparer.compare(baiduContentList, googleContentList);
			}
			
		} catch (IOException e) {
			logger.error("doCompare error",e);
		}
		
		return -1;
	}
	
	public static void main(String args[]) {
		
		Logger logger = Logger.getLogger(ComparerTester.class);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String keyword = "";
		
		try {
			
			ComparerTester main = new ComparerTester();
			
			logger.info("Please input keyword:");

			while((keyword = br.readLine())!= null) {
				
				logger.info("System begin to work,please wait...");
				
				double similarity = main.doCompare(keyword);
				logger.info("keyword is:" + keyword);
				logger.info("The similarity of query is:" + similarity + "%");
				logger.info("Please input keyword:");
			}
			
		} catch (IOException e) {
			logger.error("Main method error", e);
		}
		
	}

}
