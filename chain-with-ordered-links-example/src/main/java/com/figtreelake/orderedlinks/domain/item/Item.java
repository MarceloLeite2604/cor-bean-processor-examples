package com.figtreelake.orderedlinks.domain.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public enum Item {
  JUICE_BOX(BigDecimal.valueOf(0.49)),
  SANDWICH(BigDecimal.valueOf(2.39)),
  CHOCOLATE_BAR(BigDecimal.valueOf(1.19));

  private final BigDecimal unitPrice;
}
