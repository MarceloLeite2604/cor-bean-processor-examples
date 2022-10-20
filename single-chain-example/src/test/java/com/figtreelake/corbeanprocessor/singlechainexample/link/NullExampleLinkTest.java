package com.figtreelake.corbeanprocessor.singlechainexample.link;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NullExampleLinkTest {

  private NullExampleLink nullExampleLink;

  @BeforeEach
  void setUp() {
    nullExampleLink = new NullExampleLink();
  }

  @Test
  void shouldReturnTrueWhenObjectIsNull() {
    final var actual = nullExampleLink.applies(null);

    assertThat(actual).isTrue();
  }

  @Test
  void shouldReturnFalseWhenObjectIsNotADouble() {
    final var object = "This is not the type defined.";
    final var actual = nullExampleLink.applies(object);

    assertThat(actual).isFalse();
  }

  @Test
  void shouldReturnTypeMessage() {
    final var object = "This is the type defined.";
    final var result = nullExampleLink.doFindType(object);

    assertThat(result).isNotBlank();
  }

}