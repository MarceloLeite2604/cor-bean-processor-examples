package com.figtreelake.orderedlinks.service.discount.link;

import com.figtreelake.corbeanprocessor.autoconfigure.link.ChainLink;
import com.figtreelake.orderedlinks.domain.Order;

public interface DiscountCheckerLink extends ChainLink<DiscountCheckerLink> {

  Order check(Order order);
}
