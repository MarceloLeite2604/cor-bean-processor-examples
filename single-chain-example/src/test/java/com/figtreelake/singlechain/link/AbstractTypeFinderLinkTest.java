package com.figtreelake.singlechain.link;

import com.figtreelake.singlechain.test.implementation.AbstractTypeFinderLinkImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AbstractTypeFinderLinkTest {

  @Test
  void shouldInvokeDoFindTypeWhenLinkAppliesToObject() {
    final var input = new Object();
    final var appliesResult = true;
    final var expected = "This is the expected output.";
    final var abstractExampleLink = new AbstractTypeFinderLinkImplementation(appliesResult, expected);

    final var actual = abstractExampleLink.findType(input);

    Assertions.assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldInvokeFindTypeFromNextLinkWhenActualLinksDoesNotApplyAndNextLinkIsSet() {
    final var input = new Object();
    final var appliesResult = false;
    final var expected = "This is the expected output.";
    final var abstractExampleLink = new AbstractTypeFinderLinkImplementation(appliesResult, null);

    final var mockedAbstractExampleLink = mock(AbstractTypeFinderLink.class);
    when(mockedAbstractExampleLink.findType(input)).thenReturn(expected);

    abstractExampleLink.setNext(mockedAbstractExampleLink);

    final var actual = abstractExampleLink.findType(input);

    Assertions.assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldThrowIllegalStateExceptionWhenActualLinksDoesNotApplyAndThereIsNoNextLink() {
    final var input = new Object();
    final var appliesResult = false;
    final var abstractExampleLink = new AbstractTypeFinderLinkImplementation(appliesResult, null);

    assertThrows(IllegalStateException.class, () -> abstractExampleLink.findType(input));
  }
}