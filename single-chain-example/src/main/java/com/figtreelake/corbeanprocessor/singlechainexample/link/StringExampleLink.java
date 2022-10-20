package com.figtreelake.corbeanprocessor.singlechainexample.link;

import org.springframework.stereotype.Component;

@Component
public class StringExampleLink extends AbstractExampleLink {

  @Override
  protected boolean applies(Object object) {
    return object instanceof String;
  }

  @Override
  protected String doFindType(Object object) {
    return "Object is a string.";
  }
}
