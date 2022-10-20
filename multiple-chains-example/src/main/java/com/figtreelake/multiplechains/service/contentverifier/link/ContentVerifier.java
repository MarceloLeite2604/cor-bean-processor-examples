package com.figtreelake.multiplechains.service.contentverifier.link;

import com.figtreelake.corbeanprocessor.autoconfigure.link.ChainLink;
import com.figtreelake.multiplechains.service.contentverifier.ContentType;

public interface ContentVerifier extends ChainLink<ContentVerifier> {

  ContentType verify(String content);
}
