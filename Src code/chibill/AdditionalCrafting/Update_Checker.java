package chibill.AdditionalCrafting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Update_Checker implements Runnable {
boolean runing = true;
static boolean No_Internet;
static boolean Up_to_Date;
@Override
	public void run() {
	System.out.println("[AdditionalCrafting] Spawning Second Thread!");
		while(runing){
		try {
		    URL url = new URL("https://raw.github.com/chibill/AdditionalCrafting/master/Version_Control/1.5.txt");
		  
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    int str;
		    int str1;
		    int str2;
		    int str3;
		    
		     
		      str = in.read();
		      str1 = in.read();
		      str2 = in.read();
		      str1 = in.read();
		      str3 = in.read();
		      System.out.println(in);
		      System.out.println(str);
		      if(in != null){
		    	  No_Internet = false;
		      if(str == 49 && str2 == 48 && str3 == 48){
		    	  Up_to_Date = true;
		    	System.out.println("[AdditionalCrafting] Additional Crafting up to date!");
		      }else
		      {
		    	System.out.println("[AdditionalCrafting] Additional Crafting is out of date for this verison of Minecraft!");
		      }
		      }
		   in.close();
	  } catch (MalformedURLException e) {
		  } catch (IOException e) {
			  No_Internet = true;
	    	  System.out.println("[AdditionalCrafting] Additional Crafting has no internet conection!");
		  }
		
		try {
			Thread.sleep(1800000);
		} catch (InterruptedException e) {
		}
			}
}
		
	}


