package com.figtreelake.orderedlinks.service.discount;

import com.figtreelake.orderedlinks.domain.Order;
import com.figtreelake.orderedlinks.service.discount.link.DiscountCheckerLink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountService {

  private final DiscountCheckerLink firstLink;

  public Order calculateDiscounts(Order order) {
    return firstLink.check(order);
  }
}
