package com.pres.ik;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Dora
 * @date 2019/11/4 13:47
 **/
@Component

@Data
@ConfigurationProperties(prefix = "ik")
public class ExtProperties {
    private String ext;

    private String extStop;
}
