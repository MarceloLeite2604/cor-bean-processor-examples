package com.figtreelake.multiplechains;

import com.figtreelake.multiplechains.service.configuration.ConfigurationService;
import com.figtreelake.multiplechains.service.contentverifier.ContentVerifierService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("integrationTest")
@SpringBootTest
class ContentVerifierServiceIT {

  @Autowired
  private ConfigurationService configurationService;

  @Autowired
  private ContentVerifierService contentVerifierService;

  @Test
  void should() {
    assertThat(configurationService).isNotNull();
    assertThat(contentVerifierService).isNotNull();
  }
}
