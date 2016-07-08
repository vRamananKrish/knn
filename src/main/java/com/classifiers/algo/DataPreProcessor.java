package com.classifiers.algo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.classifiers.model.DataItem;
import com.classifiers.model.DataSet;

public class DataPreProcessor {
	
	private float splitPercentage = 0.66f;
	
	public DataPreProcessor() {	
		
	}
	
	public static void main(String[] args) {
		
		DataPreProcessor proc = new DataPreProcessor();
		
		DataSet dataSet = proc.extractDataSet("E:\\Space\\titan\\hdpSpace\\kNN\\src\\main\\resources\\data/iris.csv", 0.70f);
		
		System.out.println("Data set:"+ dataSet);
		
	}
	
	/**
	 * Read the given file and split into lines
	 * 
	 * */
	public DataSet extractDataSet(String fileName, float splitPercentage){
		
		DataSet dataSet = new DataSet();
		
		
		if(fileName != ""){
			
			List<CSVRecord> csvRecords = parseCsvFile(fileName);
			
			DataItem dataItem = new DataItem();
			
			Float[] features = new Float[4];
			
			
			for(CSVRecord csvRecord: csvRecords){
				
				System.out.println(csvRecord);
				System.out.println(csvRecord.get(0));
			
				features[0] = new Float(csvRecord.get(0)); 
				features[1] = new Float(csvRecord.get(1));
				features[2] = new Float(csvRecord.get(2));
				features[3] = new Float(csvRecord.get(3));
				
				dataItem.setFeatures(features);
				dataItem.setClassName(csvRecord.get(4));		
				
				if(Math.random() < splitPercentage){
					
					dataSet.addToTrainingSet(dataItem);
				}
				else{
					dataSet.addToTestSet(dataItem);
				}
				
			}
		}
		
		return dataSet;
	}
	
	
	/**
	 * Open a file and read the content 
	 * as lines of file 
	 * 
	 * */
	private List<CSVRecord> parseCsvFile(String fileName){
		
		// CSV parser
		File csvFile = new File(fileName);
		
		List<CSVRecord> csvRecords = new ArrayList<CSVRecord>();
		
		try {
			
			CSVParser parser = CSVParser.parse(csvFile, Charset.defaultCharset(), CSVFormat.RFC4180);
			
			csvRecords = parser.getRecords();
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return csvRecords;		
		
		
	}
	

	
	
}
