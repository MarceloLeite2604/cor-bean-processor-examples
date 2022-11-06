package com.figtreelake.orderedlinks.domain.discount;

import com.figtreelake.orderedlinks.domain.CustomerType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public enum CustomerTypeDiscount implements Discount {

  WELCOME_BACK(CustomerType.FREQUENT, BigDecimal.valueOf(0.03));

  private final CustomerType customerType;

  private final BigDecimal value;
}
