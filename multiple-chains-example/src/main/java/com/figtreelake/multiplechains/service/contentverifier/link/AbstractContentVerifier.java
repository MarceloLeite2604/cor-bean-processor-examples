package com.figtreelake.multiplechains.service.contentverifier.link;

import com.figtreelake.multiplechains.service.contentverifier.ContentType;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public abstract class AbstractContentVerifier implements ContentVerifier {

  @Setter
  private ContentVerifier next;

  @Override
  public ContentType verify(String content) {
    final var contentType = doVerify(content);

    if (ContentType.UNKNOWN.equals(contentType) && next != null) {
      return next.verify(content);
    }

    return contentType;
  }

  protected abstract ContentType doVerify(String content);
}
