package com.figtreelake.primarylink.test.fixture;

import com.figtreelake.primarylink.IpV4Flags;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IpV4FlagsFixture {

  public static IpV4Flags create() {
    return IpV4Flags.builder()
        .doNotFragment(true)
        .moreFragments(false)
        .evilBit(true)
        .build();
  }
}
