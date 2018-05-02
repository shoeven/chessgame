// Kildekoden er fra nettsiden https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
// Koden er blit tilpasset slik at vi kan bruke den til v�r oppgave

package config;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLfile {

	IConfiguration config;
	public static String ipPlayer1;
	public static String ipPlayer2;

	public ReadXMLfile() {

		try {

			// xml filen �pnes der den ble lagret av WriteXMLFile.
			File fXmlFile = new File(".\\src\\config\\configuration.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("ipadresse");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					ipPlayer1 = eElement.getElementsByTagName("IpPlayer1").item(0).getTextContent();
					
					ipPlayer2 = eElement.getElementsByTagName("IpPlayer2").item(0).getTextContent();
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getIpPlayer1() {
		return ipPlayer1;
	}
	
	public static String getIpPlayer2() {
		return ipPlayer2;
	}

}