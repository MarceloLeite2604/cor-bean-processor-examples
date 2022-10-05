package com.figtreelake.multiplechains.test.fixture;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class PropertiesFixture {

  public static Map<String, String> createFileProperties() {
    return Map.of("fileConfigurationProperty1", "fileConfigurationValue1",
        "fileConfigurationProperty2", "fileConfigurationValue2",
        "fileConfigurationProperty3", "fileConfigurationValue3");
  }

  public static Map<String, String> createClasspathProperties() {
    return Map.of("classpathConfigurationProperty1", "classpathConfigurationValue1",
        "classpathConfigurationProperty2", "classpathConfigurationValue2",
        "classpathConfigurationProperty3", "classpathConfigurationValue3");

  }

  public static Map<String, String> createHttpProperties() {
    return Map.of("httpConfigurationProperty1", "httpConfigurationValue1",
        "httpConfigurationProperty2", "httpConfigurationValue2",
        "httpConfigurationProperty3", "httpConfigurationValue3");

  }
}
