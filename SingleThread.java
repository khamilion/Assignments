/**
 * @author Kamilya Hardie
 * 
 * Description: This class implements the Runnable class and accesses an array of 200,000,000 values.
 * 				It will compute the total sum of the array and time how long the operation takes.
 * 
 * Date: October 30 2020
 */
package concurrencyassignment;

import java.time.LocalDateTime;

public class SingleThread implements Runnable
{
	private int[] arr;
	private int tempSum;
	private int start;
	private int end;
	private int totalTime;
	
	//Holds the total ending sum of the array
	private int finalSum;
	
	LocalDateTime now;
	
	/**
	 * This method is used to create an array of 200 million random numbers 
	 * between the values of 1 and 10
	 */
	public void createArray()
	{
		arr = new int[200_000_000];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = generateRand();
		}
	}
	
	/**
	 * generateRand() method calculates a random number between 1 and 10
	 * @return int This method returns a generated random number
	 */
	public int generateRand()
	{
		int rNum = 1 + (int)(Math.random() * 10);
		return rNum;
	}
	
	/**
	 * sumArray() calculates the sum of entire array in a for loop
	 */
	public void sumArray()
	{
		for (int i = 0; i < arr.length; i++)
		{
			//Add the sum of the array
			tempSum += arr[i];
		}
	}
	

	@Override
	public void run()
	{
		//Create the array
		createArray();
		
		//Get the start time
		now = LocalDateTime.now();
		
		start = now.getNano();
		
		//Add the arrays values
		sumArray();
		
		//Get the end time 
		now = LocalDateTime.now();
		end = now.getNano();
		
		finalSum = tempSum;
		
		//Get the total amount of time it took to complete
		totalTime = end - start;
		
		//Print Results
		System.out.println("\n\nSingle thread sum: " + finalSum);
		System.out.println("Time of single threaded sum calculation: " + totalTime+ " nano seconds");
		
	}
	
	
}
