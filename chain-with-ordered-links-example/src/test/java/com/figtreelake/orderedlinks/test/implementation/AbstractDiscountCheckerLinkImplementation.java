package com.figtreelake.orderedlinks.test.implementation;

import com.figtreelake.orderedlinks.domain.Order;
import com.figtreelake.orderedlinks.service.discount.link.AbstractDiscountCheckerLink;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AbstractDiscountCheckerLinkImplementation extends AbstractDiscountCheckerLink {

  private final boolean isCandidateResponse;

  private final Order doCheckResponse;

  @Override
  protected Order doCheck(Order order) {
    return doCheckResponse;
  }

  @Override
  protected boolean isCandidate(Order order) {
    return isCandidateResponse;
  }
}
