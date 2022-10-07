package com.figtreelake.multiplechains.service.configuration.retriever;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

@Component
public class ClasspathConfigurationRetriever extends AbstractConfigurationRetriever {

  public ClasspathConfigurationRetriever() {
    super(Set.of("classpath"));
  }

  @Override
  protected Map<String, String> doRetrieve(URI uri) {
    var path = Path.of(uri.getPath())
        .toString();

    path = path.replaceAll("^/+", Strings.EMPTY);

    try (var inputStream = ClassLoader.getSystemClassLoader()
        .getResourceAsStream(path)) {
      Assert.notNull(inputStream, String.format("Property file \"%s\" not found on classpath.", uri));

      final var content = new String(inputStream.readAllBytes());

      return splitConfigurationFileContent(content);

    } catch (IOException ioException) {
      final var message = String.format("Exception thrown while reading properties from file \"%s\".", path);
      throw new IllegalStateException(message, ioException);
    }
  }
}
