package com.classifiers.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 
 * 
 * */
public class DataItem implements Serializable {
	
	private Float[] features = {};
	
	private String className;
	
	private Double distance;
	
	public DataItem() {
	}
	
	public DataItem(Float[] features, String className){
		
		this.features = features;
		this.className = className;
		
	}

	public Float[] getFeatures() {
		return features;
	}

	public void setFeatures(Float[] features) {
		this.features = features;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	/**
	 * Add features to the array
	 * 
	 * */
	public void addFeatures(Float dataPoint){

		final int N = getFeatures().length;
		
		Float[] arr = Arrays.copyOf(getFeatures(), N+1);
		
		arr[N] = dataPoint;
		
		setFeatures(arr);
		
	}
	
}
