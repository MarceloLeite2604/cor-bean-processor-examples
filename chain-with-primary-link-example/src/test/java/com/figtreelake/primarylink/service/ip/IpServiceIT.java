package com.figtreelake.primarylink.service.ip;

import com.figtreelake.primarylink.service.ip.link.IpV4VersionCheckLink;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration-test")
@SpringBootTest
class IpServiceIT {

  @Autowired
  private IpService ipService;

  @Test
  void shouldContainIpV4VersionCheckerAsFirstLink() {
    assertThat(ipService.getFirstIpV4FlagCheckerLink())
        .isInstanceOf(IpV4VersionCheckLink.class);
  }

  @SpringBootConfiguration
  @ComponentScan("com.figtreelake.primarylink")
  public static class ITConfiguration {
  }
}
