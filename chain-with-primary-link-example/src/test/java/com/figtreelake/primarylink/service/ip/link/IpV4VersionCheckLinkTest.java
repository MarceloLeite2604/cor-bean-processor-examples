package com.figtreelake.primarylink.service.ip.link;

import com.figtreelake.primarylink.test.util.FilePath;
import com.figtreelake.primarylink.test.util.HexStreamFileToByteArrayUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IpV4VersionCheckLinkTest {

  private IpV4VersionCheckLink ipV4VersionCheckLink;

  @BeforeEach
  void setUp() {
    ipV4VersionCheckLink = new IpV4VersionCheckLink();
  }

  @Test
  void shouldReturnOptionalEmptyWhenIpPackageIsNotVersion4() {
    final var content = HexStreamFileToByteArrayUtil.readFile(FilePath.IPV6_PACKAGE_HEX_STREAM);

    final var actual = ipV4VersionCheckLink.check(content);

    assertThat(actual).isEmpty();
  }

  @Test
  void shouldReturnOptionalEmptyWhenIpPackageDoesNotHaveTheMinimumRequiredSize() {
    final var content = HexStreamFileToByteArrayUtil.readFile(FilePath.INVALID_CONTENT);

    final var actual = ipV4VersionCheckLink.check(content);

    assertThat(actual).isEmpty();
  }

  @Test
  void shouldReturnOptionalWithIpV4FlagsWhenIpPackageIsOfVersion4() {
    final var content = HexStreamFileToByteArrayUtil.readFile(FilePath.IpV4PackageHexStream.DoNotFragmentFlag.ENABLED);

    final var actual = ipV4VersionCheckLink.check(content);

    assertThat(actual).isPresent();
  }
}