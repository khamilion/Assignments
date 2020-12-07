package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * This application will function as a server. It will receive a request
 * from  a client application to check whether a number is prime or not.
 * It will then return the answer.
 * 
 * Date: December 5, 2020
 * 
 * @author Kamilya
 *
 */
public class Server2 extends Application
{
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	
	public void start(Stage primaryS)
	{	
		//Text are to display the content 
		TextArea text = new TextArea();
		
		//Create scene and set the stage
		Scene scene = new Scene(new ScrollPane(text), 500, 400);
		primaryS.setTitle("Server");
		primaryS.setScene(scene);
		primaryS.show();
		
		new Thread( () -> {
		try
		{
			// Create a server socket        
			ServerSocket serverSocket = new ServerSocket(8000);
			
			Platform.runLater(() ->  text.appendText("Server started at " + new Date() + '\n'));
			
			// Listen for a connection request        
			Socket socket = serverSocket.accept();
			
			 // Create data input stream       
			DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			
			//Create data output stream
			DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
			
			
			while (true)
			{
				// Receive number from the client          
				int clientNum = inputFromClient.readInt();
				
				//Decide if number is prime
				boolean answer = isPrime(clientNum);
				
				 // Send number back to the client          
				outputToClient.writeBoolean(answer);
				
				 Platform.runLater(() -> {text.appendText("Number received from client: " + clientNum + '\n');            
				 text.appendText("Is number prime? : " + answer + '\n'); });
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
				
		}).start();
	}
	
	/**
	 * Decides whether or not a number is prime
	 * 
	 * @param num
	 * @return true or false
	 */
	public static boolean isPrime(int num)
	{
		if (num <= 1)
		{
			return false;
		}
		
		for (int i = 2; i < num; i++)
		{
			if (num % i == 0)
			{
				return false;
			}
		}
		
		return true;
	}
}
