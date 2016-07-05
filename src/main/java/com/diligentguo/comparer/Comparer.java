package com.diligentguo.comparer;

import java.util.List;

/**
 * Define a interface for comparer
 * @author Administrator
 *
 */
public interface Comparer {
	@SuppressWarnings("rawtypes")
	public double compare(List srcList, List otherList);

}
