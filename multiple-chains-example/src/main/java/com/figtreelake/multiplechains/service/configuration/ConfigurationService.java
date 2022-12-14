package com.figtreelake.multiplechains.service.configuration;

import com.figtreelake.multiplechains.service.configuration.retriever.ConfigurationRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ConfigurationService {

  private final ConfigurationRetriever firstLink;

  public Map<String, String> retrieve(URI uri) {
    return firstLink.retrieve(uri);
  }
}
