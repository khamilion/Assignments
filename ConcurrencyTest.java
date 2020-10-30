/**
 * 
 * @author Kamilya Hardie
 * 
 * Description: This JUnit application class will test two methods from the Multithreads class
 * 				The createArray() method will be tested first to make sure it can successfully 
 * 				create a new array with a size argument.
 * 
 * 				The generateRand() method will also be tested. This method is responsible for
 * 				generated a random number between 1 and 10. It will be tested to make sure
 * 				it implements this operation successfully.
 * 
 * Date: October 30 2020
 */

package concurrencyassignment;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ConcurrencyTest 
{
	
	
	//Test the method that creates an array
	@Test
	void testCreateArray() 
	{
		final int SIZE = 5;
		Multithreads.createArray(SIZE);
	}
	
	//Test the method that generates the random numbers
	@Test
	void testGenerateRand()
	{
		//Hold boolean value to determine whether operation was successful
		boolean success;
		
		//Array holding the valid values that could be returned from generateRand() method
		int[] array = {1,2,3,4,5,6,7,8,9,10};
		
		//Variable to hold the random number generated from generateRand()
		int rNum = Multithreads.generateRand();
		
		//Sort the array before using binarySearch()
		Arrays.sort(array);
		
		/*
		 * Search for the random number in the array
		 * If the rNum is in the array, the index of rNum is returned, otherwise -1 is returned
		 * 
		 * If -1 is returned, the search failed and the generateRand() method did not fulfill its 
		 * purpose
		 */
		if(Arrays.binarySearch(array, rNum) != -1)
		{
			success = true;
		}
		else
		{
			success = false;
		}
		
		//Checks to make sure that generateRand() method was a success
		assertTrue(success);
	}
}
