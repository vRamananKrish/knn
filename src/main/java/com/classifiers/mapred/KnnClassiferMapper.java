package com.classifiers.mapred;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.classifiers.algo.DataPreProcessor;
import com.classifiers.model.DataSet;

public class KnnClassiferMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	// Instance variable for the training set data
	private DataSet trainingSet;
	
	private float SPLIT_PERCENTAGE = 0.80f;
	
	
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		Configuration conf = context.getConfiguration();
		
		DataPreProcessor dataPreProcessor = new DataPreProcessor();
		
		
		String dataSetFile = conf.get("dataset-training");
		
		trainingSet = dataPreProcessor.extractDataSet(dataSetFile, SPLIT_PERCENTAGE);
		
		
		
		
		
//		super.setup(context);
	}
	
	

}
