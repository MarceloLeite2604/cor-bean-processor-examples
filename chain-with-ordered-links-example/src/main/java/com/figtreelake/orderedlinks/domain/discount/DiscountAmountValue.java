package com.figtreelake.orderedlinks.domain.discount;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DiscountAmountValue {

  private final Discount discount;

  private final int quantity;

  private BigDecimal value;
}
