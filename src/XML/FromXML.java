package XML;

import java.io.ByteArrayInputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class FromXML {

	private String xmlMess;
	private String sender;
	private String mess;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public FromXML(String xmlMess) {
		try {

			// Tạo một DocumentBuilder
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Đọc chuỗi XML vào đối tượng Document
			InputSource source = new InputSource(new StringReader(xmlMess));
			Document doc = builder.parse(source);

			Element root = doc.getDocumentElement();

			sender = root.getElementsByTagName("sender").item(0).getTextContent();
			mess = root.getElementsByTagName("content").item(0).getTextContent();

			setSender(sender);
			setMess(mess);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
