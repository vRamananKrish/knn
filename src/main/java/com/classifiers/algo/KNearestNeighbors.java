package com.classifiers.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.classifiers.model.DataItem;
import com.classifiers.utils.MapSorter;
import com.classifiers.utils.SortComparator;


/**
 * kNearest Neighbor algorithm implementation class
 * 
 * @author venkataramanan-k
 * @version 0.0.1
 * */
public class KNearestNeighbors {

	public KNearestNeighbors() {}
	
	
	/**
	 * Super method to orchestrate the algorithm step methods
	 * 
	 * 
	 * */
	public Map<String, Integer> findAndPredict(Set<DataItem> trainingSet, DataItem testInstance, int pivot){
		
		List<DataItem> neighbors = findNeighbors(trainingSet, testInstance, pivot);
		
		return predictClass(neighbors);
		
	}
	
	
	/**
	 * findNeighbors() - finds the similarity analysis between the data points
	 * 
	 * */
	public List<DataItem> findNeighbors(Set<DataItem> trainingSet, DataItem testInstance, int pivot){
		Float distance = new Float(0.0);
		
		List<DataItem> similarPoints = new ArrayList<DataItem>();
		// Neighbors with length of pivot
		List<DataItem> neighbors = new ArrayList<DataItem>(pivot);
		
		int i;
		
		// Iterate the training set
		for(DataItem trainData: trainingSet){
			
			// Iterate the feature attributes
			for(i=0;i<testInstance.getFeatures().length;i++){
				// Distance calculation
				distance += ((trainData.getFeatures()[i] - testInstance.getFeatures()[i]) * (trainData.getFeatures()[i] - testInstance.getFeatures()[i])); 
			}
			// Square root of the distance
			trainData.setDistance(Math.sqrt(distance));
			
			similarPoints.add(trainData);			
			
		}
		
		// Sort the neighbors in ascending order 
		Collections.sort(similarPoints, new SortComparator());
		
		int j;
		
		for(j = 0;j < pivot; j++){
			// Add the top similars as neighbors
			neighbors.add(similarPoints.get(j));
			
		}
		
		return neighbors;
	}
	
	
	/**
	 * Predict the class
	 * 
	 * */
	private Map<String, Integer> predictClass(List<DataItem> neighbors){
	
		Map<String, Integer> classMap = new TreeMap<String, Integer>();
		Map<String, Integer> topClass = new HashMap<String, Integer>();
		Integer vote;
		
		for(DataItem dataItem: neighbors){
			
			if(classMap.containsKey(dataItem.getClassName())){
				vote = classMap.get(dataItem.getClassName()) + 1;
				
				classMap.put(dataItem.getClassName(), vote);
			}
			else{
				classMap.put(dataItem.getClassName(), 1);
			}
			
		}
		
		// Sort the map into desc order
		classMap = MapSorter.sortMapByValue(classMap);

		String key = (String)MapSorter.sortMapByValue(classMap).keySet().toArray()[0];
		
		topClass.put(key, classMap.get(key));
		
		return topClass;		
		
	}
	
	
	/**
	 * find the distance between the data points
	 * 
	 * */
	public Float findEuclideanDistance(Float[] trainingFeatures, Float testFeature){
		
		Float distance = new Float(0.0);
		
		for(Float dataPoint: trainingFeatures){
			
			distance += ((dataPoint - testFeature)*(dataPoint - testFeature));
			
		}
		
		return distance;
	}
}
