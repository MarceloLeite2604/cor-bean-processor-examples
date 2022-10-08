package com.figtreelake.multiplechains.service.contentverifier;

import com.figtreelake.multiplechains.service.contentverifier.link.ContentVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContentVerifierService {

  private final ContentVerifier contentVerifier;

  public ContentType verify(String content) {
    return contentVerifier.verify(content);
  }
}
