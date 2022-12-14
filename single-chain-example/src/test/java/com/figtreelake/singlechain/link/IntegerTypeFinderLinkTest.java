package com.figtreelake.singlechain.link;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IntegerTypeFinderLinkTest {

  private IntegerTypeFinderLink integerExampleLink;

  @BeforeEach
  void setUp() {
    integerExampleLink = new IntegerTypeFinderLink();
  }

  @Test
  void shouldReturnTrueWhenObjectIsAnInteger() {
    final var object = 13;
    final var actual = integerExampleLink.applies(object);

    assertThat(actual).isTrue();
  }

  @Test
  void shouldReturnFalseWhenObjectIsNotAnInteger() {
    final var object = "This is not the type defined.";
    final var actual = integerExampleLink.applies(object);

    assertThat(actual).isFalse();
  }

  @Test
  void shouldReturnTypeMessage() {
    final var object = "This is the type defined.";
    final var result = integerExampleLink.doFindType(object);

    assertThat(result).isNotBlank();
  }
}