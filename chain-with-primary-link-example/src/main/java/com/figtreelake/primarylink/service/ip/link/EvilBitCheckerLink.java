package com.figtreelake.primarylink.service.ip.link;

import com.figtreelake.primarylink.IpV4Flags;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * For more details about the evil bit flag definition please check
 * <a href="https://www.ietf.org/rfc/rfc3514.txt">RFC-3514</a>.
 */
@Component
public class EvilBitCheckerLink extends AbstractIpV4FlagCheckerLink {

  // Curse you, Java definition of byte as signalized, for this unnecessary casting!
  private static final byte EVIL_BIT_FLAG = (byte) 0x80;

  @Override
  protected Optional<Context> doCheck(Context context) {
    final var flags = context.getData()[6];

    final var evilBit = (flags & EVIL_BIT_FLAG) == EVIL_BIT_FLAG;

    final var ipV4Flags = Optional.ofNullable(context.getIpV4Flags())
        .map(IpV4Flags::toBuilder)
        .orElseGet(IpV4Flags::builder)
        .evilBit(evilBit)
        .build();

    context.setIpV4Flags(ipV4Flags);

    return Optional.of(context);
  }
}
