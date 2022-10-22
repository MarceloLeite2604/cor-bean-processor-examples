package com.figtreelake.singlechain.link;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NullTypeFinderLinkTest {

  private NullTypeFinderLink nullExampleLink;

  @BeforeEach
  void setUp() {
    nullExampleLink = new NullTypeFinderLink();
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