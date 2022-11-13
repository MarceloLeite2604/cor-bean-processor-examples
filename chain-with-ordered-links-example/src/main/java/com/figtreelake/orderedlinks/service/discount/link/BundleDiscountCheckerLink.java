package com.figtreelake.orderedlinks.service.discount.link;

import com.figtreelake.orderedlinks.domain.Order;
import com.figtreelake.orderedlinks.domain.discount.BundleDiscount;
import com.figtreelake.orderedlinks.domain.discount.DiscountAmountValue;
import com.figtreelake.orderedlinks.domain.item.Item;
import com.figtreelake.orderedlinks.domain.item.ItemAmount;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

@org.springframework.core.annotation.Order(10)
@Component
public class BundleDiscountCheckerLink extends AbstractDiscountCheckerLink {

  @Override
  protected Order doCheck(Order order) {
    Map<Item, Integer> itemAmounts = new EnumMap<>(order.getItemAmounts());

    final var discountAmountValues = Optional.ofNullable(order.getDiscountAmountValues())
        .map(ArrayList::new)
        .orElseGet(ArrayList::new);

    for (final BundleDiscount bundleDiscount : BundleDiscount.values()) {
      final var quantity = calculateDiscountQuantity(itemAmounts, bundleDiscount);
      final var value = calculateDiscountValue(bundleDiscount);

      if (quantity > 0) {

        final var discountAmountValue = DiscountAmountValue.builder()
            .discount(bundleDiscount)
            .quantity(quantity)
            .value(value)
            .build();

        discountAmountValues.add(discountAmountValue);

        itemAmounts = reduceItemAmounts(itemAmounts, bundleDiscount, quantity);
      }
    }
    return order.toBuilder()
        .discountAmountValues(discountAmountValues)
        .build();
  }

  private Map<Item, Integer> reduceItemAmounts(Map<Item, Integer> itemAmounts, BundleDiscount bundleDiscount, int quantity) {
    final var updatedItemAmounts = new EnumMap<>(itemAmounts);
    for (final ItemAmount itemAmount : bundleDiscount.getItemQuantities()) {
      final var item = itemAmount.getItem();
      final var originalAmount = itemAmounts.get(item);
      final var reducedItemAmount = itemAmount.getAmount() * quantity;
      final var newAmount = originalAmount - reducedItemAmount;
      updatedItemAmounts.put(item, newAmount);
    }
    return updatedItemAmounts;
  }

  private BigDecimal calculateDiscountValue(BundleDiscount bundleDiscount) {
    var total = BigDecimal.ZERO;
    for (final Item item : bundleDiscount.getItems()) {
      final var itemDiscount = item.getUnitPrice()
          .multiply(bundleDiscount.getValue());
      total = total.add(itemDiscount);
    }
    return total.setScale(2, RoundingMode.HALF_EVEN);
  }

  private int calculateDiscountQuantity(Map<Item, Integer> itemAmounts, BundleDiscount bundleDiscount) {
    Integer discountQuantity = null;
    for (final ItemAmount itemQuantity : bundleDiscount.getItemQuantities()) {
      final var item = itemQuantity.getItem();
      final var itemQuantityForDiscount = itemQuantity.getAmount();

      final var discountQuantityCandidate = itemAmounts.getOrDefault(item, 0) / itemQuantityForDiscount;

      if (discountQuantity == null) {
        discountQuantity = discountQuantityCandidate;
      } else {
        discountQuantity = Integer.min(discountQuantity, discountQuantityCandidate);
      }
    }

    return Optional.ofNullable(discountQuantity)
        .orElse(0);
  }

  @Override
  protected boolean isCandidate(Order order) {
    return CollectionUtils.isEmpty(order.getDiscountAmountValues());
  }
}
