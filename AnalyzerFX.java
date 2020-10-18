/**
 * @author: Kamilya Hardie
 * 
 * Date Completed: October 18, 2020
 * 
 * Description: This class will serve as a text analyzer that will read from an html file and count the
 * 				occurrence of each word. I use jsoup to extract from the hmtl file.
 * 				JavaFX will be used to present the word count as a GUI
 * 				
 */
package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.jsoup.select.Elements;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class AnalyzerFX extends Application
{
	/**
	 * The main method will call the launch() method to start the application
	 * @param args gets passed to the launch method
	 */
	public static void main(String args[])
	{
		launch(args);
	}
	
	Button go;
	VBox center;
	BorderPane root;
	
	//Save the file in a File object
	File input = new File("/users/kamilya/desktop/Poem/poem.htm");
	File output;
	
	//Create elements to the tile of the poem
	Elements title1;
	Elements title2;
	Elements title3;
	
	//String to hold the entire title
	String title;
	
	//Create element to hold the entire poem
	Elements poem;
	
	//This map will hold the key value pair of word and word count for the poem
	Map <String, Integer> poemCount = new TreeMap <String,Integer>();
	
	/**
	 * This method defines and returns the layout for the center of the BorderPane as VBox
	 * @return VBox 
	 */
	private VBox center()
	{
		center = new VBox(20);
		center.setAlignment(Pos.CENTER);
		
		Label start = new Label("Hit GO to view the top 20 words in the poem!");
		start.setFont(Font.font(20));
		start.setAlignment(Pos.TOP_CENTER);
		
		
		go = new Button("GO!");
		go.setAlignment(Pos.BOTTOM_CENTER);
		go.setPrefWidth(150);
	

		center.getChildren().addAll(start, go);
		
		return center;
	}

	/**
	 * The start method define stage and sets the scene and calls the analyze() method to read from
	 * the file.
	 * 
	 */
	@Override 
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Text Analyzer");
		
		root = new BorderPane();
		 
		//Set the center of the BorderPane by calling the center() method
		root.setCenter(center());
		
		//Change background color
		BackgroundFill fill = new BackgroundFill(Color.CADETBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		
		Background bground = new Background (fill);
		
		root.setBackground(bground);
		
		//Method call to read from the html file
		analyze();
		
		
		/**
		 * This method sets the action to take after the user hits the go button.
		 * Once the go button is hit the word count of the poem will be shown.
		 */
		go.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent e)
			{
				root.getChildren().remove(center);
				
				//Create the title
				Text title = new Text("Poem Word Count");
				title.setFont(Font.font("Verdana",FontWeight.BOLD,30));
				
				
				HBox titleBox = new HBox();
				titleBox.setAlignment(Pos.CENTER);
				titleBox.setPadding(new Insets(30,10,10,30));
				
				
				
				titleBox.getChildren().add(title);
				
				//Add HBox to the top of the BorderPane
				root.setTop(titleBox);
				
				//Call method to replace the center of the BorderPane with the word count
				newCenter();
			}
		});
		
		
		Scene scene = new Scene(root, 500, 400);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
		
	}
	
	/**This method holds the statements that will be used to read from the html file. 
	 * It will then save the contents in a map.
	 */
	private void analyze()
	{

		try 
		{
			Document doc = Jsoup.parse(input, "UTF-8");
			
			title1 = doc.select("H1");
			title2 = doc.select("H4");
			title3 = doc.select("H3");
			
			title = title1.text() + " " + title2.text() + " " + title3.text();
		
			//Put the title string in an array
			String[] titleArray = title.split(" ");
			Integer tCount = 1;
			
			//Add the title to the poemCount map first
			for (String t : titleArray)
			{
				t = t.toLowerCase();
				
				//Check if the word in the poem was already saved as a key in the "poemCount" Tree Map
				if(poemCount.containsKey(t))
				{
					//Get the current value of the word (key)
					Integer newVal = poemCount.get(t);
					
					//Update the word count for this word
					newVal++;
					
					//Add the new key value pair to the Tree Map
					poemCount.put(t, newVal);
				}
				else
				{
					poemCount.put(t, tCount);
				}
				
			}
			
			//Retrieve the poem from the document
			poem = doc.getElementsByTag("p");
			
			//Retrieve the strings from the entire poem
			String poemS = poem.text();
			
			//Split the the poem by each space and save in a new array
			String[] words = poemS.split(" ");
		
			
			//This for loop removes certain punctuation from each word in the array 
			int index;
			for (int i = 0; i < words.length; i++)
			{
				//Change all words to lowercase
				words[i] = words[i].toLowerCase();
				
				if (words[i].indexOf(",") != -1)
				{
					index = words[i].indexOf(",");
					words[i] = words[i].substring(0, index);
				}
				else if(words[i].indexOf(".") != -1)
				{
					index = words[i].indexOf(".");
					words[i] = words[i].substring(0, index);
				}
				else if(words[i].indexOf("!") != -1)
				{
					index = words[i].indexOf("!");
					
					words[i] = words[i].substring(0, index);
				}
				else if(words[i].indexOf("?") != -1)
				{
					index = words[i].indexOf("?");
					
					words[i] = words[i].substring(0, index);
				}
				else if(words[i].indexOf(";") != -1)
				{
					index = words[i].indexOf(";");
					words[i] = words[i].substring(0, index);
				}
				else if(words[i].indexOf("\"") != -1)
				{
					index = words[i].indexOf("\"");
					
					if (index == 0)
					{
						words[i] = words[i].substring(1);
					}
				}
			}
			
			//Add the rest of the poem to the poemCount map
			int count = 1;
			for (String wrd : words)
			{
				
				//Check if the word in the poem was already saved as a key in the "poemCount" Tree Map
				if(poemCount.containsKey(wrd))
				{
					//Get the current value of the word (key)
					Integer newVal = poemCount.get(wrd);
					
					//Update the word count for this word
					newVal++;
					
					//Add the new key value pair to the Tree Map
					poemCount.put(wrd, newVal);
				}
				else
				{
					poemCount.put(wrd, count);
				}
				
			}
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	String key = "Word \n", val = "Count \n", all = "";
	int value;
	
	/**
	 * This method provides a newly defined layout for the center of the
	 * BorderPane after the user hits the go button.
	 * 
	 * It sorts the map holding the poem and applies it to the stage
	 */
	private void newCenter()
	{
		VBox words = new VBox();
		words.setAlignment(Pos.CENTER);
		
		//Text to hold the words
		Text wd = new Text();
		
		//Text to hold the count
		Text ct = new Text();
		
		//New map creation to hold the sorted words and associated values
		Map<String, Integer> sortedWords = new TreeMap<String, Integer>();
		
		sortedWords = poemCount.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(20).collect(Collectors.toMap(
				Map.Entry::getKey, Map.Entry::getValue));
		
		
		//Loop through the map to get each key value pair
		for (Map.Entry<String,Integer> entry : sortedWords.entrySet())
		{
			key += entry.getKey() + "\n";
			
		}
		
		wd.setText(key);
		
		VBox count = new VBox();
		
		count.setAlignment(Pos.CENTER);
		
		for (Map.Entry<String,Integer> entry : sortedWords.entrySet())
		{
			value = entry.getValue();
			val += Integer.toString(value) + "\n";
		}
		
		//Add the text 
		ct.setText(val);
		
		count.getChildren().add(ct);
		words.getChildren().add(wd);
		
		//This HBox will hold both VBox's
		HBox rootCenter = new HBox(10);
		rootCenter.setAlignment(Pos.CENTER);
		rootCenter.setPadding(new Insets(0,0,0 , 50));
		rootCenter.getChildren().addAll(words, count);
		
		root.setCenter(rootCenter);
	}
	
}
