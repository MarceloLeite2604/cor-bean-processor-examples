package com.figtreelake.primarylink;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class IpV4Flags {

  private boolean doNotFragment;

  private boolean moreFragments;

  private boolean evilBit;
}
