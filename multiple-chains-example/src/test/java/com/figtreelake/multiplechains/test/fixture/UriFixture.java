package com.figtreelake.multiplechains.test.fixture;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import lombok.experimental.UtilityClass;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.logging.log4j.util.Strings;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@UtilityClass
public class UriFixture {

  public static final String HTTP_CONFIGURATION_PATH = "/configuration";

  public static URI createClasspathUri() throws URISyntaxException {
    return new URIBuilder().setScheme("classpath")
        .setPath("/classpath-input/configuration.txt")
        .build();
  }

  public static URI createFileUri() {
    return Paths.get(Strings.EMPTY)
        .toAbsolutePath()
        .resolve("file-input/configuration.txt")
        .toUri();
  }

  public static URI createHttpUri(WireMockRuntimeInfo wireMockRuntimeInfo) throws URISyntaxException {
    return new URIBuilder(wireMockRuntimeInfo.getHttpBaseUrl())
        .setPath(HTTP_CONFIGURATION_PATH)
        .build();
  }
}
