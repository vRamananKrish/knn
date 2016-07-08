package com.classifiers.mapred.model;

import java.io.Serializable;

import com.classifiers.model.DataItem;

public class TestInputWritable implements Serializable {

	private DataItem testSample;
	
	private Integer pivot;
	
	public TestInputWritable() {
	}
	
	public TestInputWritable(DataItem testSample, Integer pivot){
		this.testSample = testSample;
		this.pivot = pivot;
	}

	public DataItem getTestSample() {
		return testSample;
	}

	public void setTestSample(DataItem testSample) {
		this.testSample = testSample;
	}

	public Integer getPivot() {
		return pivot;
	}

	public void setPivot(Integer pivot) {
		this.pivot = pivot;
	}
	
	
}
