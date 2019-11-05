package com.pres.ik.demo;

import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Dora
 * @date 2019/11/5 10:30
 **/
public class PropertiesDemo {

    @SneakyThrows
    public static void main(String[] args) {
        PropertiesDemo demo = new PropertiesDemo();
        Properties properties = new Properties();
        properties.loadFromXML(demo.getFileInputStream());

        properties.setProperty("ext_dict", "我是测试,hhaha");
        String filePath = demo.getFilePath();
        properties.storeToXML(new FileOutputStream(filePath), "IKAnalyzer.cfg.xml");
        System.out.println("写入成功!! ");
    }

    public InputStream getFileInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream("IKAnalyzer.cfg.xml");
    }

    public String getFilePath() {
        return this.getClass().getClassLoader().getResource("IKAnalyzer.cfg.xml").getPath();
    }
}
