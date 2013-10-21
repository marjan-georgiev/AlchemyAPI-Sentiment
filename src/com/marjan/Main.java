package com.marjan;

import com.alchemyapi.api.AlchemyAPI;
import com.alchemyapi.api.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
        AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromString("API KEY");

        // load text
        String text = readFile("text_files/text.txt", Charset.defaultCharset());

        // analyze text
        Document doc = alchemyObj.TextGetTextSentiment(text);

        // parse XML result
        String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
        String score = doc.getElementsByTagName("score").item(0).getTextContent();
        // print results
        System.out.println("Sentiment: " + sentiment);
        System.out.println("Score: " + score);
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }
}
