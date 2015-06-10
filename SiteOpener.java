import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;

public class SiteOpener 
{
    public static void main(String[] args) throws IOException 
    {
   
        //create ArrayList to hold list of websites
        ArrayList<String> websiteList = new ArrayList<>();
             
        //create scanner to open and read the file
        try
        {
            FileReader file = new FileReader("sites.txt");
            Scanner reader = new Scanner(file);
            
            //read contents of the file to the ArrayList
            while (reader.hasNext())
            {
                websiteList.add(reader.nextLine().toLowerCase());
            }
            //close file as precaution, possible IOException
            file.close();
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        } 
        
        //prefix links with "www" or "http" 
        for (int i = 0; i < websiteList.size(); i++)
        {
            //attach link behind www if it does not have http or www
            if (!websiteList.get(i).startsWith("www.") && !websiteList.get(i).startsWith("http://"))
            {
                websiteList.set(i, "www.".concat(websiteList.get(i)));
            }
            //some links may already have www in them so we just add http
            if (websiteList.get(i).startsWith("www."))
            {
                websiteList.set(i, "http://".concat(websiteList.get(i)));
            }      
        } 
        
        //sort arraylist
        Collections.sort(websiteList);
         
        //overwrite file with ArrayList back onto same text file
        //overwrite is done by using same file name 
        FileWriter file = new FileWriter ("sites.txt");
        for (int i = 0; i < websiteList.size(); i++)
        {
            file.write(websiteList.get(i) + "\n");
        }
        //close file as precaution
        file.close();
        
        //create random number based on arraylist size 
        //this will be used later to open a random site
        Random randomNum = new Random();
        int siteNumber = randomNum.nextInt(websiteList.size() + 1);
        
        //open site based off random number
        String URL = websiteList.get(siteNumber);
       
        try
        {
           Desktop.getDesktop().browse(URI.create(URL));
        }
        catch (IOException e)
        {
            System.out.println(e);
        }  
        
    }
    
}
