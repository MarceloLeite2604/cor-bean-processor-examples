package com.figtreelake.multiplechains.service.configuration.retriever;

import com.figtreelake.multiplechains.test.fixture.PropertiesFixture;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileConfigurationRetrieverTest {

  private FileConfigurationRetriever fileConfigurationRetriever;

  @BeforeEach
  void setUp() {
    fileConfigurationRetriever = new FileConfigurationRetriever();
  }

  @Test
  void shouldReturnPropertiesMap() {

    final var expectedProperties = PropertiesFixture.createFileProperties();

    final var uri = Paths.get(Strings.EMPTY)
        .toAbsolutePath()
        .resolve("file-input/configuration.txt")
        .toUri();

    final var properties = fileConfigurationRetriever.retrieve(uri);

    assertThat(properties).containsAllEntriesOf(expectedProperties);
  }

  @Test
  void shouldThrowIllegalStateExceptionWhenFileCannotBeRead() {
    final var uri = Paths.get(Strings.EMPTY)
        .toAbsolutePath()
        .resolve("invalid-file-path.txt")
        .toUri();

    assertThrows(IllegalStateException.class,
        () -> fileConfigurationRetriever.retrieve(uri));
  }

}