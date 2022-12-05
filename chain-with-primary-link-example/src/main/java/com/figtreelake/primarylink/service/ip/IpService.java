package com.figtreelake.primarylink.service.ip;

import com.figtreelake.primarylink.IpV4Flags;
import com.figtreelake.primarylink.service.ip.link.IpV4FlagCheckerLink;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IpService {

  @Getter(AccessLevel.PACKAGE)
  private final IpV4FlagCheckerLink firstLink;

  public Optional<IpV4Flags> retrieveIpV4Flags(byte[] data) {
    return firstLink.check(data);
  }
}
