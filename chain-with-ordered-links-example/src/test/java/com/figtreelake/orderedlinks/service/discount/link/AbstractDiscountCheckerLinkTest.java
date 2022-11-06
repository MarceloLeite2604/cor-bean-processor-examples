package com.figtreelake.orderedlinks.service.discount.link;

import com.figtreelake.orderedlinks.test.fixture.OrderFixture;
import com.figtreelake.orderedlinks.test.implementation.AbstractDiscountCheckerLinkImplementation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AbstractDiscountCheckerLinkTest {

  @Test
  void shouldInvokeDoCheckWhenOrderArgumentIsCandidate() {
    final var input = OrderFixture.createWithBundleDiscounts();
    final var expected = OrderFixture.createWithBundleDiscounts();
    final var abstractDiscountCheckerLink = new AbstractDiscountCheckerLinkImplementation(true, expected);

    final var actual = abstractDiscountCheckerLink.check(input);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldInvokeCheckFromNextFieldWhenInputIsNotCandidateAndNextIsDefined() {
    final var input = OrderFixture.createWithBundleDiscounts();
    final var expected = OrderFixture.createWithBundleDiscounts();

    final var mockedDiscountCheckerLink = mock(DiscountCheckerLink.class);
    when(mockedDiscountCheckerLink.check(input)).thenReturn(expected);

    final var abstractDiscountCheckerLink = new AbstractDiscountCheckerLinkImplementation(false, null);
    abstractDiscountCheckerLink.setNext(mockedDiscountCheckerLink);

    final var actual = abstractDiscountCheckerLink.check(input);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldReturnInputOrderWhenInputIsNotCandidateAndNextIsNotDefined() {
    final var input = OrderFixture.createWithBundleDiscounts();

    final var abstractDiscountCheckerLink = new AbstractDiscountCheckerLinkImplementation(false, null);

    final var actual = abstractDiscountCheckerLink.check(input);

    assertThat(actual).isEqualTo(input);
  }

}