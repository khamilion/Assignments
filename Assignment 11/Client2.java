import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This application is a client. It will make a request from the server to compute whether a number
 * is prime or not.
 * 
 * Date: December 5, 2020
 * 
 * @author Kamilya
 *
 */

public class Client2 extends Application
{
	//IO streams
		DataOutputStream toServer = null;  
	    DataInputStream fromServer = null;
		
		public static void main(String[] args) 
		{
			launch(args);
		}
		
		@Override
		public void start(Stage primary)
		{
			BorderPane textFieldPane = new BorderPane();
			textFieldPane.setPadding(new Insets(5,5,5,5));
			textFieldPane.setStyle("-fx-border-color: green");
			textFieldPane.setLeft(new Label("Enter a Number: "));
			
			//Textfield for user input
			TextField text = new TextField();
			
			text.setAlignment(Pos.BASELINE_RIGHT);
			textFieldPane.setCenter(text);
			
			//The root pane
			BorderPane mainPane = new BorderPane();
			
			//Text area displays the content
			TextArea textA = new TextArea();
			
			mainPane.setCenter(new ScrollPane(textA));
			mainPane.setTop(textFieldPane);
			
			//Create the scene
			Scene scene = new Scene(mainPane, 500, 400);
			
			primary.setTitle("Client");
			primary.setScene(scene);
			primary.show();
			
			text.setOnAction(e -> {
				try
				{
					// Get the number from the text field        
					int num = Integer.parseInt(text.getText().trim());
					
					// Send the number to the server        
					toServer.writeInt(num);        
					toServer.flush();
					
					 // Get answer from the server        
					boolean answer = fromServer.readBoolean();
					
					 // Display to the text area        
					textA.appendText("Is number prime? " + answer + "\n");        
					textA.appendText("Number received from the server is " + num + '\n');
					
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
			});
			
			try 
			{
				// Create a socket to connect to the server      
				Socket socket = new Socket("localhost", 8000);
				
			    // Create an input stream to receive data from the server      
				fromServer = new DataInputStream(socket.getInputStream());
				
			    // Create an output stream to send data to the server      
				toServer = new DataOutputStream(socket.getOutputStream());
				
			}
			catch(IOException e)
			{
				textA.appendText(e.toString() + '\n');
			}
		}
}
