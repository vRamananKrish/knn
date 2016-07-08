package com.classifiers.mapred.model;

import java.util.Set;

import org.apache.hadoop.io.LongWritable;

public class KnnInputSetWritable<S extends Set, V> extends LongWritable {

	private S trainingSet;
	private V testSample;
	
	public KnnInputSetWritable() {
	}
	
	public KnnInputSetWritable(S trainingSet, V testSample){
		this.trainingSet = trainingSet;
		this.testSample = testSample;		
	}

	public S getTrainingSet() {
		return trainingSet;
	}

	public void setTrainingSet(S trainingSet) {
		this.trainingSet = trainingSet;
	}

	public V getTestSample() {
		return testSample;
	}

	public void setTestSample(V testSample) {
		this.testSample = testSample;
	}
	
	
}
