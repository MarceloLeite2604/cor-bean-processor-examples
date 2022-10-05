package com.figtreelake.multiplechains.service.configuration.retriever;


import com.figtreelake.multiplechains.test.fixture.PropertiesFixture;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.apache.hc.core5.net.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WireMockTest
class HttpConfigurationRetrieverTest {

  private static final String CONFIGURATION_PATH = "/configuration";

  private HttpConfigurationRetriever httpConfigurationRetriever;

  @BeforeEach
  void setUp() {
    final var webClient = WebClient.builder()
        .build();

    httpConfigurationRetriever = new HttpConfigurationRetriever(webClient);
  }

  @Test
  void shouldReturnProperties(WireMockRuntimeInfo wireMockRuntimeInfo) throws Exception {

    final var expectedProperties = PropertiesFixture.createHttpProperties();

    final var responseBody = expectedProperties.entrySet()
        .stream()
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .collect(Collectors.joining("\n"));

    stubFor(get(CONFIGURATION_PATH).willReturn(ok().withBody(responseBody)));

    final var uri = new URIBuilder(wireMockRuntimeInfo.getHttpBaseUrl())
        .setPath(CONFIGURATION_PATH)
        .build();

    final var properties = httpConfigurationRetriever.doRetrieve(uri);

    assertThat(properties).containsAllEntriesOf(expectedProperties);
  }

  @MethodSource("provideParametersForShouldThrowIllegalStateExceptionWhenHttpConnectionReturnStatusDifferentFromOkTest")
  @ParameterizedTest
  void shouldThrowIllegalStateExceptionWhenHttpConnectionReturnStatusDifferentFromOk(
      HttpStatus httpStatus,
      WireMockRuntimeInfo wireMockRuntimeInfo) throws Exception {

    stubFor(get(CONFIGURATION_PATH).willReturn(status(httpStatus.value())));

    final var uri = new URIBuilder(wireMockRuntimeInfo.getHttpBaseUrl())
        .setPath(CONFIGURATION_PATH)
        .build();

    assertThrows(IllegalStateException.class,
        () -> httpConfigurationRetriever.doRetrieve(uri));
  }

  private static Stream<HttpStatus> provideParametersForShouldThrowIllegalStateExceptionWhenHttpConnectionReturnStatusDifferentFromOkTest() {
    return Stream.of(HttpStatus.BAD_REQUEST,
        HttpStatus.INTERNAL_SERVER_ERROR,
        HttpStatus.GATEWAY_TIMEOUT,
        HttpStatus.FORBIDDEN,
        HttpStatus.UNAUTHORIZED);
  }
}