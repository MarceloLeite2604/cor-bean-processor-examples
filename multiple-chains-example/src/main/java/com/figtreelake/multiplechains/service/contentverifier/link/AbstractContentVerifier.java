package com.figtreelake.multiplechains.service.contentverifier.link;

import com.figtreelake.multiplechains.service.contentverifier.ContentType;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractContentVerifier implements ContentVerifier {

  @Setter
  private ContentVerifier next;

  @Override
  public ContentType verify(String content) {
    return doVerify(content)
        .orElseGet(() ->
            Optional.ofNullable(next)
                .map(n -> n.verify(content))
                .orElse(ContentType.UNKNOWN)
        );
  }

  protected abstract Optional<ContentType> doVerify(String content);
}
