package com.diligentguo.segmenter.imple;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.diligentguo.segmenter.WordSegmenter;

import edu.stanford.nlp.ie.crf.CRFClassifier;

/**
 * Segment Chinese sentence based on Stanford Word Segmenter.
 * See more in http://nlp.stanford.edu/software/segmenter.shtml
 * 
 * @author Administrator
 *
 */
public class DefaultWordSegmenter implements WordSegmenter {

	Logger logger = Logger.getLogger(WordSegmenter.class);

	
	@SuppressWarnings("rawtypes")
	private CRFClassifier classifier = null;
	
	@SuppressWarnings("rawtypes")
	public DefaultWordSegmenter() {
		
		logger.info("Begin to init DefaultWordSegmenter");
		
		Properties props = new Properties();
		props.setProperty("sighanCorporaDict", "data");
		props.setProperty("serDictionary", "data/dict-chris6.ser.gz");
		props.setProperty("inputEncoding", "UTF-8");
		props.setProperty("sighanPostProcessing", "true");
		
		classifier = new CRFClassifier(props);
		classifier.loadClassifierNoExceptions("data/ctb.gz", props);
		classifier.flags.setProperties(props);
		
		logger.info("Init is Done.");

	}
	
	/**
	 * It will cost some time for classifier to segmentString, in order to 
	 * make this method safe to be used into multi-threading environment,
	 * we add synchronized mechanism
	 */
	@SuppressWarnings("rawtypes")
	public synchronized List getSegment(String text) {
		if (text != null && text.trim().length() > 0) {
			return classifier.segmentString(text);
		}
		
		return null;
	}
}
