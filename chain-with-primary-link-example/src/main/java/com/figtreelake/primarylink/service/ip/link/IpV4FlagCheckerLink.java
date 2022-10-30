package com.figtreelake.primarylink.service.ip.link;

import com.figtreelake.corbeanprocessor.autoconfigure.link.ChainLink;
import com.figtreelake.primarylink.IpV4Flags;

import java.util.Optional;

public interface IpV4FlagCheckerLink extends ChainLink<IpV4FlagCheckerLink> {

  Optional<IpV4Flags> check(byte[] data);
}
