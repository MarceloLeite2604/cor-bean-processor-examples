package com.figtreelake.multiplechains.service.configuration.retriever;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class AbstractConfigurationRetriever implements ConfigurationRetriever {

  private static final int PROPERTY_NAME_INDEX = 0;
  private static final int PROPERTY_VALUE_INDEX = 1;

  private final Set<String> attendedSchemes;

  @Setter
  private ConfigurationRetriever next;

  @Override
  public Map<String, String> retrieve(URI uri) {
    Assert.notNull(uri, "Configuration URI cannot be null.");

    if (canAttend(uri)) {
      return doRetrieve(uri);
    }

    if (next != null) {
      return next.retrieve(uri);
    }

    final var message = String.format("Cannot retrieve configuration from URL \"%s\".", uri.toASCIIString());
    throw new IllegalArgumentException(message);
  }

  private boolean canAttend(URI uri) {
    return attendedSchemes.contains(uri.getScheme());
  }

  protected Map<String, String> splitConfigurationFileContent(String content) {
    Assert.hasLength(content, "Retrieved configuration content cannot be null or empty.");

    return Stream.of(content.split("\n"))
        .map(this::retrievePropertyLineAsMapEntry)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  protected Map.Entry<String, String> retrievePropertyLineAsMapEntry(String line) {
    Assert.hasLength(line, "Configuration line cannot be null or empty.");

    final var lineParts = line.split("=");
    if (lineParts.length != 2) {
      final var message = String.format("Property line is invalid: \"%s\"", line);
      throw new IllegalArgumentException(message);
    }

    final var propertyName = lineParts[PROPERTY_NAME_INDEX].trim();
    final var propertyValue = lineParts[PROPERTY_VALUE_INDEX].trim();

    return Map.entry(propertyName, propertyValue);
  }

  protected abstract Map<String, String> doRetrieve(URI uri);
}
