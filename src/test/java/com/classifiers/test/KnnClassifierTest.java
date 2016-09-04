package com.classifiers.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import com.classifiers.mapred.KnnClassifierMapper;
import com.classifiers.mapred.KnnClassifierReducer;

@RunWith(PowerMockRunner.class)
public class KnnClassifierTest {

	// Driver class
	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
	
	@Before
	public void setUp(){
		Mapper<LongWritable, Text, Text, IntWritable> knnMapper = new KnnClassifierMapper();
		Reducer<Text, IntWritable, Text, IntWritable> reducer = new KnnClassifierReducer();
		
		mapDriver = MapDriver.newMapDriver(knnMapper);
		
		reduceDriver = ReduceDriver.newReduceDriver(reducer);		
		
		
	}
	
	@Test
	public void testMapper(){
		
		String filePath = this.getClass().getClassLoader().getResource("data/iris.txt").getPath();
		
		// Dataset for the knn algorithm
		mapDriver.getConfiguration().set("dataset-training", filePath);
						
		mapDriver.withInput(new LongWritable(), new Text("5.9, 3.0, 5.1, 1.8, Iris-virginica"));
		mapDriver.withInput(new LongWritable(), new Text("6.2, 3.4, 5.4, 2.3, Iris-virginica"));

		
		mapDriver.withOutput(new Text("Iris-virginica"), new IntWritable(1));
		mapDriver.withOutput(new Text("Iris-virginica"), new IntWritable(1));
		
		try {
			mapDriver.runTest();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReducer(){
		// Output from the mapper object
		List<IntWritable> values = new ArrayList<IntWritable>();
	    values.add(new IntWritable(1));
	    values.add(new IntWritable(1));
		
		reduceDriver.withInput(new Text("Iris-virginica"), values);
		reduceDriver.withOutput(new Text("Iris-virginica"), new IntWritable(2));
		
		
	    try {
			reduceDriver.runTest();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
