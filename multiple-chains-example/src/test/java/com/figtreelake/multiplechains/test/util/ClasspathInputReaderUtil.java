package com.figtreelake.multiplechains.test.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

import java.io.IOException;

@UtilityClass
public class ClasspathInputReaderUtil {

  private static final String RESOURCE_PATH_PREFIX = "classpath-input/";

  public static String read(String resourcePath) {
    final var completeResourcePath = RESOURCE_PATH_PREFIX + resourcePath;
    try (var inputStream = ClassLoader.getSystemClassLoader()
        .getResourceAsStream(completeResourcePath)) {
      Assert.notNull(inputStream, "Resource input stream is null.");
      return new String(inputStream.readAllBytes());
    } catch (IOException ioException) {
      final var message = String.format("Exception thrown while " +
          "reading classpath resource \"%s\".", ioException);
      throw new IllegalStateException(message, ioException);
    }
  }
}
