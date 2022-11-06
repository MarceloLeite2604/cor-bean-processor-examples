package com.figtreelake.orderedlinks.domain.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ItemAmount {

  private final Item item;

  private final int amount;
}
