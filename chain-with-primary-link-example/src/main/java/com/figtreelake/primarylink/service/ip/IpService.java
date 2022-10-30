package com.figtreelake.primarylink.service.ip;

import com.figtreelake.primarylink.IpV4Flags;
import com.figtreelake.primarylink.service.ip.link.IpV4FlagCheckerLink;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IpService {

  private IpV4FlagCheckerLink firstIpV4FlagCheckerLink;

  public Optional<IpV4Flags> retrieveIpV4Flags(byte[] data) {
    return firstIpV4FlagCheckerLink.check(data);
  }
}
