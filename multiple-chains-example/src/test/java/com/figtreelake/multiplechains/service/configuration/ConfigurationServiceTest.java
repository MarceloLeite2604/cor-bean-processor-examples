package com.figtreelake.multiplechains.service.configuration;

import com.figtreelake.multiplechains.service.configuration.retriever.ConfigurationRetriever;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfigurationServiceTest {

  @InjectMocks
  private ConfigurationService configurationService;

  @Mock
  private ConfigurationRetriever configurationRetriever;

  @Test
  void shouldReturnConfiguration() {
    final Map<String, String> expectedConfiguration = Map.of("property", "value");
    final var uri = URI.create("file:///example-file.txt");

    when(configurationRetriever.retrieve(uri)).thenReturn(expectedConfiguration);

    final var configuration = configurationService.retrieve(uri);

    assertThat(configuration).isEqualTo(expectedConfiguration);
  }
}