package com.pns.regex.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pns.controllers.SMSController;

public class RegexProcessor {
	
	JsonParser parser = new JsonParser();


	public Map<String, String> processRegex(String json) {
		
		
		JsonElement jsonTree = parser.parse(json);
		

	    Map<String, String> mapData = new HashMap<String, String>();
		
		if(jsonTree.isJsonObject()){
		    JsonObject jsonObject = jsonTree.getAsJsonObject();

		    String message = jsonObject.get("message").toString().trim().replace("\"","");

		    String sender = jsonObject.get("sender").toString().trim().replace("\"","");  
		    
		    //String[] data = {};
		    
		    // query the database then loop the extract value and code id
		    
		    //SMSController smc = new SMSController();
		    
		    /*
		    ArrayList indicators = smc.getSmsIndicators();
		    
		    for(int i = 0; i<indicators.size(); i++) {
		    	
		    	System.out.println("INTERAOR ********************"+indicators.get(i));
		    	
		    }
		    */
		    
		    
		    
		    String tested =null;
		    String negative =null;
		    String positive =null;
		    String unknown =null;
		    
		    
		    
		    String testedRegex = "([T][0-9]*)";
		    String input =message;
		    Pattern pattern = Pattern.compile(testedRegex);
		    Matcher matcher_tested = pattern.matcher(input);
		    if(matcher_tested.find())
		    {
		    	
		    	tested=matcher_tested.group(1).replaceAll("[^0-9]", "");
		    	System.out.println("sender "+sender);
		    	System.out.println("tested "+tested);
		    	mapData.put("T", tested);   
		    	
		    }
		    
		    String positiveRegex = "([P][0-9]*)";
		    Pattern pattern_positive = Pattern.compile(positiveRegex);
		    Matcher matcher_positive = pattern_positive.matcher(input);
		    if(matcher_positive.find())
		    {
		    	
		    	positive=matcher_positive.group(1).replaceAll("[^0-9]", "");;
		    	System.out.println("positive "+positive);
		    	mapData.put("P", positive);
		    }
		    
		    String gegativeRegex = "([N][0-9]*)";
		    Pattern pattern_negative = Pattern.compile(gegativeRegex);
		    Matcher matcher_negative = pattern_negative.matcher(input);
		    if(matcher_negative.find())
		    {
		    	
		    	negative=matcher_negative.group(1).replaceAll("[^0-9]", "");;
		    	System.out.println("negative "+negative);
		    	mapData.put("N", negative);
		    }
		    String unknwnRegex = "([U][0-9]*)";
		    Pattern pattern_unknown = Pattern.compile(unknwnRegex);
		    Matcher matcher_unknown = pattern_unknown.matcher(input);
		    if(matcher_unknown.find())
		    {
		    	
		    	unknown=matcher_unknown.group(1).replaceAll("[^0-9]", "");;
		    	System.out.println("unknown "+unknown);
		    	mapData.put("U", unknown);
		    }			 
		    
		    
		   
		}
		
		return mapData;
		
	}
	
	
	

}
