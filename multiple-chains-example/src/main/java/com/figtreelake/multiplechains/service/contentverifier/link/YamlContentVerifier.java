package com.figtreelake.multiplechains.service.contentverifier.link;

import com.figtreelake.multiplechains.service.contentverifier.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.StringReader;
import java.util.Optional;

@Component
@Slf4j
public class YamlContentVerifier extends AbstractContentVerifier {

  private final Yaml yaml;

  public YamlContentVerifier() {
    this.yaml = new Yaml();
  }

  @Override
  protected Optional<ContentType> doVerify(String content) {
    final var contentReader = new StringReader(content);
    final var events = yaml.parse(contentReader);

    try {
      while (events.iterator()
          .hasNext()) {
        events.iterator().next();
      }
      return Optional.of(ContentType.YAML);
    } catch (Exception exception) {
      return Optional.empty();
    }

  }
}
