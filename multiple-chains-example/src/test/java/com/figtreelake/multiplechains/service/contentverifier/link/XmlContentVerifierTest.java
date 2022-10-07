package com.figtreelake.multiplechains.service.contentverifier.link;

import com.figtreelake.multiplechains.service.contentverifier.ContentType;
import com.figtreelake.multiplechains.test.util.ClasspathInputReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class XmlContentVerifierTest {

  private XmlContentVerifier xmlContentVerifier;

  @BeforeEach
  void setUp() {
    xmlContentVerifier = new XmlContentVerifier();
  }

  @Test
  void shouldReturnContentTypeXmlWhenContentIsXml() {
    final var content = ClasspathInputReaderUtil.read("xml/xml-file.xml");

    final var contentType = xmlContentVerifier.verify(content);

    assertThat(contentType).isEqualTo(ContentType.XML);
  }

  @Test
  void shouldReturnContentTypeUnknownWhenContentIsNotXml() {
    final var content = ClasspathInputReaderUtil.read("xml/invalid-xml-file.xml");

    final var contentType = xmlContentVerifier.verify(content);

    assertThat(contentType).isEqualTo(ContentType.UNKNOWN);
  }

}