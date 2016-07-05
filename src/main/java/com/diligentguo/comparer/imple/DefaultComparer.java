package com.diligentguo.comparer.imple;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.diligentguo.comparer.Comparer;

/**
 * Compare similary using the algorithm that intersection of Set A and Set B divides
 * the union of Set A and Set B
 * @author Administrator
 *
 */
public class DefaultComparer implements Comparer {
	Logger logger = Logger.getLogger(DefaultComparer.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int getIntersectionSetCount(Set srcSet, Set otherSet) throws Exception {
		
		if (srcSet != null && otherSet != null) {
			srcSet.retainAll(otherSet);
			return srcSet.size();
		} else {
			throw new Exception("all parameter list should not be null");
		}		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int getUnionSetCount(Set srcSet, Set otherSet) throws Exception {
		if (srcSet != null && otherSet != null) {
			srcSet.addAll(otherSet);
			return srcSet.size();
			
		} else {
			throw new Exception("all parameter list should not be null");
		}	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public double compare(List srcList, List otherList) {
		try {
			
			Set srcSet = new HashSet(srcList);
			Set otherSet = new HashSet(otherList);
			
			/**
			 * GetIntersectionSetCount method will change the srcSet, so copy another srcSet
			 * for getUnionSetCount method
			 * 
			 * TODO: We could use SimHash algorithm to enhance this compare algorithm
			 */
			Set tmpSet = new HashSet(srcList);

			int intersectionNum = getIntersectionSetCount(tmpSet,otherSet);
			int unionNum = getUnionSetCount(srcSet,otherSet);
			
			if (unionNum != 0) {
				double  similarity = (double)intersectionNum/unionNum * 100;
				return similarity;
				
			} else {
				throw new Exception("Union Number should not be Zero.");
			}
			
		} catch (Exception e) {
			logger.error("compare error", e);
		}
		
		return -1;
		
	}

}
