package com.figtreelake.multiplechains.service.contentverifier;

import com.figtreelake.multiplechains.service.contentverifier.link.ContentVerifier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContentVerifierService {

  private final ContentVerifier contentVerifier;

  public ContentType verify(String content) {
    return contentVerifier.verify(content);
  }
}
