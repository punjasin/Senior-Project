package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class Application
{
    public static void main( String[] args ) throws Exception
    {
    	SpringApplication.run(Application.class, args);  
    	
    	System.out.println("WebService Started!!");
    }
}
