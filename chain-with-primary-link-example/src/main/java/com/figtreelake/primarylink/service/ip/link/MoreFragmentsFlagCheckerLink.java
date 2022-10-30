package com.figtreelake.primarylink.service.ip.link;

import com.figtreelake.primarylink.IpV4Flags;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MoreFragmentsFlagCheckerLink extends AbstractIpV4FlagCheckerLink {

  private static final byte MF_FLAG_BIT = 0x20;

  @Override
  protected Optional<Context> doCheck(Context context) {
    final var flags = context.getData()[6];

    final var moreFragments = (flags & MF_FLAG_BIT) == MF_FLAG_BIT;

    final var ipV4Flags = Optional.ofNullable(context.getIpV4Flags())
        .map(IpV4Flags::toBuilder)
        .orElseGet(IpV4Flags::builder)
        .moreFragments(moreFragments)
        .build();

    context.setIpV4Flags(ipV4Flags);

    return Optional.of(context);
  }
}
