package com.figtreelake.primarylink.service.ip.link;

import com.figtreelake.primarylink.test.util.FilePath;
import com.figtreelake.primarylink.test.util.HexStreamFileToByteArrayUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoreFragmentsFlagCheckerLinkTest {

  private MoreFragmentsFlagCheckerLink moreFragmentsFlagCheckerLink;

  @BeforeEach
  void setUp() {
    moreFragmentsFlagCheckerLink = new MoreFragmentsFlagCheckerLink();
  }

  @Test
  void shouldReturnMoreFragmentsAsTrueWhenIpv4PackageHasMoreFragmentsBitActivated() {
    final var content = HexStreamFileToByteArrayUtil.readFile(FilePath.IpV4PackageHexStream.MoreFragmentsFlag.ENABLED);

    final var optionalIpV4Flags = moreFragmentsFlagCheckerLink.check(content);

    assertThat(optionalIpV4Flags).isPresent();
    assertThat(optionalIpV4Flags.get()
        .isMoreFragments()).isTrue();
  }

  @Test
  void shouldReturnMoreFragmentsAsFalseWhenIpv4PackageDoesNotHaveMoreFragmentsBitActivated() {
    final var content = HexStreamFileToByteArrayUtil.readFile(FilePath.IpV4PackageHexStream.MoreFragmentsFlag.DISABLED);

    final var optionalIpV4Flags = moreFragmentsFlagCheckerLink.check(content);

    assertThat(optionalIpV4Flags).isPresent();
    assertThat(optionalIpV4Flags.get()
        .isMoreFragments()).isFalse();
  }

}