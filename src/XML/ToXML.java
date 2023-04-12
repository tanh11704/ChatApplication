package XML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ToXML {
	
	private String msg;
	private String sender;

	public String toXML(String sender, String msg) {
		try {
			
//			create DocumentBuilder
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        
//	        create Xml Document
	        Document doc = builder.newDocument();
	        
//	        tạo phần tử gốc
	        Element rootElement = doc.createElement("message");
            doc.appendChild(rootElement);

//          tạo phần tử con
            Element senderElement = doc.createElement("sender");
            senderElement.appendChild(doc.createTextNode(sender));
            rootElement.appendChild(senderElement);

            Element msgElement = doc.createElement("content");
            msgElement.appendChild(doc.createTextNode(msg));
            rootElement.appendChild(msgElement);

//          Chuyển đổi tài liệu XML thành chuỗi
            javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
            java.io.StringWriter sw = new java.io.StringWriter();
            javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(sw);
            transformer.transform(source, result);

//          Xuất chuỗi XML ra màn hình
            String xmlString = sw.toString();
            
            return xmlString;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
