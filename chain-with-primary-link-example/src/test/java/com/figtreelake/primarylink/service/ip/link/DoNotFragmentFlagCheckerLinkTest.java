package com.figtreelake.primarylink.service.ip.link;

import com.figtreelake.primarylink.test.util.FilePath;
import com.figtreelake.primarylink.test.util.HexStreamFileToByteArrayUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoNotFragmentFlagCheckerLinkTest {

  private DoNotFragmentFlagCheckerLink doNotFragmentFlagCheckerLink;

  @BeforeEach
  void setUp() {
    doNotFragmentFlagCheckerLink = new DoNotFragmentFlagCheckerLink();
  }

  @Test
  void shouldReturnDoNotFragmentAsTrueWhenIpv4PackageHasDoNotFragmentBitActivated() {
    final var content = HexStreamFileToByteArrayUtil.readFile(FilePath.IpV4PackageHexStream.DoNotFragmentFlag.ENABLED);

    final var optionalIpV4Flags = doNotFragmentFlagCheckerLink.check(content);

    assertThat(optionalIpV4Flags).isPresent();
    assertThat(optionalIpV4Flags.get()
        .isDoNotFragment()).isTrue();
  }

  @Test
  void shouldReturnDoNotFragmentAsFalseWhenIpv4PackageDoesNotHaveDoNotFragmentBitActivated() {
    final var content = HexStreamFileToByteArrayUtil.readFile(FilePath.IpV4PackageHexStream.DoNotFragmentFlag.DISABLED);

    final var optionalIpV4Flags = doNotFragmentFlagCheckerLink.check(content);

    assertThat(optionalIpV4Flags).isPresent();
    assertThat(optionalIpV4Flags.get()
        .isDoNotFragment()).isFalse();
  }

}