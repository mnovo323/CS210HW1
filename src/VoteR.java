import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VoteR {
	String defaultPath;
	
	public void processVoterData(String filePath) {
		BufferedReader br;
		ArrayList<String> seenIDs = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line;
			
			// skip the first line containing headers and whatnot
			br.readLine();
			
			int lineCounter = 1;
			
			while ((line = br.readLine()) != null) {
				boolean containsInvalidData = false;
				String outputString = "";
				
				String [] words = line.split(",");
				
				if (seenIDs.contains(words[0])) {
					System.out.printf("Duplicate record ID %s at line %d.\n", words[0], lineCounter);
				} else {
					for(int i = 0; i < words.length; i++) {
						if (i == 3 || i == 11 || i == 12) {
							outputString = outputString.concat(words[i] + ";");
						} else {
							if (Integer.parseInt(words[i]) < 0) {
								System.out.printf("Invalid data at line %d.\n", lineCounter);
								containsInvalidData = true;
								break;
							} else {
								outputString = outputString.concat(words[i] + ";");
							}
						}
					}
					if (!containsInvalidData) {
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
		}
	}
	
	// I don't know how to do default parameters
	// in java. Overloading was the simple solution.
	// If a path string is not provided, it will still run.
	public void processVoterData() throws FileNotFoundException {
		processVoterData(defaultPath);
	}
	
	VoteR() {
		defaultPath = "resources/NVRA Statistics July 2018.csv";
	}
	
	VoteR(String defaultPath) {
		this.defaultPath = defaultPath;
	}
}
