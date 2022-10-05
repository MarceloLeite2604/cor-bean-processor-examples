package com.figtreelake.multiplechains.service.configuration.retriever;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FileConfigurationRetriever extends AbstractConfigurationRetriever {

  public FileConfigurationRetriever() {
    super(Set.of("file"));
  }

  @Override
  protected Map<String, String> doRetrieve(URI uri) {
    final var path = Path.of(uri.getPath());
    try {
      return Files.readAllLines(path)
          .stream()
          .map(super::retrievePropertyLineAsMapEntry)
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    } catch (IOException ioException) {
      final var message = String.format("Exception thrown while reading properties from file \"%s\".", path);
      throw new IllegalStateException(message, ioException);
    }
  }


}
