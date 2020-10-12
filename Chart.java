/**
 * Description: This class will track the time it takes to calculate the Fibonacci function both recursively
 * 				and iteratively.
 * 				Using Javafx, it will chart the results of the time test for each Fibonacci function.
 * 				
 * @author Kamilya Hardie
 */
package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class Chart extends Application
{
	/**
	 * This is the main method. It will call the launch method to launch the program.
	 * @param args
	 */
	public static void main(String args[])
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage)
	{
		long x = 1, y = 1;
		long z = 0;
		
		long startTime = 0, endTime = 0, total = 0;
		
		/**
		 * The ArrayLists hold the nanoseconds of each fibonacci sequence
		 */
		List <Long> recurNanoS = new ArrayList<Long>();
		List <Long> iterNanoS = new ArrayList<Long>();
		
		/**
		 * The number[] array holds the numbers calculated from the fibonacci sequence
		 */
		long[] number = new long [9];
		
		
		//Temp value for the number[] array
		int n = 0;
		
		//Place holder for the iterNanoS arrays
		int t1 = 0;
		
		System.out.print("Fibonacci Iteratively: \n");
		while (z <= 60)
		{
			//Get the start time of each number calculation in the sequence
			startTime = System.nanoTime();
			
			//Find next number in the sequence
			z = x + y;
			System.out.print("Number: " + z + "  ");
			number[n] = z;
			
			//Get the ending time
			endTime = System.nanoTime();
			
			//Adjust the values
			x = y;
			y = z;

			//Get the time stamp
			total = endTime - startTime;
			
			//Add the time stamp to the iterative nanosecond array
			iterNanoS.add(total);
			
			System.out.print("NanoSec: " + iterNanoS.get(t1));
			
			System.out.print("\n");
			

			n++;
			t1++;
		}
		
		/**
		 * Variables created for the start and end times of the recursive fibonacci function
		 */
		long startTime2 = 0, endTime2 = 0, total2 = 0;
	
		/**
		 * Recursive Fibonacci Sequence Section
		 */
		int i = 0;
		long num = 0;
		
		//Placeholder variable for recursive nanosecond array
		int t2 = 0;
		
		System.out.print("\n");
		
		System.out.print("Fibonacci Recursively: \n");
		while (num <= 60)
		{
			//Calculate Start time
			startTime2 = System.nanoTime();
			
			num = fibseq(i);

			//Calculate End time
			endTime2 = System.nanoTime();
			
			//Get the time stamp
			total2 = endTime2 - startTime2;
			
			//Add the time stamp to the recurNanoS array list
			recurNanoS.add(total2);
			
			System.out.print("Numbers: " + num + "  ");
			
			System.out.print("NanoSec: " + recurNanoS.get(t2));
			
			System.out.print("\n");
			
			i++;
			t2++;
		}
		
		/**
		 * Define the X Axis
		 */
		NumberAxis xAxis = new NumberAxis(0, 90, 5);
		xAxis.setLabel("Fib Numbers");
		
		/**
		 * Define the Y Axis
		 */
		NumberAxis yAxis = new NumberAxis(500, 300_000, 5_000);
		yAxis.setLabel("Nano Seconds");

		//Create the line chart
		LineChart fibChart = new LineChart(xAxis, yAxis);
		
		/**
		 * Set the data for s1
		 */
		XYChart.Series s1 = new XYChart.Series();
		s1.setName("Iterative"); 
	    
		s1.getData().add(new XYChart.Data(number[0], iterNanoS.get(0)));
		s1.getData().add(new XYChart.Data(number[1], iterNanoS.get(1)));
		s1.getData().add(new XYChart.Data(number[2], iterNanoS.get(2)));
		s1.getData().add(new XYChart.Data(number[3], iterNanoS.get(3)));
		s1.getData().add(new XYChart.Data(number[4], iterNanoS.get(4)));
		s1.getData().add(new XYChart.Data(number[5], iterNanoS.get(5)));
		s1.getData().add(new XYChart.Data(number[6], iterNanoS.get(6)));
		s1.getData().add(new XYChart.Data(number[7], iterNanoS.get(7)));
		s1.getData().add(new XYChart.Data(number[8], iterNanoS.get(8)));
		
		
		
		
		/**
		 * Set the data for s2
		 */
		XYChart.Series s2 = new XYChart.Series();
		s2.setName("Recursive"); 
		
		s2.getData().add(new XYChart.Data(number[0], recurNanoS.get(0)));
		s2.getData().add(new XYChart.Data(number[1], recurNanoS.get(1)));
		s2.getData().add(new XYChart.Data(number[2], recurNanoS.get(2)));
		s2.getData().add(new XYChart.Data(number[3], recurNanoS.get(3)));
		s2.getData().add(new XYChart.Data(number[4], recurNanoS.get(4)));
		s2.getData().add(new XYChart.Data(number[5], recurNanoS.get(5)));
		s2.getData().add(new XYChart.Data(number[6], recurNanoS.get(6)));
		s2.getData().add(new XYChart.Data(number[7], recurNanoS.get(7)));
		s2.getData().add(new XYChart.Data(number[8], recurNanoS.get(8)));
		
		
		/**
		 * Set the data to apply to the line chart
		 */
		fibChart.getData().addAll(s1, s2);
		fibChart.setTitle("Fib Graph");
		
		
		primaryStage.setTitle("Fibonacci Graph");
		Group root = new Group(fibChart);
		primaryStage.setScene(new Scene(root, 700,600));
		
		primaryStage.show();
	
	}

	/**
	 * The fibseq() method is used to calculate the fibonacci sequence recursively
	 * @param i This parameter is the current number in the fibonacci sequence we are trying to determine
	 * @return long 
	 */
	    //Fibonacci sequence recursively 
		public static long fibseq (int i)
		{
			if (i == 0)
			{
				return 0;
			}
			if (i <= 2)
			{
				return 1;
			}
			
			long fibNum = (fibseq(i - 1) + fibseq(i - 2));
			return fibNum;
		}
	
}
