package com.pns.pnsBackend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
 

@EnableAutoConfiguration

@ComponentScan(basePackages="com.pns.controllers")

public class PnsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PnsBackendApplication.class, args);
		System.out.println("THE PROGRAM STARTS");
		//regex();
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200")); //http://localhost:4200
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
	
	
	
	public static void regex() {
        HashMap<Integer, String> regex = new HashMap<Integer, String>(); 
        
        regex.put(1, "NP");
        regex.put(2, "N");
        regex.put(3, "L");
        regex.put(4, "TT");
        regex.put(5, "TTP");
        
        
		
		
		 ArrayList<String> messageArray = new ArrayList();
		 
		 
		 // HashMap containing code key(id from database) and code value(extract from messages)
		 
		 HashMap<Integer, Integer> cleanCodeKey = new HashMap<Integer, Integer>(); 
		
		String message ="L10 TT12 NTP22 ";
        
		String[] mArray = message.split(" ");
		
		for(String s:mArray) {
			messageArray.add(s);
			System.out.println("String Array "+s.length());		
			
		}
		
		for (Map.Entry<Integer, String> entry : regex.entrySet()) {
			
			int i=0;
			for(String data: messageArray) {
				if(data.replaceAll("[0-9]", "").equals(entry.getValue())) {
					System.out.println("FOUND "+entry.getValue()+ " equel "+data);
					
					cleanCodeKey.put(entry.getKey(), Integer.parseInt(data.replaceAll("[^0-9]", "")));
					
				}else {
					System.out.println("NOT FOUND "+entry.getValue()+ " equel "+data);
				}
			}
		   // System.out.println(entry.getKey() + " = " + entry.getKey());
		}
		
		// extract the values to be inserted into the database
		for(Map.Entry<Integer, Integer> bdData : cleanCodeKey.entrySet()){
			System.out.println("NOT FOUND DB key  "+bdData.getKey()+ " Value "+bdData.getValue());
			
		}
        
	}
	

}


