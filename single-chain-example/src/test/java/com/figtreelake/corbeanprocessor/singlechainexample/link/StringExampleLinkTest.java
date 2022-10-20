package com.figtreelake.corbeanprocessor.singlechainexample.link;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringExampleLinkTest {

  private StringExampleLink stringExampleLink;

  @BeforeEach
  void setUp() {
    stringExampleLink = new StringExampleLink();
  }

  @Test
  void shouldReturnTrueWhenObjectIsAString() {
    final var object = "This is a string";
    final var actual = stringExampleLink.applies(object);

    assertThat(actual).isTrue();
  }

  @Test
  void shouldReturnFalseWhenObjectIsNotAString() {
    final var object = 1.21;
    final var actual = stringExampleLink.applies(object);

    assertThat(actual).isFalse();
  }

  @Test
  void shouldReturnTypeMessage() {
    final var object = "This is the type defined.";
    final var result = stringExampleLink.doFindType(object);

    assertThat(result).isNotBlank();
  }
}