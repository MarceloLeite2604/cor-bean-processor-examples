package com.figtreelake.primarylink.service.ip.link;

import com.figtreelake.primarylink.IpV4Flags;
import org.springframework.util.Assert;

import java.util.Optional;

public abstract class AbstractIpV4FlagCheckerLink implements IpV4FlagCheckerLink {

  private AbstractIpV4FlagCheckerLink next;

  @Override
  public void setNext(IpV4FlagCheckerLink ipV4FlagCheckerLink) {
    Assert.notNull(ipV4FlagCheckerLink, "Argument cannot be null.");

    if (!(ipV4FlagCheckerLink instanceof AbstractIpV4FlagCheckerLink)) {
      final var message = String.format("Argument \"%s\" is not of type \"%s\".", ipV4FlagCheckerLink.getClass(), AbstractIpV4FlagCheckerLink.class);
      throw new IllegalArgumentException(message);
    }

    next = (AbstractIpV4FlagCheckerLink) ipV4FlagCheckerLink;
  }

  public Optional<IpV4Flags> check(byte[] data) {

    final var ipV4Flags = IpV4Flags.builder()
        .build();

    final var context = Context.builder()
        .data(data)
        .ipV4Flags(ipV4Flags)
        .build();

    final var optionalContext = check(context);

    if (optionalContext.isEmpty()) {
      return Optional.empty();
    }

    return optionalContext.map(Context::getIpV4Flags);
  }

  protected Optional<Context> check(Context context) {
    final var optionalContext = doCheck(context);

    if (optionalContext.isEmpty() || next == null) {
      return optionalContext;
    }

    return next.check(optionalContext.get());
  }

  protected abstract Optional<Context> doCheck(Context context);
}
