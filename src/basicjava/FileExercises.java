package basicjava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileExercises {
	
	/**
	 * Reads a file a counts how many times the stringToCount is repeated
	 * @param stringToCount
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static int counting(String stringToCount) throws FileNotFoundException, IOException {
		 BufferedReader reader = new BufferedReader(new FileReader("example.txt"));
		 int stringCount = 0;		 
		 
		 for( String line ; (line = reader.readLine()) != null; ) {
			 String lowercaseLine = line.toLowerCase();
			// System.out.print(lowercaseLine);
			 if(lowercaseLine.contains(stringToCount.toLowerCase())) {
				 //System.out.print(stringCount);
				 stringCount += subStringCounter(lowercaseLine , stringToCount.toLowerCase());
				// System.out.print(stringCount);
			 }
		 }
		 reader.close();
		 return stringCount;
	}
	
	/**
	 * Recursively counts occurrences of stringToCount 
	 * 
	 * @param string
	 * @param stringToCount
	 * @return
	 */
	static int subStringCounter(String string , String stringToCount) {
		
		if (string.contains(stringToCount)) {
			//System.out.print("\nCounting " + "'" + stringToCount + "'" + " in " + string);
			return 1 + subStringCounter(string.replaceFirst(stringToCount, ""), stringToCount);	
		}
		return 0;
	}

	
	public void toUpper(String filename, String outputFileName) throws FileNotFoundException, IOException{
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
		PrintWriter output = new PrintWriter(writer);
		
		for( String line ; (line = reader.readLine()) != null; ) {
			 String upperCaseLine = line.toUpperCase();
			 System.out.print(upperCaseLine);
			 //writer.write(upperCaseLine);
			 output.println(upperCaseLine);
			 }
		
		reader.close();
		writer.close();
		 }
		// the output file should be created and contain exactly the same 
		//content as the input file, except that everything in the output file is in upper case. So, if the 
		//input file contained the text “Coding Challenge” then the output file should contain the text 
		//“CODING CHALLENGE”	
	}
