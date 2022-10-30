package com.figtreelake.primarylink.test.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HexFormat;

@UtilityClass
public class HexStreamFileToByteArrayUtil {

  public static byte[] readFile(Path path) {
    try {
      final var content = Files.readString(path);
      return HexFormat.of().parseHex(content);
    } catch (IOException ioException) {
      final var message = String.format("Exception thrown while reading file \"%s\".", path);
      throw new IllegalStateException(message, ioException);
    }
  }
}
