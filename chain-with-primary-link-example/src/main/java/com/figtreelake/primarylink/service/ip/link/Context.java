package com.figtreelake.primarylink.service.ip.link;

import com.figtreelake.primarylink.IpV4Flags;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public
class Context {

  private final byte[] data;

  @Setter
  private IpV4Flags ipV4Flags;
}
