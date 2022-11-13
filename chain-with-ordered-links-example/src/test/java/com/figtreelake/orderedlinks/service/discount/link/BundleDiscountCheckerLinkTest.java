package com.figtreelake.orderedlinks.service.discount.link;

import com.figtreelake.orderedlinks.test.fixture.OrderFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class BundleDiscountCheckerLinkTest {

  private BundleDiscountCheckerLink bundleDiscountCheckerLink;

  @BeforeEach
  void setUp() {
    bundleDiscountCheckerLink = new BundleDiscountCheckerLink();
  }

  @Test
  void shouldReturnBundleDiscountsOnOrder() {
    final var input = OrderFixture.createForBundledDiscounts();
    final var expected = OrderFixture.createWithBundleDiscounts();

    final var actual = bundleDiscountCheckerLink.check(input);

    assertThat(actual).usingRecursiveComparison()
        .isEqualTo(expected);
  }

  @Test
  void shouldReturnOrderWithoutDiscountsWhenNoBundleDiscountMatches() {
    final var input = OrderFixture.createWithNoMatchingDiscountItems();

    final var expected = input.toBuilder()
        .discountAmountValues(Collections.emptyList())
        .build();

    final var actual = bundleDiscountCheckerLink.check(input);

    assertThat(actual).usingRecursiveComparison()
        .ignoringCollectionOrder()
        .isEqualTo(expected);
  }

}