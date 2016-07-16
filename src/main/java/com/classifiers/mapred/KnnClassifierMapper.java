package com.classifiers.mapred;

import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.classifiers.algo.DataPreProcessor;
import com.classifiers.algo.KNearestNeighbors;
import com.classifiers.model.DataItem;
import com.classifiers.model.DataSet;

public class KnnClassifierMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	// Instance variable for the training set data
	private DataSet dataSet = new DataSet();
	
	private KNearestNeighbors kNearestNeighbor = new KNearestNeighbors();
	
	private int pivot = 1;
	
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		Configuration conf = context.getConfiguration();
		
		DataPreProcessor dataPreProcessor = new DataPreProcessor();
		
		
		String dataSetFile = conf.get("dataset-training");
		
		pivot = (int) conf.getInt("pivot-value", 1);
		
		// Initializing the trainingSet object
		dataSet = dataPreProcessor.extractDataSet(dataSetFile);
		
	}
	
	/**
	 * Map method implementation 
	 * 
	 * */
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		// Read the line of test Instance
		String testInstanceLine = value.toString();
		
		// Tokenize the line based on the delimiter ','
		StringTokenizer tokenizer = new StringTokenizer(testInstanceLine, ",");
		
		// Test instance object
		DataItem testInstance = new DataItem();
		String token;
		
		// Iterate the tokenizer
		while(tokenizer.hasMoreTokens()){
			token = tokenizer.nextToken();
			
			if(isNumeric(token)){
				testInstance.addFeatures(new Float(token));
			}
		}
		
		Map<String, Integer> classMap = kNearestNeighbor.findAndPredict(dataSet.getTrainingSet(), testInstance, pivot);
		
		String className = (String)classMap.keySet().toArray()[0];
		
		Text prediction = new Text(className);
		
		IntWritable val = new IntWritable(classMap.get(className));
		
		System.out.println(className+"-"+val.toString());
		
		context.write(prediction, val);		
		
	}
	
	public boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}

}
