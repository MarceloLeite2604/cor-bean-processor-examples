package com.figtreelake.singlechain.link;

import org.springframework.stereotype.Component;

@Component
public class IntegerTypeFinderLink extends AbstractTypeFinderLink {

  public static final String TYPE_MESSAGE = "Object is an integer.";

  @Override
  protected boolean applies(Object object) {
    return object instanceof Integer;
  }

  @Override
  protected String doFindType(Object object) {
    return TYPE_MESSAGE;
  }
}
