package com.company.io;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.company.image.Image;
import com.company.session.Session;
import com.company.session.SessionManager;
import com.company.transformation.Transformation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XmlRead {

    public static void read(String fileName) {
        try {
            File file = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList sessionList = doc.getElementsByTagName("Session");

            for (int i = 0; i < sessionList.getLength(); i++) {
                Node sessionNode = sessionList.item(i);

                if (sessionNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element sessionElement = (Element) sessionNode;
                    int id = Integer.parseInt(sessionElement.getElementsByTagName("id").item(0).getTextContent());

                    List<Image> images = new ArrayList<>();
                    NodeList imageList = sessionElement.getElementsByTagName("image");
                    for (int j = 0; j < imageList.getLength(); j++) {
                        Element imageElement = (Element) imageList.item(j);
                        String path = imageElement.getElementsByTagName("path").item(0).getTextContent();
                        String filename = imageElement.getElementsByTagName("fileName").item(0).getTextContent();
                        images.add(new Image(path, filename));
                    }

                    Session session = new Session(images);
                    session.setId(id);

                    NodeList transformationList = sessionElement.getElementsByTagName("transformation");
                    for (int j = 0; j < transformationList.getLength(); j++) {
                        Element transformationElement = (Element) transformationList.item(j);
                        String type = transformationElement.getElementsByTagName("type").item(0).getTextContent();
                        session.addTransformation(new Transformation(type));
                    }

                    SessionManager.getInstance().addSession(session);
                }
            }
            SessionManager.getInstance().setFileOpened(true);
            System.out.println("Sessions loaded successfully");
            System.out.println("Use switch <session_id> to switch session");
        } catch (ParserConfigurationException | SAXException e) {
            System.err.println("Error parsing XML file");
        } catch (IOException e) {
            System.err.println("Error reading XML file");
        }
    }
}
