package org.jrue.maven.weather;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import junit.framework.TestCase;

public class WeatherFormatterTest extends TestCase {

	public WeatherFormatterTest(String name) {
		super(name);
	}
	
	public void testFormat() throws Exception {
		InputStream testData = getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
		Weather weather = new YahooParser().parse(testData);
		String actualResult = new WeatherFormatter().format(weather);
		String expectedResult = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("format-expected.dat"));
		assertEquals(actualResult.trim(), expectedResult.trim());
	}
}
