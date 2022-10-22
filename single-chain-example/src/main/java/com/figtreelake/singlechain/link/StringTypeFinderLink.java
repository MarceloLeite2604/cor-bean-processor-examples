package com.figtreelake.singlechain.link;

import org.springframework.stereotype.Component;

@Component
public class StringTypeFinderLink extends AbstractTypeFinderLink {

  public static final String TYPE_MESSAGE = "Object is a string.";

  @Override
  protected boolean applies(Object object) {
    return object instanceof String;
  }

  @Override
  protected String doFindType(Object object) {
    return TYPE_MESSAGE;
  }
}
