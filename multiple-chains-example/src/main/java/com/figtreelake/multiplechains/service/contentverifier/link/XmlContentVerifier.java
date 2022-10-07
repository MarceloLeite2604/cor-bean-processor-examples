package com.figtreelake.multiplechains.service.contentverifier.link;

import com.figtreelake.multiplechains.service.contentverifier.ContentType;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

@Component
public class XmlContentVerifier extends AbstractContentVerifier {

  private final DocumentBuilderFactory documentBuilderFactory;

  public XmlContentVerifier() {
    documentBuilderFactory = DocumentBuilderFactory.newInstance();
  }

  @Override
  protected Optional<ContentType> doVerify(String content) {
    try (var inputStream = new ByteArrayInputStream((content.getBytes()))) {
      documentBuilderFactory.newDocumentBuilder()
          .parse(inputStream);
      return Optional.of(ContentType.XML);
    } catch (IOException | ParserConfigurationException exception) {
      throw new IllegalStateException("Exception thrown while parsing XML content.", exception);
    } catch (SAXException saxException) {
      return Optional.empty();
    }
  }
}
