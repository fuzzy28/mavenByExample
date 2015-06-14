package org.jrue.maven.weather;

import java.io.InputStream;

import junit.framework.TestCase;

public class YahooParserTest extends TestCase {

	public YahooParserTest(String name) {
		super(name);
	}
	
	public void testParser() throws Exception {
		InputStream testData = getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
		Weather weather = new YahooParser().parse(testData);
		assertEquals( "New York", weather.getCity() );
        assertEquals( "NY", weather.getRegion() );
        assertEquals( "US", weather.getCountry() );
        assertEquals( "39", weather.getTemp() );
        assertEquals( "Fair", weather.getCondition() );
        assertEquals( "39", weather.getChill() );
        assertEquals( "67", weather.getHumidity() );
	}
}
