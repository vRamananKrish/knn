package com.classifiers.mapred;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class KnnClassifierReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	@Override
	protected void reduce(Text neighbor, Iterable<IntWritable> intItr,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		
		int sum = 0;
		// Iterate the mapped values 
		for(IntWritable value: intItr){			
			sum += value.get();
		}		
		
		context.write(neighbor, new IntWritable(sum));
	}
		
	
}
