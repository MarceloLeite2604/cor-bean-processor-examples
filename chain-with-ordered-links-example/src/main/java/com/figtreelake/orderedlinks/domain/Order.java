package com.figtreelake.orderedlinks.domain;

import com.figtreelake.orderedlinks.domain.discount.DiscountAmountValue;
import com.figtreelake.orderedlinks.domain.item.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Order {

  private CustomerType customerType;

  private Map<Item, Integer> itemAmounts;

  private BigDecimal total;

  private List<DiscountAmountValue> discountAmountValues;

  private BigDecimal totalWithDiscounts;
}
