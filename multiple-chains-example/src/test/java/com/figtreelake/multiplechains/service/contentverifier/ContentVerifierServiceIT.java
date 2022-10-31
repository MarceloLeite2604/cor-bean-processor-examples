package com.figtreelake.multiplechains.service.contentverifier;

import com.figtreelake.multiplechains.service.contentverifier.ContentType;
import com.figtreelake.multiplechains.service.contentverifier.ContentVerifierService;
import com.figtreelake.multiplechains.test.AbstractIT;
import com.figtreelake.multiplechains.test.util.ClasspathInputReaderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ContentVerifierServiceIT extends AbstractIT {

  @Autowired
  private ContentVerifierService contentVerifierService;

  @Test
  void shouldReturnContentTypeJsonWhenContentIsJson() {
    final var content = ClasspathInputReaderUtil.read("json/json-file.json");

    final var expectedType = ContentType.JSON;

    final var actualType = contentVerifierService.verify(content);

    assertThat(actualType).isEqualTo(expectedType);
  }

  @Test
  void shouldReturnContentTypeXmlWhenContentIsXml() {
    final var content = ClasspathInputReaderUtil.read("xml/xml-file.xml");

    final var expectedType = ContentType.XML;

    final var actualType = contentVerifierService.verify(content);

    assertThat(actualType).isEqualTo(expectedType);
  }

  @Test
  void shouldReturnContentTypeYamlWhenContentIsYaml() {
    final var content = ClasspathInputReaderUtil.read("yaml/yaml-file.yml");

    final var expectedType = ContentType.YAML;

    final var actualType = contentVerifierService.verify(content);

    assertThat(actualType).isEqualTo(expectedType);
  }

  @Test
  void shouldReturnContentTypeUnknownWhenContentIsUnknown() {
    final var content = ClasspathInputReaderUtil.read("yaml/invalid-yaml-file.yml");

    final var expectedType = ContentType.UNKNOWN;

    final var actualType = contentVerifierService.verify(content);

    assertThat(actualType).isEqualTo(expectedType);
  }
}
