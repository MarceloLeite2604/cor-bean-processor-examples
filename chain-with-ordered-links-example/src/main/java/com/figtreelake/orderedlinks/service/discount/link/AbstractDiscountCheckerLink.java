package com.figtreelake.orderedlinks.service.discount.link;

import com.figtreelake.orderedlinks.domain.Order;
import lombok.Setter;

public abstract class AbstractDiscountCheckerLink implements DiscountCheckerLink {

  @Setter
  private DiscountCheckerLink next;

  @Override
  public Order check(Order order) {
    var updatedOrder = order;
    if (isCandidate(order)) {
      updatedOrder = doCheck(order);
    }

    if (next == null) {
      return updatedOrder;
    }

    return next.check(updatedOrder);
  }

  protected abstract Order doCheck(Order order);

  protected abstract boolean isCandidate(Order order);
}

