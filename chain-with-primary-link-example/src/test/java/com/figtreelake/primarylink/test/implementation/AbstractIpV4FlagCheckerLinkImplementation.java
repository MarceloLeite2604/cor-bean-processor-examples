package com.figtreelake.primarylink.test.implementation;

import com.figtreelake.primarylink.service.ip.link.AbstractIpV4FlagCheckerLink;
import com.figtreelake.primarylink.service.ip.link.Context;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AbstractIpV4FlagCheckerLinkImplementation extends AbstractIpV4FlagCheckerLink {

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  private final Optional<Context> doCheckResponse;

  @Override
  protected Optional<Context> doCheck(Context context) {
    return doCheckResponse;
  }
}
