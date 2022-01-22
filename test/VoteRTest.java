//import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.Test;

/*
 * Michael Novotny
 * 21 Jan 2022
 * CS 210 HW 1
 */
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

/**
 * 
 * @author Michael Novotny
 * Test class for VoteR
 *
 */
public class VoteRTest {
	// VoteR instance to run tests on.
	VoteR voteR = new VoteR();
	
	// Needed so that I can set the System.out to it's original
	// when I am finished with the test
	static PrintStream originalOut = System.out;
	
	static File expectedText;
	static File out;
	
	// Simple set up for the test
	// creating a file for the output of running VoteR
	// so that I can run a simple assert on it
	// compared to validated results done by hand
	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		// file containing validated results
		expectedText = new File("resources/expected.txt");
		
		// create file for VoteR to write to
		out = new File("resources/test.txt");
		
		// So that my VoteR instance writes directly to this file for
		// comparison
		PrintStream fileOut = new PrintStream(out);
		System.setOut(fileOut);
	}
	
	@Test
	public void testProcessVoterData() throws IOException {
		// Do the thing!!!
		voteR.processVoterData("resources/NVRA Short Test File.csv");
		
		// I really can't figure out an elegant way of
		// comparing two files easily. Maybe I'm going about this whole
		// thing wrong, but this is a solution that I thought of.
		// This solution is acceptable for this particular implementation,
		// but runs into the dangers of reading a whole file into memory,
		// which is generally considered a bad move.
		byte[] expected = Files.readAllBytes(expectedText.toPath());
		byte[] results = Files.readAllBytes(out.toPath());
		
		assertArrayEquals(expected, results);
		
	}
	
	// Clean up after testing
	// by setting System.out to it's original form before the test
	// and deleting the temporary file we made for the test.
	@AfterClass
	public static void cleanUp() {
		System.setOut(originalOut);
		out.delete();
	}
}