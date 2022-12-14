package com.figtreelake.singlechain.link;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoubleTypeFinderLinkTest {

  private DoubleTypeFinderLink doubleExampleLink;

  @BeforeEach
  void setUp() {
    doubleExampleLink = new DoubleTypeFinderLink();
  }

  @Test
  void shouldReturnTrueWhenObjectIsADouble() {
    final var object = 13.33;
    final var actual = doubleExampleLink.applies(object);

    assertThat(actual).isTrue();
  }

  @Test
  void shouldReturnFalseWhenObjectIsNotADouble() {
    final var object = "This is not the type defined.";
    final var actual = doubleExampleLink.applies(object);

    assertThat(actual).isFalse();
  }

  @Test
  void shouldReturnTypeMessage() {
    final var object = "This is the type defined.";
    final var result = doubleExampleLink.doFindType(object);

    assertThat(result).isNotBlank();
  }
}