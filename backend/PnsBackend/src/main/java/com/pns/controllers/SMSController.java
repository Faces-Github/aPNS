/**
 * 
 */
package com.pns.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pns.models.SMSModel;
import com.pns.regex.processor.RegexProcessor;
import com.pns.sqlQueries.SmsSql;
/**
 * @author agik
 *
 */
public class SMSController {
	
	Connection conn=null;
	Statement st =null;
	ResultSet rs =null;
	PreparedStatement pst =null;
	
	@Autowired
	private DataSource dataSource;
	
	// Post to database SMS Received
	
			@PostMapping(("/insertSms1"))	
			public String inserSms(@RequestBody String json) {
				System.out.println("INSERTIN RECORDS PNS");
				getSmsIndicators();
				// final GsonJsonParser gson = new GsonJsonParser();
				//http://tutorials.jenkov.com/java-json/gson-jsonparser.html			
				String str=json;
				  // String numberOnly= str.replaceAll("[^f^0-9]", "").replaceAll("[^0-9]", "");
				
				RegexProcessor rp = new RegexProcessor();
				
				Map<String, String> mapEntries = rp.processRegex(json);
				
				System.out.println("THE REGEX DATA xxx::::: "+rp.processRegex(json).toString());
				System.out.println("THE REGEX DATA3 ::::: "+rp.processRegex(json).get("T"));
				
				// Loop through the map to extract keys and values
				
				
				for (Map.Entry<String,String> entry : mapEntries.entrySet()) { 
		            System.out.println("Key = " + entry.getKey() + 
		                             ", Value = " + entry.getValue()); 
		    } 
				
				
				
				  
				   
				
				JsonParser parser = new JsonParser();


				JsonElement jsonTree = parser.parse(json);

				
				
				 String sjson = "{message=inserted};";
				
				
				
				SmsSql sql = new SmsSql();
				String query = sql.getIndicators();
				
				try {
					conn = dataSource.getConnection();					
					pst =  conn.prepareStatement(query);
					
					if(jsonTree.isJsonObject()){
					    JsonObject jsonObject = jsonTree.getAsJsonObject();

					    String message = jsonObject.get("message").toString().trim().replace("\"","");

					    String sender = jsonObject.get("sender").toString().trim().replace("\"","");  
					    
					    
					    
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
					    }
					    
					    String positiveRegex = "([P][0-9]*)";
					    Pattern pattern_positive = Pattern.compile(positiveRegex);
					    Matcher matcher_positive = pattern_positive.matcher(input);
					    if(matcher_positive.find())
					    {
					    	
					    	positive=matcher_positive.group(1).replaceAll("[^0-9]", "");;
					    	System.out.println("positive "+positive);
					    }
					    
					    String gegativeRegex = "([N][0-9]*)";
					    Pattern pattern_negative = Pattern.compile(gegativeRegex);
					    Matcher matcher_negative = pattern_negative.matcher(input);
					    if(matcher_negative.find())
					    {
					    	
					    	negative=matcher_negative.group(1).replaceAll("[^0-9]", "");;
					    	System.out.println("negative "+negative);
					    }
					    String unknwnRegex = "([U][0-9]*)";
					    Pattern pattern_unknown = Pattern.compile(unknwnRegex);
					    Matcher matcher_unknown = pattern_unknown.matcher(input);
					    if(matcher_unknown.find())
					    {
					    	
					    	unknown=matcher_unknown.group(1).replaceAll("[^0-9]", "");;
					    	System.out.println("unknown "+unknown);
					    }			    
					    
					   // pst.setString(1, sender);
					   // pst.setString(2, tested);
					   // pst.setString(3, positive);
					  //  pst.setString(4, negative);
						//pst.setString(5, unknown);
						//pst.setInt(6, 0);
						//pst.executeUpdate();

					}
					
					
				} catch (SQLException e) {
					System.out.println(e);
					throw new RuntimeException();
					
				} finally {
					try {
						if (conn != null) {
							conn.close();
						}
						
					} catch (SQLException e) {
						System.out.println("5555 ");
						// TODO: handle exception
					}
				}
				return sjson;
				
				
			}
			
			
	
public ArrayList getSmsIndicators(){
	
	System.out.println("THE INDICATORS dataSource.getConnection():::%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%55 ");
				
	SMSModel smsModel = null;
				SmsSql sql = new SmsSql();
				
				String query = sql.getIndicators();
				
				ArrayList<SMSModel> indicators = new ArrayList();
				System.out.println("THE INDICATORS ::: "+query);
				
				
				
				return indicators;
			}
			




/*
public Map processRegex(String json) {

	JsonParser parser = new JsonParser();
	JsonElement jsonTree = parser.parse(json);

	Map<String, String> mapData = new HashMap<String, String>();

	if (jsonTree.isJsonObject()) {
		JsonObject jsonObject = jsonTree.getAsJsonObject();

		String message = jsonObject.get("message").toString().trim().replace("\"", "");

		String sender = jsonObject.get("sender").toString().trim().replace("\"", "");

		String matchedRecord = null;

		// Map containing all indicators

		 Map<String, String> indicators = getSmsIndicators();
		 
		// String[] strn =message.split("split(" "));  
		 
		 for(String val: message.split(" ")) {
			// System.out.println("THE STRING VALUE::::   "+val);
			 
		

		// loop through the map , pass the code to the regex matcher
		// String smsText = message;
		for (Map.Entry<String, String> entry : indicators.entrySet()) {
			// define a dynamic regex containing indicators;
			
			// decompile the message string
			String testedRegex = "([" + entry.getValue() + "][0-9]*)";

			// check to make sure only available indicators are looped through
			//System.out.println("ENTRIES::: "+testedRegex);
			if (val.contains(entry.getValue())) {
				
				

				Pattern pattern = Pattern.compile(testedRegex);
				Matcher matcher_tested = pattern.matcher(val);

				// find the matched record
				if (matcher_tested.find()) {
					
					System.out.println("ENTRIES::: here========== "+val.replaceAll("[^0-9]", "")+  " found "+entry.getKey() );

					matchedRecord = matcher_tested.group(1);//.replaceAll("[^0-9]", "");
					//System.out.println("ENTRIES:::========== sss "+matchedRecord);
					// add record to the map
					mapData.put(entry.getKey(), val.replaceAll("[^0-9]", ""));

				
					//  System.out.println(" KEY ******************** = " + val+
					// ",  found VALUE ******************** = " + entry.getValue());
					  
					// break;

				}
			} else {
				// no indicators matched in the sms sent
			}
		 }
		}

	}

	return mapData;

}
*/			
			
			
}
