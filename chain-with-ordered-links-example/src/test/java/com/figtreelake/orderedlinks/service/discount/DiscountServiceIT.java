package com.figtreelake.orderedlinks.service.discount;

import com.figtreelake.orderedlinks.test.fixture.OrderFixture;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration-test")
@SpringBootTest
class DiscountServiceIT {

  @Autowired
  private DiscountService discountService;

  @Test
  void shouldReturnBundleDiscountsOnOrder() {
    final var inputOrder = OrderFixture.createForBundledDiscounts();
    final var expected = OrderFixture.createWithBundleDiscounts();

    final var actual = discountService.calculateDiscounts(inputOrder);

    assertThat(actual).usingRecursiveComparison()
        .ignoringCollectionOrder()
        .isEqualTo(expected);
  }

  @Test
  void shouldReturnQuantityDiscountsOnOrder() {
    final var inputOrder = OrderFixture.createForQuantityDiscounts();
    final var expected = OrderFixture.createWithQuantityDiscounts();

    final var actual = discountService.calculateDiscounts(inputOrder);

    assertThat(actual).usingRecursiveComparison()
        .ignoringCollectionOrder()
        .isEqualTo(expected);
  }

  @Test
  void shouldReturnCustomerTypeDiscountsOnOrder() {
    final var inputOrder = OrderFixture.createForCustomerTypeDiscounts();
    final var expected = OrderFixture.createWithCustomerTypeDiscounts();

    final var actual = discountService.calculateDiscounts(inputOrder);

    assertThat(actual).usingRecursiveComparison()
        .ignoringCollectionOrder()
        .isEqualTo(expected);
  }

  @SpringBootConfiguration
  @ComponentScan("com.figtreelake.orderedlinks")
  public static class ITConfiguration {
  }
}
