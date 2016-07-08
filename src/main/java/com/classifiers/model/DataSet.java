package com.classifiers.model;

import java.util.HashSet;
import java.util.Set;

public class DataSet {
	
	private Set<DataItem> traingSet;
	
	private Set<DataItem> testSet;
	
	public DataSet(){
		
		traingSet = new HashSet<DataItem>();
		testSet = new HashSet<DataItem>();
		
	}
	
	public DataSet(Set<DataItem> traingSet, Set<DataItem> testSet) {
		
		setTraingSet(traingSet);
		
		setTestSet(testSet);
		
	}

	public Set<DataItem> getTraingSet() {
		return traingSet;
	}

	public void setTraingSet(Set<DataItem> traingSet) {
		this.traingSet = traingSet;
	}

	public Set<DataItem> getTestSet() {
		return testSet;
	}

	public void setTestSet(Set<DataItem> testSet) {
		this.testSet = testSet;
	}
	
	public void addToTrainingSet(DataItem dataItem){
		getTraingSet().add(dataItem);	
	}
	
	public void addToTestSet(DataItem dataItem){
		getTestSet().add(dataItem);		
	}
	
	
}
