package com.figtreelake.singlechain.link;

import org.springframework.stereotype.Component;

@Component
public class NullTypeFinderLink extends AbstractTypeFinderLink {

  public static final String TYPE_MESSAGE = "Object is null.";

  @Override
  protected boolean applies(Object object) {
    return object == null;
  }

  @Override
  protected String doFindType(Object object) {
    return TYPE_MESSAGE;
  }
}
