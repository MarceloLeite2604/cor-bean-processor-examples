package com.figtreelake.orderedlinks.domain.discount;

import com.figtreelake.orderedlinks.domain.item.Item;
import com.figtreelake.orderedlinks.domain.item.ItemAmount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Getter
public enum BundleDiscount implements Discount {

  QUICK_SNACK_PACK(
      List.of(
          new ItemAmount(Item.SANDWICH, 1),
          new ItemAmount(Item.JUICE_BOX, 1),
          new ItemAmount(Item.CHOCOLATE_BAR, 1)
      ),
      BigDecimal.valueOf(0.18)),
  FAMILY_MEAL(
      List.of(
          new ItemAmount(Item.SANDWICH, 3),
          new ItemAmount(Item.JUICE_BOX, 3)
      ),
      BigDecimal.valueOf(0.22)),
  REFRESH_FLASH(
      List.of(
          new ItemAmount(Item.JUICE_BOX, 2),
          new ItemAmount(Item.CHOCOLATE_BAR, 2)
      ),
      BigDecimal.valueOf(0.08));

  private final List<ItemAmount> itemQuantities;

  private final BigDecimal value;

  public List<Item> getItems() {
    return itemQuantities.stream()
        .map(ItemAmount::getItem)
        .toList();
  }
}
