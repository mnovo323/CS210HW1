/*
 * Michael Novotny
 * 21 Jan 2022
 * CS 210 HW 1
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Michael Novotny
 * VoteR class to do the things VoteR is supposed to do
 *
 */
public class VoteR {
	
	/**
	 * The path to a file that VoteR will try to process by default
	 */
	private String defaultPath;
	
	/**
	 * This method performs the basic requirements of HW1
	 * By processing a certain formatted .csv file
	 * and looking for duplicate records and errors.
	 * This method is static so that it can be called without an instance of VoteR.
	 * @param filePath path to the .csv record you wish to process
	 */
	public static void processVoterData(String filePath) {
		BufferedReader br = null;
		//ArrayList rather than an array because we won't know how large it will grow.
		// also things like .contains() are really nice to use.
		ArrayList<String> seenIDs = new ArrayList<String>();
		
		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line;
			
			// skip the first line containing headers and whatnot
			br.readLine();
			
			// needed to display what line an error occurs on
			int lineCounter = 1;
			
			// Read the next line, and continue if there is still data to process
			while ((line = br.readLine()) != null) {
				// Flag to tell whether or not the data is valid
				// and the constructed string should be outputted
				boolean containsInvalidData = false;
				// String that we can concat to in order to construct the format wanted
				String outputString = "";
				
				String [] words = line.split(",");
				
				//check if the ID is duplicate
				if (seenIDs.contains(words[0])) {
					System.out.printf("Duplicate record ID %s at line %d.\n", words[0], lineCounter);
				} else {
					// loop through the line that has been split by the comma delimiter
					for(int i = 0; i < words.length; i++) {
						// these are special cases where we do not want to try to cast to int
						if (i == 3 || i == 11 || i == 12) {
							// add the current word to the constructed string with a semicolon delimiter
							outputString = outputString.concat(words[i] + ";");
						} else {
							// validate that the data is non-negative
							if (Integer.parseInt(words[i]) < 0) {
								System.out.printf("Invalid data at line %d.\n", lineCounter);
								containsInvalidData = true;
								// I've had a teacher who did not like break statements in loops
								// but I think it makes sense to stop processing the data as soon
								// as we know it's invalid
								break;
							} else {
								
								// this repeats code, but I can't think of a way to do this without repeating code
								outputString = outputString.concat(words[i] + ";");
							}
						}
					}
					
					// if the message so far has been valid then it's time to print it
					if (!containsInvalidData) {
						
						// Perhaps an incredibly hacky way of removing the last semicolon
						// I really don't know. It makes my test pass so what do I care.
						System.out.println(outputString.substring(0, outputString.length() - 1));
					}
				}
				lineCounter++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.print(e);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.print(e);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.err.print(e);
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.print(e);
			}
		}
	}
	
	// I don't know how to do default parameters
	// in java. Overloading was the simple solution.
	// If a path string is not provided, it will still run.
	public void processVoterData() {
		processVoterData(defaultPath);
	}
	
	VoteR() {
		defaultPath = "resources/NVRA Statistics July 2018.csv";
	}
	
	VoteR(String defaultPath) {
		this.defaultPath = defaultPath;
	}
}
