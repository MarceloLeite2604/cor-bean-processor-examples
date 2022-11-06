package com.figtreelake.orderedlinks.service.discount.link;

import com.figtreelake.orderedlinks.domain.CustomerType;
import com.figtreelake.orderedlinks.test.fixture.OrderFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTypeDiscountCheckerLinkTest {

  private CustomerTypeDiscountCheckerLink customerTypeDiscountCheckerLink;

  @BeforeEach
  void setUp() {
    customerTypeDiscountCheckerLink = new CustomerTypeDiscountCheckerLink();
  }

  @Test
  void shouldReturnCustomerDiscountOnOrder() {
    final var input = OrderFixture.create(CustomerType.FREQUENT);
    final var expected = OrderFixture.createWithCustomerTypeDiscounts();

    final var actual = customerTypeDiscountCheckerLink.check(input);

    assertThat(actual).usingRecursiveComparison()
        .ignoringCollectionOrder()
        .isEqualTo(expected);
  }

  @Test
  void shouldReturnOrderWithoutDiscountsWhenNoQuantityDiscountMatches() {
    final var input = OrderFixture.create(CustomerType.USUAL);

    final var expected = input.toBuilder()
        .discountAmountValues(Collections.emptyList())
        .build();

    final var actual = customerTypeDiscountCheckerLink.check(input);

    assertThat(actual).usingRecursiveComparison()
        .ignoringCollectionOrder()
        .isEqualTo(expected);
  }

}