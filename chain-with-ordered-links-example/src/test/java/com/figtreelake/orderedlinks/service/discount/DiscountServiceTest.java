package com.figtreelake.orderedlinks.service.discount;

import com.figtreelake.orderedlinks.service.discount.link.DiscountCheckerLink;
import com.figtreelake.orderedlinks.test.fixture.OrderFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DiscountServiceTest {

  @InjectMocks
  private DiscountService discountService;

  @Mock
  private DiscountCheckerLink firstDiscountCheckerLink;

  @Test
  void shouldInvokeFirstDiscountCheckerLinkCheckMethod() {
    final var input = OrderFixture.createForBundledDiscounts();
    discountService.calculateDiscounts(input);

    verify(firstDiscountCheckerLink, times(1)).check(input);
  }
}