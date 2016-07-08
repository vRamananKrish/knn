package com.classifiers.mapred.model;

import java.util.Set;

import org.apache.hadoop.io.LongWritable;

public class KnnOutputSetWritable<S extends Set, V> extends LongWritable {

	private S trainingSet;
	private V weight;
	
	public KnnOutputSetWritable() {
	}
	
	public KnnOutputSetWritable(S trainingSet, V weight){
		this.trainingSet = trainingSet;
		this.weight = weight;		
	}

	public S getTrainingSet() {
		return trainingSet;
	}

	public void setTrainingSet(S trainingSet) {
		this.trainingSet = trainingSet;
	}

	public V getWeight() {
		return weight;
	}

	public void setWeight(V weight) {
		this.weight = weight;
	}


	
}
