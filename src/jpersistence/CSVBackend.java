package jpersistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVBackend implements StorageBackend {
	
	@Override
	public void save(String filename, ArrayList<String> properties, ArrayList<String> values) {
		
		BufferedWriter file = null;
		try {
			FileWriter fw = new FileWriter(filename, false);
			file = new BufferedWriter(fw);
		} catch (IOException e) { 
			e.printStackTrace();
		}
				
		String csvProperties = joinString(properties);
		String csvValues = joinString(values);
		
		try {
			file.write(csvProperties);
			file.write("\n");
			file.write(csvValues);
			file.write("\n");
			
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String[] getRows(String filename) {
		
		String content = null;
		try {
			content = new Scanner(new File(filename)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
		
		return content.split("\n");
	}
	
	@Override
	public String[] getRowValues(String row) {
		
		return row.split(",");
	}

	private String joinString(ArrayList<String> stringList) {
		
		String joinedString = "";
		for (String str : stringList) {
			joinedString += str +",";
		}
		joinedString = joinedString.substring(0, joinedString.length() - 1);
		return joinedString;
	}
}
