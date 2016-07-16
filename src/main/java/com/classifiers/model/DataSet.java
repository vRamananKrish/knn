package com.classifiers.model;

import java.util.HashSet;
import java.util.Set;

public class DataSet {
	
	private Set<DataItem> trainingSet;
	
	public DataSet(){
		
		trainingSet = new HashSet<DataItem>();
		
	}
	
	public DataSet(Set<DataItem> trainingSet) {
		
		this.trainingSet = trainingSet;
		
	}

	public Set<DataItem> getTrainingSet() {
		return trainingSet;
	}

	public void setTrainingSet(Set<DataItem> trainingSet) {
		this.trainingSet = trainingSet;
	}

	/**
	 * Add dataItem to the set
	 * 
	 * @param DataItem 
	 * @return void
	 * */
	public void addTrainingSet(DataItem dataItem){
		
		getTrainingSet().add(dataItem);
	}
	
}
