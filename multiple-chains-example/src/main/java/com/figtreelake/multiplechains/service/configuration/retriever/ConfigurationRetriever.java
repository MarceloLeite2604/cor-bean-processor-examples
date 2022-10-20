package com.figtreelake.multiplechains.service.configuration.retriever;


import com.figtreelake.corbeanprocessor.autoconfigure.link.ChainLink;

import java.net.URI;
import java.util.Map;

public interface ConfigurationRetriever extends ChainLink<ConfigurationRetriever> {
  Map<String, String> retrieve(URI uri);
}
