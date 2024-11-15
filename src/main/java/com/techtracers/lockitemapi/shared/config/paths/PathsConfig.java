package com.techtracers.lockitemapi.shared.config.paths;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationProperties(prefix = "lockitem")
@ConfigurationPropertiesScan
public class PathsConfig {

}
