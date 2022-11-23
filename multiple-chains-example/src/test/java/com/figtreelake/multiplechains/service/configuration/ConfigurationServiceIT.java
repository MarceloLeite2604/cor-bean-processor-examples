package com.figtreelake.multiplechains.service.configuration;

import com.figtreelake.multiplechains.test.AbstractIT;
import com.figtreelake.multiplechains.test.fixture.PropertiesFixture;
import com.figtreelake.multiplechains.test.fixture.UriFixture;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration-test")
@WireMockTest
class ConfigurationServiceIT extends AbstractIT {

  @Autowired
  private ConfigurationService configurationService;

  @Test
  void shouldRetrieveConfigurationFromClasspath() throws Exception {
    final var uri = UriFixture.createClasspathUri();
    final var expectedProperties = PropertiesFixture.createClasspathProperties();
    final var actualProperties = configurationService.retrieve(uri);

    assertThat(actualProperties).containsAllEntriesOf(expectedProperties);
  }

  @Test
  void shouldRetrieveConfigurationFromFile() {
    final var uri = UriFixture.createFileUri();
    final var expectedProperties = PropertiesFixture.createFileProperties();
    final var actualProperties = configurationService.retrieve(uri);

    assertThat(actualProperties).containsAllEntriesOf(expectedProperties);
  }

  @Test
  void shouldRetrieveConfigurationFromHttpRequest(WireMockRuntimeInfo wireMockRuntimeInfo) throws Exception {
    final var uri = UriFixture.createHttpUri(wireMockRuntimeInfo);
    final var expectedProperties = PropertiesFixture.createHttpProperties();

    final var responseBody = expectedProperties.entrySet()
        .stream()
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .collect(Collectors.joining("\n"));

    stubFor(get(UriFixture.HTTP_CONFIGURATION_PATH)
        .willReturn(ok().withBody(responseBody)));

    final var actualProperties = configurationService.retrieve(uri);

    assertThat(actualProperties).containsAllEntriesOf(expectedProperties);
  }
}
