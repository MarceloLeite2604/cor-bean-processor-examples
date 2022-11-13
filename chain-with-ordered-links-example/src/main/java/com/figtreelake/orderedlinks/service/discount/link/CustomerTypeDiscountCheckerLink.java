package com.figtreelake.orderedlinks.service.discount.link;

import com.figtreelake.orderedlinks.domain.Order;
import com.figtreelake.orderedlinks.domain.discount.CustomerTypeDiscount;
import com.figtreelake.orderedlinks.domain.discount.DiscountAmountValue;
import com.figtreelake.orderedlinks.domain.item.Item;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@org.springframework.core.annotation.Order(30)
@Component
public class CustomerTypeDiscountCheckerLink extends AbstractDiscountCheckerLink {

  @Override
  protected Order doCheck(Order order) {
    final var discountAmountValues = Optional.ofNullable(order.getDiscountAmountValues())
        .map(ArrayList::new)
        .orElseGet(ArrayList::new);

    for (final CustomerTypeDiscount customerTypeDiscount : CustomerTypeDiscount.values()) {
      if (customerTypeDiscount.getCustomerType()
          .equals(order.getCustomerType())) {
        final var value = calculateDiscountValue(customerTypeDiscount, order.getItemAmounts());
        final var discountAmountValue = DiscountAmountValue.builder()
            .discount(customerTypeDiscount)
            .quantity(1)
            .value(value)
            .build();

        discountAmountValues.add(discountAmountValue);
      }
    }

    return order.toBuilder()
        .discountAmountValues(discountAmountValues)
        .build();
  }

  private BigDecimal calculateDiscountValue(CustomerTypeDiscount customerTypeDiscount, Map<Item, Integer> itemAmounts) {
    var total = BigDecimal.ZERO;
    for (final Map.Entry<Item, Integer> itemAmountEntry : itemAmounts.entrySet()) {
      final var item = itemAmountEntry.getKey();
      final var amount = BigDecimal.valueOf(itemAmountEntry.getValue());

      final var itemTotal = item.getUnitPrice()
          .multiply(amount);

      total = total.add(itemTotal);
    }
    return total.multiply(customerTypeDiscount.getValue())
        .setScale(2, RoundingMode.HALF_EVEN);
  }

  @Override
  protected boolean isCandidate(Order order) {
    return true;
  }
}
