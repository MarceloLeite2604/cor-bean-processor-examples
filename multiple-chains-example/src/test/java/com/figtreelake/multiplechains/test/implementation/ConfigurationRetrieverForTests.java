package com.figtreelake.multiplechains.test.implementation;

import com.figtreelake.multiplechains.service.configuration.retriever.AbstractConfigurationRetriever;
import lombok.Setter;

import java.net.URI;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("java:S2187")
public class ConfigurationRetrieverForTests extends AbstractConfigurationRetriever {

  public static final String SCHEME = "testScheme";
  public static final String DEFAULT_CONTENT = """
      property1=value1
      property2=value2
      property3=value3
      """;

  public static final Map<String, String> DEFAULT_PROPERTIES = Map.of("property1", "value1",
      "property2", "value2",
      "property3", "value3");

  @Setter
  private String content;

  public ConfigurationRetrieverForTests() {
    super(Set.of(SCHEME));
    this.content = DEFAULT_CONTENT;
  }

  @Override
  protected Map<String, String> doRetrieve(URI uri) {
    return splitConfigurationFileContent(content);
  }
}
