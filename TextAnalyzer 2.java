/*
 * Author: Kamilya Hardie
 * 
 * Date Completed: September 20, 2020
 * 
 * Description: This class will serve as a text analyzer that will read from an html file and count the
 * 				occurrence of each word. I use jsoup to extract from the hmtl file.
 * 				
 */

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TextAnalyzer 
{
	public static void main(String args[])
	{
		File input = new File("/users/kamilya/desktop/Poem/poem.htm");
		
		Elements title1;
		Elements title2;
		Elements title3;
		
		String title;
		
		Elements poem;
		
		Map <String, Integer> poemCount = new TreeMap <String,Integer>();
		
		
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
					
			//Convert to a Stream, and print the newly sorted map 
			poemCount.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(System.out::println);
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
	}
	
	
}
