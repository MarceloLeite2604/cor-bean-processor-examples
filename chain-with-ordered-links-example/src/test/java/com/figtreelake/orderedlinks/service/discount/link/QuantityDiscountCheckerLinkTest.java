package com.figtreelake.orderedlinks.service.discount.link;

import com.figtreelake.orderedlinks.test.fixture.OrderFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class QuantityDiscountCheckerLinkTest {

  private QuantityDiscountCheckerLink quantityDiscountCheckerLink;

  @BeforeEach
  void setUp() {
    quantityDiscountCheckerLink = new QuantityDiscountCheckerLink();
  }

  @Test
  void shouldReturnQuantityDiscountsOnOrder() {
    final var input = OrderFixture.create();
    final var expected = OrderFixture.createWithQuantityDiscounts();

    final var actual = quantityDiscountCheckerLink.check(input);

    assertThat(actual).usingRecursiveComparison()
        .ignoringCollectionOrder()
        .isEqualTo(expected);
  }

  @Test
  void shouldReturnOrderWithoutDiscountsWhenNoQuantityDiscountMatches() {
    final var input = OrderFixture.createWithNoMatchingDiscountItems();

    final var expected = input.toBuilder()
        .discountAmountValues(Collections.emptyList())
        .build();

    final var actual = quantityDiscountCheckerLink.check(input);

    assertThat(actual).usingRecursiveComparison()
        .isEqualTo(expected);
  }
}