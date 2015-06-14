package org.jrue.maven.weather;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class YahooRetriever {

	private static Logger log = Logger.getLogger(YahooRetriever.class);
	
	public InputStream retrieve(String zipcode) throws Exception {
		log.info("Retrieving weather info...");
		String url = String.format("http://weather.yahooapis.com/forecastrss?p=%s", zipcode);
		URLConnection conn = new URL(url).openConnection();
		return conn.getInputStream();
	}
}
