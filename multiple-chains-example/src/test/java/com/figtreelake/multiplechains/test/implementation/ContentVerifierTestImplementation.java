package com.figtreelake.multiplechains.test.implementation;

import com.figtreelake.multiplechains.service.contentverifier.ContentType;
import com.figtreelake.multiplechains.service.contentverifier.link.AbstractContentVerifier;
import lombok.Setter;

public class ContentVerifierTestImplementation extends AbstractContentVerifier {

  @Setter
  private ContentType contentType;

  public ContentVerifierTestImplementation() {
    contentType = ContentType.UNKNOWN;
  }

  @Override
  protected ContentType doVerify(String content) {
    return contentType;
  }
}
