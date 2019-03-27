/*
 * 
 */
package com.almundo.callcenter.server.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// TODO: Auto-generated Javadoc
/**
 * The Class CallCenterApplication. Lift the application by scanning all spring components
 */
@SpringBootApplication(scanBasePackages = {"com.almundo"})
public class CallCenterApplication {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CallCenterApplication.class);

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CallCenterApplication.class, args);
		logger.info("Aplication Call Center Application Almundo started");
		
	}
}
