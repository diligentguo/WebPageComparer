package com.diligentguo.segmenter;

import java.util.List;

public interface WordSegmenter {

	@SuppressWarnings("rawtypes")
	public List getSegment(String text);
	
}
