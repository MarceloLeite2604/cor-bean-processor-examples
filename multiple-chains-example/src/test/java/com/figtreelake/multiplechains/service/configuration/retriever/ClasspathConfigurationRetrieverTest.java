package com.figtreelake.multiplechains.service.configuration.retriever;

import com.figtreelake.multiplechains.test.fixture.PropertiesFixture;
import org.apache.hc.core5.net.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClasspathConfigurationRetrieverTest {

  private ClasspathConfigurationRetriever classpathConfigurationRetriever;

  @BeforeEach
  void setUp() {
    classpathConfigurationRetriever = new ClasspathConfigurationRetriever();
  }

  @Test
  void shouldReturnPropertiesMap() throws Exception {

    final var expectedProperties = PropertiesFixture.createClasspathProperties();

    final var uri = new URIBuilder().setScheme("classpath")
        .setPath("/classpathConfigurationInput/configuration.txt")
        .build();

    final var properties = classpathConfigurationRetriever.retrieve(uri);

    assertThat(properties).containsAllEntriesOf(expectedProperties);
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenResourceIsNotFoundOnClasspath() throws Exception {

    final var uri = new URIBuilder().setScheme("classpath")
        .setPath("/invalid-resource-location.txt")
        .build();

    assertThrows(IllegalArgumentException.class,
        () -> classpathConfigurationRetriever.retrieve(uri));
  }
}