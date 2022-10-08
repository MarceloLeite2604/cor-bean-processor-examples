package com.figtreelake.multiplechains;

import com.figtreelake.multiplechains.service.configuration.ConfigurationService;
import com.figtreelake.multiplechains.service.contentverifier.ContentVerifierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MultipleChainsExampleIT {

  @Autowired
  private ConfigurationService configurationService;

  @Autowired
  private ContentVerifierService contentVerifierService;

  @Test
  void should() {
    assertThat(configurationService).isNotNull();
  }

//  @SpringBootConfiguration
//  @ComponentScan("com.figtreelake.multiplechains")
//  @Import(ChainConfiguration.class)
//  public static class ITConfig {
//  }
}
