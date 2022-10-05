package com.figtreelake.multiplechains.service.configuration.retriever;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Map;
import java.util.Set;


@Component
public class HttpConfigurationRetriever extends AbstractConfigurationRetriever {

  private final WebClient webClient;

  public HttpConfigurationRetriever(WebClient webClient) {
    super(Set.of("http", "https"));
    this.webClient = webClient;
  }

  @Override
  protected Map<String, String> doRetrieve(URI uri) {
    return webClient.get()
        .uri(uri)
        .retrieve()
        .bodyToMono(String.class)
        .onErrorMap(throwable -> {
          final var message = String.format("Exception thrown while retrieving configuration from URL \"%s\".", uri);
          return new IllegalStateException(message, throwable);
        })
        .map(super::splitConfigurationFileContent)
        .block();
  }
}
