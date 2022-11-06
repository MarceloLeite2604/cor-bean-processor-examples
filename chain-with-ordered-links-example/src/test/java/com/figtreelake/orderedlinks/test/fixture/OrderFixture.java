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

  public static final Map<Item, Integer> ITEM_AMOUNTS = Map.ofEntries(
      Map.entry(Item.JUICE_BOX, 24),
      Map.entry(Item.SANDWICH, 4),
      Map.entry(Item.CHOCOLATE_BAR, 9)
  );

  public static final CustomerType CUSTOMER_TYPE = CustomerType.USUAL;

  private static Order create(
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

  public static Order create() {
    return create(
        CUSTOMER_TYPE,
        ITEM_AMOUNTS,
        Collections.emptyList(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }

  public static Order createWithBundleDiscounts() {
    return create(
        CUSTOMER_TYPE,
        ITEM_AMOUNTS,
        DiscountAmountValueFixture.createBundleDiscounts(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }

  public static Order createWithQuantityDiscounts() {
    return create(
        CUSTOMER_TYPE,
        ITEM_AMOUNTS,
        DiscountAmountValueFixture.createQuantityDiscounts(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }

  public static Order createWithBundleDiscountsAndTotal() {
    return create(
        CUSTOMER_TYPE,
        ITEM_AMOUNTS,
        DiscountAmountValueFixture.createBundleDiscounts(),
        BigDecimal.valueOf(32.03),
        BigDecimal.valueOf(28.85));
  }

  public static Order createWithNoMatchingDiscountItems() {
    return create(
        CUSTOMER_TYPE,
        Map.of(Item.CHOCOLATE_BAR, 1),
        Collections.emptyList(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }

  public static Order create(CustomerType customerType) {
    return create(
        customerType,
        ITEM_AMOUNTS,
        Collections.emptyList(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }

  public static Order createWithCustomerTypeDiscounts() {
    return create(
        CustomerType.FREQUENT,
        ITEM_AMOUNTS,
        DiscountAmountValueFixture.createCustomerTypeDiscounts(),
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }
}
