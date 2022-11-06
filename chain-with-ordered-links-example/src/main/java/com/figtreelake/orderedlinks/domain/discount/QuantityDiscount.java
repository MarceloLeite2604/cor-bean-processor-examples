package com.figtreelake.orderedlinks.domain.discount;

import com.figtreelake.orderedlinks.domain.item.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public enum QuantityDiscount implements Discount {

  REFRESHING_DOZEN(
      Item.JUICE_BOX,
      12,
      BigDecimal.valueOf(0.1)),
  THREE_PLUS_ONE_SANDWICHES(
      Item.SANDWICH,
      4,
      BigDecimal.valueOf(0.25)),
  DELICIOUS_TRIO(
      Item.CHOCOLATE_BAR,
      3,
      BigDecimal.valueOf(0.05));

  private final Item item;

  private final int quantity;

  private final BigDecimal value;
}
