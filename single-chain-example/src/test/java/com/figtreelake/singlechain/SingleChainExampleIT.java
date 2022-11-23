package com.figtreelake.singlechain;

import com.figtreelake.singlechain.link.DoubleTypeFinderLink;
import com.figtreelake.singlechain.link.IntegerTypeFinderLink;
import com.figtreelake.singlechain.link.NullTypeFinderLink;
import com.figtreelake.singlechain.link.StringTypeFinderLink;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("integration-test")
@SpringBootTest
class SingleChainExampleIT {

  @Autowired
  private TypeFinder typeFinder;

  @Test
  void shouldReturnStringTypeWhenObjectIsString() {
    final var input = "This is a String input.";
    final var expected = StringTypeFinderLink.TYPE_MESSAGE;

    final var actual = typeFinder.findType(input);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldReturnDoubleTypeWhenObjectIsDouble() {
    final var input = 2d;
    final var expected = DoubleTypeFinderLink.TYPE_MESSAGE;

    final var actual = typeFinder.findType(input);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldReturnIntegerTypeWhenObjectIsInteger() {
    final var input = 7;
    final var expected = IntegerTypeFinderLink.TYPE_MESSAGE;

    final var actual = typeFinder.findType(input);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldReturnNullTypeWhenObjectIsNull() {
    final Object input = null;
    final var expected = NullTypeFinderLink.TYPE_MESSAGE;

    final var actual = typeFinder.findType(input);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldThrowIllegalStateExceptionWhenObjectTypeIsNotRecognized() {
    final var input = new ByteArrayOutputStream();

    assertThrows(IllegalStateException.class, () -> typeFinder.findType(input));
  }


  @SpringBootConfiguration
  @ComponentScan("com.figtreelake.singlechain")
  public static class ITConfig {
  }
}
