package com.figtreelake.primarylink.service.ip.link;

import com.figtreelake.primarylink.test.util.FilePath;
import com.figtreelake.primarylink.test.util.HexStreamFileToByteArrayUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EvilBitCheckerLinkTest {

  private EvilBitCheckerLink evilBitCheckerLink;

  @BeforeEach
  void setUp() {
    evilBitCheckerLink = new EvilBitCheckerLink();
  }

  @Test
  void shouldReturnEvilBitAsTrueWhenIpv4PackageHasEvilBitActivated() {
    final var content = HexStreamFileToByteArrayUtil.readFile(FilePath.IpV4PackageHexStream.EvilBitFlag.ENABLED);

    final var optionalIpV4Flags = evilBitCheckerLink.check(content);

    assertThat(optionalIpV4Flags).isPresent();
    assertThat(optionalIpV4Flags.get()
        .isEvilBit()).isTrue();
  }

  @Test
  void shouldReturnEvilBitAsFalseWhenIpv4PackageDoesNotHaveEvilBitActivated() {
    final var content = HexStreamFileToByteArrayUtil.readFile(FilePath.IpV4PackageHexStream.EvilBitFlag.DISABLED);

    final var optionalIpV4Flags = evilBitCheckerLink.check(content);

    assertThat(optionalIpV4Flags).isPresent();
    assertThat(optionalIpV4Flags.get()
        .isMoreFragments()).isFalse();
  }

}