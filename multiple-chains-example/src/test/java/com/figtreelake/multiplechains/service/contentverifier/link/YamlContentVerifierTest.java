package com.figtreelake.multiplechains.service.contentverifier.link;

import com.figtreelake.multiplechains.service.contentverifier.ContentType;
import com.figtreelake.multiplechains.test.util.ClasspathInputReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class YamlContentVerifierTest {

  private YamlContentVerifier yamlContentVerifier;

  @BeforeEach
  void setUp() {
    yamlContentVerifier = new YamlContentVerifier();
  }

  @Test
  void shouldReturnContentTypeYamlWhenContentIsYaml() {
    final var content = ClasspathInputReaderUtil.read("yaml/yaml-file.yml");

    final var contentType = yamlContentVerifier.verify(content);

    assertThat(contentType).isEqualTo(ContentType.YAML);
  }

  @Test
  void shouldReturnContentTypeUnknownWhenContentIsNotYaml() {
    final var content = ClasspathInputReaderUtil.read("yaml/invalid-yaml-file.yml");

    final var contentType = yamlContentVerifier.verify(content);

    assertThat(contentType).isEqualTo(ContentType.UNKNOWN);
  }
}