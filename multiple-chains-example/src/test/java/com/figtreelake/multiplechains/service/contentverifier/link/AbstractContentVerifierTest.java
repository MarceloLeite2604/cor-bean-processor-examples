package com.figtreelake.multiplechains.service.contentverifier.link;

import com.figtreelake.multiplechains.service.contentverifier.ContentType;
import com.figtreelake.multiplechains.test.implementation.ContentVerifierTestImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AbstractContentVerifierTest {

  private AbstractContentVerifier abstractContentVerifier;

  @BeforeEach
  void setUp() {
    abstractContentVerifier = new ContentVerifierTestImplementation();
  }

  @Test
  void shouldReturnContentTypeInformedBySpecializationWhenContentMatches() {
    final var expectedContentType = ContentType.JSON;
    final var content = "contentValue";

    defineContentResponse(expectedContentType);

    final var contentType = abstractContentVerifier.verify(content);

    assertThat(contentType).isEqualTo(expectedContentType);
  }

  @Test
  void shouldPassRequestToNextContentVerifier() {
    final var expectedContentType = ContentType.JSON;
    final var content = "contentValue";

    final var mockedContentVerifier = mock(ContentVerifier.class);

    when(mockedContentVerifier.verify(content)).thenReturn(expectedContentType);

    abstractContentVerifier.setNext(mockedContentVerifier);

    final var contentType = abstractContentVerifier.verify(content);

    assertThat(contentType).isEqualTo(expectedContentType);
    verify(mockedContentVerifier, times(1)).verify(content);
  }

  @Test
  void shouldReturnContentTypeUnknownWhenContentIsNotRecognized() {
    final var expectedContentType = ContentType.UNKNOWN;
    final var content = "contentValue";

    final var mockedContentVerifier = mock(ContentVerifier.class);

    when(mockedContentVerifier.verify(content)).thenReturn(expectedContentType);

    abstractContentVerifier.setNext(mockedContentVerifier);

    final var contentType = abstractContentVerifier.verify(content);

    assertThat(contentType).isEqualTo(expectedContentType);
    verify(mockedContentVerifier, times(1)).verify(content);
  }

  private void defineContentResponse(ContentType contentType) {
    ((ContentVerifierTestImplementation)abstractContentVerifier).setContentType(contentType);
  }

}