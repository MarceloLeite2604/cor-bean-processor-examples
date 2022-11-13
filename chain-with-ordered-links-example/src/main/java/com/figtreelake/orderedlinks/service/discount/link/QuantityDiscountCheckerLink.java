package com.figtreelake.orderedlinks.service.discount.link;

import com.figtreelake.orderedlinks.domain.Order;
import com.figtreelake.orderedlinks.domain.discount.DiscountAmountValue;
import com.figtreelake.orderedlinks.domain.discount.QuantityDiscount;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Optional;

@org.springframework.core.annotation.Order(20)
@Component
public class QuantityDiscountCheckerLink extends AbstractDiscountCheckerLink {

  @Override
  protected Order doCheck(Order order) {
    final var itemAmounts = order.getItemAmounts();

    final var discountAmountValues = Optional.ofNullable(order.getDiscountAmountValues())
        .map(ArrayList::new)
        .orElseGet(ArrayList::new);

    for (final QuantityDiscount quantityDiscount : QuantityDiscount.values()) {
      final var item = quantityDiscount.getItem();

      final var quantity = itemAmounts.getOrDefault(item, 0) / quantityDiscount.getQuantity();

      final var value = calculateDiscountValue(quantityDiscount);

      if (quantity > 0) {
        final var discountAmountValue = DiscountAmountValue.builder()
            .discount(quantityDiscount)
            .quantity(quantity)
            .value(value)
            .build();

        discountAmountValues.add(discountAmountValue);
      }
    }

    return order.toBuilder()
        .discountAmountValues(discountAmountValues)
        .build();
  }

  private BigDecimal calculateDiscountValue(QuantityDiscount quantityDiscount) {
    final var quantity = BigDecimal.valueOf(quantityDiscount.getQuantity());
    return quantityDiscount.getItem()
        .getUnitPrice()
        .multiply(quantityDiscount.getValue())
        .multiply(quantity)
        .setScale(2, RoundingMode.HALF_EVEN);
  }

  @Override
  protected boolean isCandidate(Order order) {
    return CollectionUtils.isEmpty(order.getDiscountAmountValues());
  }
}
