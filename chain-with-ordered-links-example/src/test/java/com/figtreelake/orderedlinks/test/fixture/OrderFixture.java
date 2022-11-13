package com.figtreelake.orderedlinks.test.fixture;

import com.figtreelake.orderedlinks.domain.CustomerType;
import com.figtreelake.orderedlinks.domain.Order;
import com.figtreelake.orderedlinks.domain.discount.DiscountAmountValue;
import com.figtreelake.orderedlinks.domain.item.Item;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@UtilityClass
public class OrderFixture {

  public static final Map<Item, Integer> ITEM_AMOUNTS_FOR_BUNDLE_DISCOUNTS = Map.ofEntries(
      Map.entry(Item.JUICE_BOX, 24),
      Map.entry(Item.SANDWICH, 4),
      Map.entry(Item.CHOCOLATE_BAR, 9)
  );

  public static final Map<Item, Integer> ITEM_AMOUNTS_FOR_QUANTITY_DISCOUNTS = Map.ofEntries(
      Map.entry(Item.SANDWICH, 13),
      Map.entry(Item.CHOCOLATE_BAR, 10)
  );

  public static final Map<Item, Integer> ITEM_AMOUNTS_FOR_NO_DISCOUNTS = Map.ofEntries(
      Map.entry(Item.SANDWICH, 1),
      Map.entry(Item.JUICE_BOX, 1)
  );

  public static final CustomerType CUSTOMER_TYPE = CustomerType.USUAL;

  private static Order createForBundledDiscounts(
      CustomerType customerType,
      Map<Item, Integer> itemAmounts,
      List<DiscountAmountValue> discountAmountValues,
      BigDecimal total,
      BigDecimal totalWithDiscounts) {
    return Order.builder()
        .customerType(customerType)
        .itemAmounts(itemAmounts)
        .discountAmountValues(discountAmountValues)
        .total(total)
        .totalWithDiscounts(totalWithDiscounts)
        .build();
  }

  public static Order createForBundledDiscounts() {
    return createForBundledDiscounts(
        CUSTOMER_TYPE,
        ITEM_AMOUNTS_FOR_BUNDLE_DISCOUNTS,
        Collections.emptyList(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }

  public static Order createForQuantityDiscounts() {
    return createForBundledDiscounts(
        CUSTOMER_TYPE,
        ITEM_AMOUNTS_FOR_QUANTITY_DISCOUNTS,
        Collections.emptyList(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }

  public static Order createWithBundleDiscounts() {
    return createForBundledDiscounts(
        CUSTOMER_TYPE,
        ITEM_AMOUNTS_FOR_BUNDLE_DISCOUNTS,
        DiscountAmountValueFixture.createBundleDiscounts(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }

  public static Order createWithQuantityDiscounts() {
    return createForBundledDiscounts(
        CUSTOMER_TYPE,
        ITEM_AMOUNTS_FOR_QUANTITY_DISCOUNTS,
        DiscountAmountValueFixture.createQuantityDiscounts(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }

  public static Order createWithBundleDiscountsAndTotal() {
    return createForBundledDiscounts(
        CUSTOMER_TYPE,
        ITEM_AMOUNTS_FOR_BUNDLE_DISCOUNTS,
        DiscountAmountValueFixture.createBundleDiscounts(),
        BigDecimal.valueOf(32.03),
        BigDecimal.valueOf(28.85));
  }

  public static Order createWithNoMatchingDiscountItems() {
    return createForBundledDiscounts(
        CUSTOMER_TYPE,
        Map.of(Item.CHOCOLATE_BAR, 1),
        Collections.emptyList(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }

  public static Order createForBundledDiscounts(CustomerType customerType) {
    return createForBundledDiscounts(
        customerType,
        ITEM_AMOUNTS_FOR_BUNDLE_DISCOUNTS,
        Collections.emptyList(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }

  public static Order createForCustomerTypeDiscounts() {
    return createForBundledDiscounts(
        CustomerType.FREQUENT,
        ITEM_AMOUNTS_FOR_NO_DISCOUNTS,
        Collections.emptyList(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }

  public static Order createWithCustomerTypeDiscounts() {
    return createForBundledDiscounts(
        CustomerType.FREQUENT,
        ITEM_AMOUNTS_FOR_NO_DISCOUNTS,
        DiscountAmountValueFixture.createCustomerTypeDiscounts(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }
}
