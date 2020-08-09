package com.avi.movie.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *<h1>The aim of this class is to populate utility beans for application.</h1>
 *@author avinashsingh
 */
@Configuration
public class UtilityConfig {
	
	/**
	 The aim of this method is to populate ModelMapper bean for application.
	 @author avinashsingh
	 @param No parameters
	 @return ModelMapper class object to manage inside spring container.
	*/	
	@Bean 
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}

}
