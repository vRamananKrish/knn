package com.classifiers.utils;

import java.util.Comparator;

import com.classifiers.model.DataItem;

public class SortComparator implements Comparator<DataItem> {
	
	public int compare(DataItem dataItem1, DataItem dataItem2) {
		
		return (int) (dataItem1.getDistance() - dataItem2.getDistance());
			
	}
	
}
