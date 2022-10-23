package com.figtreelake.multiplechains.test;

import com.figtreelake.multiplechains.test.annotation.IntegrationTest;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

@IntegrationTest
public abstract class AbstractIT {

  @SpringBootConfiguration
  @ComponentScan("com.figtreelake.multiplechains")
  public static class ITConfiguration {
  }
}
