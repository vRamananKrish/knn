package com.classifiers.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class KnnClassifierDriver  {
	
	public static void main(String[] args) throws Exception {
		
		// Configuration details
		Configuration conf = new Configuration();
		
		// First argument is a training set
		conf.set("dataset-training", args[0]);
		// Value of K is pivot
		conf.set("pivot", "3");
		
		Job job = Job.getInstance(conf);
		job.setJobName("KnnClassifier");
		
		job.setJarByClass(KnnClassifierDriver.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//Mapper and Reducer configuration
		job.setMapperClass(KnnClassifierMapper.class);
		job.setReducerClass(KnnClassifierReducer.class);
		
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		// Test file is given as an input
		FileInputFormat.addInputPath(job, new Path(args[1]));
		// Output path
		FileOutputFormat.setOutputPath(job, new Path(args[2]));		
		
		// Non-blocking invocation of job
		job.submit();
		
	}
	
}
