/**
 * @author Kamilya Hardie
 * 
 * Description: This class will run two threads accessing the same array.
 * 				Each thread will compute the sum of the array in parallel.
 * 
 * 				A single thread will also be run from the main() method to
 * 				compute the same task on its own.
 * 
 * 				The multithread and single thread operations will be timed and compared to see how long
 * 				each calculation was.
 * 
 * Date: October 30 2020
 */
package concurrencyassignment;

import java.time.LocalDateTime;
import java.util.Random;

public class Multithreads 
{
	//Static array of 200,000,000
	private static int[] arr;
	private static int i;
	
	//shared index between both threads
	private static int index = 0;
	private static int tempSum = 0;
	
	
	//Variable to hold random numbers
	private static int rNum = 0;
	/**
	 * This method is used to create an array of 200 million random numbers 
	 * between 1 and 10
	 */
	public static void createArray(int size)
	{
		arr = new int[size];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = generateRand();
		}
	}
	
	/**
	 * generateRand() method calculates a random number between 1 and 10
	 * @return int This method returns a generated random number
	 */
	public static int generateRand()
	{
		rNum = 1 + (int)(Math.random() * 10);
		return rNum;
	}
	
	/**
	 * sumArray() is a synchronized method that loops through an array 
	 * in order to find the parallel sum with two different threads 
	 * 
	 * @param limit The index to stop at when a thread is looping through the array
	 */
	public static synchronized void sumArray(int limit)
	{
		for (i = index; i <= limit; i++)
		{
				//Add the sum of the array
				tempSum += arr[i];
		}
		
		//Assign index the last position used in the for loop
		index = i;
	}
	public static void main(String[] args) 
	{
		//Size of the array
		final int SIZE = 200_000_000;
		
		//Variables to hold the start and end time of the threads
		int start, end;
		int multiThreadTime = 0;
		
		LocalDateTime now;
		
		//Create the array
		createArray(SIZE);
		
		/**
		 * Instantiate the thread objects and call the sumArray method
		 * in order for each thread to calculate the sum of the array in
		 * parallel. 
		 * 
		 */
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run()
			{
				/* Pass in a number to be used as the limit for
				 * the amount of elements to loop through
				 */
				sumArray(arr.length / 2);
				
			}
		});

		
		Thread t2 = new Thread(new Runnable() {
		@Override
		public void run()
		{ 
			/* Pass in a number to be used as the limit for
			 * the amount of elements to loop through
			 */
			sumArray(arr.length - 1);
		}
		});
		
		/**
		 * Start the threads 
		 * Calculate the timing of both threads
		 */
		
		//Get the current time from the system
		now = LocalDateTime.now();
		
		start = now.getNano();
		t1.start();
		t2.start();
		try 
		{
			t1.join();
			t2.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		now = LocalDateTime.now();
		end = now.getNano();
		
		multiThreadTime = (end - start);
		
		//Print Results
		System.out.println("Multithread sum: " + tempSum);
		System.out.println("Time of multithreaded sum calculation: " + multiThreadTime+ " nano seconds");
		
		
		/*Instantiate the SingleThread object that computes the same operation
		 * in its own single thread
		 */
		Thread sThread = new Thread(new SingleThread());
		
		//Start the single thread
		sThread.start();
	}

}
