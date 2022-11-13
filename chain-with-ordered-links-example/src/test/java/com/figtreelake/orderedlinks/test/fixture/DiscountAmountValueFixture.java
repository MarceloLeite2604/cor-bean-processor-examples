package com.figtreelake.orderedlinks.test.fixture;

import com.figtreelake.orderedlinks.domain.discount.BundleDiscount;
import com.figtreelake.orderedlinks.domain.discount.CustomerTypeDiscount;
import com.figtreelake.orderedlinks.domain.discount.DiscountAmountValue;
import com.figtreelake.orderedlinks.domain.discount.QuantityDiscount;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;

@UtilityClass
public class DiscountAmountValueFixture {

  public static List<DiscountAmountValue> createBundleDiscounts() {
    return List.of(
        DiscountAmountValue.builder()
            .discount(BundleDiscount.QUICK_SNACK_PACK)
            .value(BigDecimal.valueOf(0.73))
            .quantity(4)
            .build(),
        DiscountAmountValue.builder()
            .discount(BundleDiscount.REFRESH_FLASH)
            .value(BigDecimal.valueOf(0.13))
            .quantity(2)
            .build());
  }

  public static List<DiscountAmountValue> createQuantityDiscounts() {
    return List.of(
        DiscountAmountValue.builder()
            .discount(QuantityDiscount.DELICIOUS_TRIO)
            .value(BigDecimal.valueOf(0.18))
            .quantity(3)
            .build(),
        DiscountAmountValue.builder()
            .discount(QuantityDiscount.THREE_PLUS_ONE_SANDWICHES)
            .value(BigDecimal.valueOf(2.39))
            .quantity(3)
            .build());
  }

  public static List<DiscountAmountValue> createCustomerTypeDiscounts() {
    return List.of(
        DiscountAmountValue.builder()
            .discount(CustomerTypeDiscount.WELCOME_BACK)
            .value(BigDecimal.valueOf(0.09))
            .quantity(1)
            .build());
  }
}
