package com.figtreelake.multiplechains.service.configuration.retriever;

import com.figtreelake.multiplechains.test.implementation.ConfigurationRetrieverTestImplementation;
import org.apache.hc.core5.net.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AbstractConfigurationRetrieverTest {

  private AbstractConfigurationRetriever abstractConfigurationRetriever;

  @BeforeEach
  void setUp() {
    abstractConfigurationRetriever = new ConfigurationRetrieverTestImplementation();
  }

  @Test
  void shouldPassToNextLinkWhenSchemeIsNotRecognized() throws Exception {
    final var expectedProperties = Map.of("key", "value");

    final var uri = new URIBuilder().setScheme("unknownScheme")
        .setPath("path")
        .build();

    final var mockedConfigurationRetriever = mock(ConfigurationRetriever.class);

    when(mockedConfigurationRetriever.retrieve(any())).thenReturn(expectedProperties);

    abstractConfigurationRetriever.setNext(mockedConfigurationRetriever);

    final var properties = abstractConfigurationRetriever.retrieve(uri);

    assertThat(properties).containsAllEntriesOf(expectedProperties);
  }

  @Test
  void shouldRetrievePropertiesWhenSchemeIsRecognized() throws Exception {

    final var uri = new URIBuilder().setScheme(ConfigurationRetrieverTestImplementation.SCHEME)
        .setPath("path")
        .build();

    final var properties = abstractConfigurationRetriever.retrieve(uri);

    assertThat(properties).containsAllEntriesOf(ConfigurationRetrieverTestImplementation.DEFAULT_PROPERTIES);
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenCannotAttendSchemeAndThereIsNotNextLink() throws Exception {

    final var uri = new URIBuilder().setScheme("invalidScheme")
        .setPath("path")
        .build();

    assertThrows(IllegalArgumentException.class, () -> abstractConfigurationRetriever.retrieve(uri));
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenConfigurationLineIsInvalid() throws Exception {

    final var uri = new URIBuilder().setScheme(ConfigurationRetrieverTestImplementation.SCHEME)
        .setPath("path")
        .build();

    ((ConfigurationRetrieverTestImplementation)abstractConfigurationRetriever).setContent("property1=value1=anotherAssertion");

    assertThrows(IllegalArgumentException.class, () -> abstractConfigurationRetriever.retrieve(uri));
  }
}