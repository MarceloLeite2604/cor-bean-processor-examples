package com.figtreelake.singlechain.link;

import org.springframework.stereotype.Component;

@Component
public class DoubleTypeFinderLink extends AbstractTypeFinderLink {

  public static final String TYPE_MESSAGE = "Object is a double.";

  @Override
  protected boolean applies(Object object) {
    return object instanceof Double;
  }

  @Override
  protected String doFindType(Object object) {
    return TYPE_MESSAGE;
  }
}
