package com.figtreelake.multiplechains.service.contentverifier.link;

import com.figtreelake.multiplechains.service.contentverifier.ContentType;
import com.figtreelake.multiplechains.test.util.ClasspathInputReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JsonContentVerifierTest {

  private JsonContentVerifier jsonContentVerifier;

  @BeforeEach
  void setUp() {
    jsonContentVerifier = new JsonContentVerifier();
  }

  @Test
  void shouldReturnContentTypeJsonWhenContentIsJson() {
    final var content = ClasspathInputReaderUtil.read("json/json-file.json");

    final var contentType = jsonContentVerifier.verify(content);

    assertThat(contentType).isEqualTo(ContentType.JSON);
  }

  @Test
  void shouldReturnContentTypeUnknownWhenContentIsNotJson() {
    final var content = ClasspathInputReaderUtil.read("json/invalid-json-file.json");

    final var contentType = jsonContentVerifier.verify(content);

    assertThat(contentType).isEqualTo(ContentType.UNKNOWN);
  }
}