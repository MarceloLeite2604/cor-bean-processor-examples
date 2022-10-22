package com.figtreelake.singlechain.test.implementation;

import com.figtreelake.singlechain.link.AbstractTypeFinderLink;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AbstractTypeFinderLinkImplementation extends AbstractTypeFinderLink {

  private final boolean appliesResult;

  private final String doFindTypeResult;

  @Override
  protected boolean applies(Object object) {
    return appliesResult;
  }

  @Override
  protected String doFindType(Object object) {
    return doFindTypeResult;
  }
}
