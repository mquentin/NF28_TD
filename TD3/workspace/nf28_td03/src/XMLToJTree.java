import java.io.FileNotFoundException;
import java.io.IOException;

import org.xml.sax.SAXException;


public class XMLToJTree {

	public static void main(String[] args) {
		String src = "xml_src.xml";
		ContactFacility cf = new ContactFacility();
		try {
			cf.parse(src);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
