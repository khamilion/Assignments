/*
 * This JUnit class is going to test a method in the AnalyzerFX class named removePunc() to make
 * 	sure it functions correctly.
 * The removePunc() method is responsible for removing common punctuation from an array of strings
 */
package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PuncTest {

	//Instantiate the object from the class being tested
	AnalyzerFX test1 = new AnalyzerFX();
	
	//Example sentence with punctuation
	String sentence = "hi! how are you?";
	
	//Assign the sentence to the array
	String[] sen = sentence.split(" ");
	
	@Test
	void test() 
	{
		//String holding the expected output 
		String expOutput = new String("hi how are you");
		
		//String to hold the generated output string after removing punctuation
		String output = new String();
		
		//Remove the punctuation from the array and assign to a new array
		String[] newSen = test1.removePunc(sen);
		
		
		//Assign the returned array to the output String
		for(int i = 0; i < newSen.length; i++)
		{
			//if current value is not the last value, then add a space to the end
			if (i != newSen.length - 1)
			{
				output += newSen[i] + " ";
			}
			else
			{
				output += newSen[i];
			}
		}
		
		
		assertEquals(expOutput, output);
		
	}

}
