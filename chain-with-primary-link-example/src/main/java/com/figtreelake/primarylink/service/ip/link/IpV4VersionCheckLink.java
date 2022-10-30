package com.figtreelake.primarylink.service.ip.link;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Primary
@Component
public class IpV4VersionCheckLink extends AbstractIpV4FlagCheckerLink {

  @Override
  protected Optional<Context> doCheck(Context context) {
    final var data = context.getData();

    if (data.length < 20) {
      return Optional.empty();
    }

    if ((data[0] & 0xf0) != 0x40) {
      return Optional.empty();
    }

    return Optional.of(context);
  }
}
