package com.figtreelake.multiplechains.service.configuration;

import com.figtreelake.multiplechains.service.configuration.retriever.ConfigurationRetriever;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.Map;

@RequiredArgsConstructor
public class ConfigurationService {

  private final ConfigurationRetriever configurationRetriever;

  public Map<String, String> retrieve(URI uri) {
    return configurationRetriever.retrieve(uri);
  }
}
