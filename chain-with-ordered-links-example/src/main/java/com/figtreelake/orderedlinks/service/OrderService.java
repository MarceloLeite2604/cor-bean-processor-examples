package com.figtreelake.orderedlinks.service;

import com.figtreelake.orderedlinks.domain.Order;
import com.figtreelake.orderedlinks.service.discount.DiscountService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderService {

  private final DiscountService discountService;

  public Order closeOrder(Order order) {
    final var total = calculateTotal(order);
    var updatedOrder = discountService.calculateDiscounts(order);


    final var discounts = updatedOrder.getDiscountAmountValues()
        .stream()
        .map(discountAmountValue -> {
          final var quantity = BigDecimal.valueOf(discountAmountValue.getQuantity());
          return quantity.multiply(discountAmountValue.getValue());
        })
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    final var totalWithDiscounts = total.subtract(discounts);

    return updatedOrder.toBuilder()
        .total(total)
        .totalWithDiscounts(totalWithDiscounts)
        .build();

  }

  private BigDecimal calculateTotal(Order order) {
    return order.getItemAmounts()
        .entrySet()
        .stream()
        .reduce(BigDecimal.ZERO, (total, itemAmountEntry) -> {

          final var unitPrice = itemAmountEntry.getKey()
              .getUnitPrice();

          final var amount = Optional.ofNullable(itemAmountEntry.getValue())
              .map(BigDecimal::valueOf)
              .orElse(BigDecimal.ZERO);

          final var itemTotal = unitPrice.multiply(amount);

          return total.add(itemTotal);
        }, BigDecimal::add);
  }
}
