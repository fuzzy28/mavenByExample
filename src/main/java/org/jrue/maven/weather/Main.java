package org.jrue.maven.weather;

import java.io.InputStream;

import org.apache.log4j.PropertyConfigurator;

public class Main {

	private String zipCode;
	
	public Main(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure(Main.class.getClassLoader().getResource("log4j.properties"));
		String defaultZipCode = "60202";
		try {
			defaultZipCode = args[0];
		} catch (Exception e) {}
		new Main(defaultZipCode).start();
	}
	
	private void start() throws Exception {
		
		//get response
		InputStream inputStream = new YahooRetriever().retrieve(zipCode);
		
		//parse response
		Weather weather = new YahooParser().parse(inputStream);
		
		//print response
		System.out.println(new WeatherFormatter().format(weather));
	}
}
