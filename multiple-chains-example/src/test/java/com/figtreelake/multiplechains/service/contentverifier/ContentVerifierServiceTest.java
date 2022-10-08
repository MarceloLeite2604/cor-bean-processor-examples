package com.figtreelake.multiplechains.service.contentverifier;

import com.figtreelake.multiplechains.service.contentverifier.link.ContentVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContentVerifierServiceTest {

  @InjectMocks
  private ContentVerifierService contentVerifierService;

  @Mock
  private ContentVerifier contentVerifier;

  @Test
  void shouldReturnContentType() {
    final var expectedContentType = ContentType.JSON;
    final var content = "contentValue";
    when(contentVerifier.verify(anyString())).thenReturn(expectedContentType);

    final var contentType = contentVerifierService.verify(content);

    assertThat(contentType).isEqualTo(expectedContentType);
  }
}