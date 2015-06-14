package org.jrue.maven.weather;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;

public class YahooParser {

	private Logger log = Logger.getLogger(YahooParser.class);
	
	public Weather parse(InputStream inputStream) throws DocumentException {
		log.info("Creating xml reader...");
		SAXReader saxReader = createXmlReader();
		Document doc = saxReader.read(inputStream);
		
		log.info("Parsing xml response from Yahoo!");
		Weather weather = new Weather();
		weather.setCity(doc.valueOf("rss/channel/y:location/@city"));
		weather.setRegion(doc.valueOf("rss/channel/y:location/@region"));
		weather.setCountry(doc.valueOf("rss/channel/y:location/@country"));
		weather.setCondition(doc.valueOf("rss/channel/item/y:condition/@text"));
		weather.setTemp(doc.valueOf("rss/channel/item/y:condition/@temp"));
		weather.setChill(doc.valueOf("rss/channel/y:wind/@chill"));
		weather.setHumidity(doc.valueOf("rss/channel/y:atmosphere/@humidity"));
		return weather;
	}
	
	
	private SAXReader createXmlReader() {
		Map<String, String> uris = new HashMap<String, String>();
		uris.put("y", "http://xml.weather.yahoo.com/ns/rss/1.0");
		
		DocumentFactory documentFactory = new DocumentFactory();
		documentFactory.setXPathNamespaceURIs(uris);
		return new SAXReader(documentFactory);
	}
}
