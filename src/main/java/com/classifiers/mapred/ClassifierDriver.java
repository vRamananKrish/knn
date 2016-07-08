package com.classifiers.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class ClassifierDriver  {
	
	public static void main(String[] args) throws Exception {
		
		// Configuration details
		Configuration conf = new Configuration();
		
		conf.set("dataset-training", "/data/iris-traning.csv");
		
		
		Job job = Job.getInstance(conf);
		job.setJobName("KnnClassifier");
		
		job.setJarByClass(ClassifierDriver.class);
		
		//Mapper and Reducer configuration
		job.setMapperClass(KnnClassiferMapper.class);
		job.setReducerClass(KnnClassifierReducer.class);
		
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		
	}
	
}
