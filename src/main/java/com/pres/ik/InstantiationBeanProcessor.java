package com.pres.ik;

import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.wltea.analyzer.cfg.DefaultConfig;

import javax.annotation.Resource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author Dora
 * @date 2019/11/4 19:17
 **/
@Configuration
@Order(100)
@EnableConfigurationProperties({ExtProperties.class})
public class InstantiationBeanProcessor {
    private static final String FILE_NAME = "IKAnalyzer.cfg.xml";
    private static final String EXT_DICT = "ext_dict";
    private static final String EXT_STOP = "ext_stopwords";
    @Resource
    ExtProperties extProperties;

    @Bean
    @SneakyThrows
    public org.wltea.analyzer.cfg.Configuration properties() {
        // 异常处理
        // 解决两个类的优先级问题 DefaultConfig 的优先级明显要高于InstantiationBeanProcessor
        String ext = extProperties.getExt();
        String extStop = extProperties.getExtStop();
        InputStream input = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
        Properties properties = new Properties();
        properties.loadFromXML(input);
        if (null != properties.get(EXT_DICT)) {
            properties.setProperty(EXT_DICT, ext);
        }
        if (null != properties.getProperty(EXT_STOP)) {
            properties.setProperty(EXT_STOP, extStop);
        }

        // 修改私有属性值
        org.wltea.analyzer.cfg.Configuration instance = DefaultConfig.getInstance();
        Class<? extends org.wltea.analyzer.cfg.Configuration> aClass = instance.getClass();
        Field props = aClass.getDeclaredField("props");
        props.setAccessible(true);
        props.set(instance, properties);
        return instance;
    }


}
