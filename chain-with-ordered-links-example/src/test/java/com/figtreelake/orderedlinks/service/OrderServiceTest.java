package com.figtreelake.orderedlinks.service;

import com.figtreelake.orderedlinks.service.discount.DiscountService;
import com.figtreelake.orderedlinks.test.fixture.OrderFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @InjectMocks
  private OrderService orderService;

  @Mock
  private DiscountService discountService;

  @Test
  void shouldUpdateOrderWithTotalAndDiscountsAndTotalWithDiscounts() {
    final var input = OrderFixture.create();
    final var expected = OrderFixture.createWithBundleDiscountsAndTotal();

    when(discountService.calculateDiscounts(input)).thenReturn(OrderFixture.createWithBundleDiscounts());

    final var actual = orderService.closeOrder(input);

    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

}