
public class Main {
	// obviously if I was making this program for a legitimate purpose
	// you would probably run it from the command line like:
	// 			`java VoteR < filetotest.csv`
	// or more likely this would be in the backend of something
	// either on the web or have a GUI or a CLI or whatever.
	// but for these purposes I hope it's okay to just hardcode this in.
	static String pathToFile = "resources/NVRA Statistics July 2018 SEB DFH.csv";
	
	public static void main(String[] args) {
		
		// you could construct this with the path string.
		VoteR voteR = new VoteR();
		
		voteR.processVoterData(pathToFile);
	}
}
