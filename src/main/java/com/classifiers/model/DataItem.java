package com.classifiers.model;

import java.io.Serializable;

/**
 * 
 * 
 * */
public class DataItem implements Serializable {
	
	private Float[] features;
	
	private String className;
	
	private Float distance;
	
	public DataItem() {

	}
	
	public DataItem(Float[] features, String className){
		
		setFeatures(features);
		
		setClassName(className);
		
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

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	
}
