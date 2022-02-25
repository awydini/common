package net.aydini.common.util;

import net.aydini.common.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class NativeQueryUtil {

    private final static Logger log = LoggerFactory.getLogger(NativeQueryUtil.class);

    private  final String fileName;

    public NativeQueryUtil(String fileName) {
        this.fileName = fileName;
    }

    /**
     * finds a query by its name from Native-Queries.xml file.
     *
     * @param queryName
     * @return query String.
     */
    public String getQuery(String queryName) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(getInputStream());
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("query");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getAttribute("name");
                    if(name == null || !name.equals(queryName))
                        continue;
                    return element.getElementsByTagName("value").item(0).getTextContent();
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error("error finding query {} : {}",queryName,e.getMessage());
            throw new CommonException(e.getMessage(),e);
        }
        throw new RuntimeException("query not found");
    }


    private InputStream getInputStream()
    {
        InputStream stream = null;
        try {
            stream=new FileInputStream(new File(fileName));
        }catch (FileNotFoundException e)
        {
            log.error("error reading query file {} ",e.getMessage());
        }
        if(stream == null)
            this.getClass().getResourceAsStream(fileName);
        if(stream== null)
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        return stream;
    }
}
