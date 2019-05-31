package edu.uw.aws.cliloginpassthrough.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author Maxime Deravet Date: 2019-05-31
 */
@ConfigurationProperties(prefix = "passthrough")
@Data
public class PassthroughProperties {

    private String localPortParamName = "localPort";
    private int defaultPort = 8080;
}
