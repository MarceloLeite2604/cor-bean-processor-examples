package com.figtreelake.primarylink.service.ip.link;

import com.figtreelake.primarylink.IpV4Flags;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DoNotFragmentFlagCheckerLink extends AbstractIpV4FlagCheckerLink {

  private static final byte DNF_FLAG_BIT = 0x40;

  @Override
  protected Optional<Context> doCheck(Context context) {
    final var flags = context.getData()[6];

    final var doNotFragment = (flags & DNF_FLAG_BIT) == DNF_FLAG_BIT;

    final var ipV4Flags = Optional.ofNullable(context.getIpV4Flags())
        .map(IpV4Flags::toBuilder)
        .orElseGet(IpV4Flags::builder)
        .doNotFragment(doNotFragment)
        .build();

    context.setIpV4Flags(ipV4Flags);

    return Optional.of(context);
  }
}
