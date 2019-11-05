package com.pres.ik.demo;

import lombok.SneakyThrows;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * @author Dora
 * @date 2019/11/4 14:10
 **/
public class DomDemo {
    @SneakyThrows
    public static void main(String[] args) {
        DomDemo demo = new DomDemo();
        File file = new File(demo.test());
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element element = document.getRootElement();

        List entry = element.elements("entry");
        for (int i = 0; i < entry.size(); i++) {
            Element o = (Element) entry.get(i);
            System.out.println("entry = " + o);
            Attribute ext_stopwords = o.attribute("key");
            System.out.println("ext_stopwords = " + ext_stopwords.getValue());
            String text = o.getText();
            System.out.println("text = " + text);

            o.setText("测试");
            System.out.println("text = " + o.getText());
        }
    }

    public String test() {
        return this.getClass().getClassLoader().getResource("IKAnalyzer.cfg.xml").getPath();
    }
}
