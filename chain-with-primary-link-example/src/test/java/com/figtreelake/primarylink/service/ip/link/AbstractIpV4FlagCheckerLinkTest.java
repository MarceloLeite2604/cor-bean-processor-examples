package com.figtreelake.primarylink.service.ip.link;

import com.figtreelake.primarylink.IpV4Flags;
import com.figtreelake.primarylink.test.fixture.IpV4FlagsFixture;
import com.figtreelake.primarylink.test.implementation.AbstractIpV4FlagCheckerLinkImplementation;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AbstractIpV4FlagCheckerLinkTest {

  @Test
  void shouldThrowIllegalArgumentExceptionWhenSetNextArgumentIsNotInstanceOfAbstractIpV4FlagCheckerLink() {
    final AbstractIpV4FlagCheckerLink abstractIpV4FlagCheckerLink = new AbstractIpV4FlagCheckerLinkImplementation(Optional.empty());

    final var mockedIpV4FlagCheckerLink = mock(IpV4FlagCheckerLink.class);

    assertThrows(IllegalArgumentException.class, () -> abstractIpV4FlagCheckerLink.setNext(mockedIpV4FlagCheckerLink));
  }

  @Test
  void shouldNotThrowExceptionWhenSetNextArgumentIsInstanceOfAbstractIpV4FlagCheckerLink() {
    final AbstractIpV4FlagCheckerLink abstractIpV4FlagCheckerLink = new AbstractIpV4FlagCheckerLinkImplementation(Optional.empty());

    final var mockedIpV4FlagCheckerLink = mock(AbstractIpV4FlagCheckerLink.class);

    assertDoesNotThrow(() -> abstractIpV4FlagCheckerLink.setNext(mockedIpV4FlagCheckerLink));
  }

  @Test
  void shouldReturnOptionalEmptyWhenDoCheckReturnOptionalEmpty() {
    final AbstractIpV4FlagCheckerLink abstractIpV4FlagCheckerLink = new AbstractIpV4FlagCheckerLinkImplementation(Optional.empty());

    final var input = new byte[]{0x00};

    final var actual = abstractIpV4FlagCheckerLink.check(input);

    assertThat(actual).isEmpty();
  }

  @Test
  void shouldReturnIpV4FlagsFromDoCheckResponseWhenNextFieldIsNull() {
    final var input = new byte[]{0x00};

    final var ipV4Flags = IpV4Flags.builder()
        .doNotFragment(true)
        .moreFragments(false)
        .evilBit(true)
        .build();

    final var context = Context.builder()
        .data(input)
        .ipV4Flags(ipV4Flags)
        .build();

    final AbstractIpV4FlagCheckerLink abstractIpV4FlagCheckerLink = new AbstractIpV4FlagCheckerLinkImplementation(Optional.of(context));

    final var actual = abstractIpV4FlagCheckerLink.check(input);

    assertThat(actual).contains(ipV4Flags);
  }

  @Test
  void shouldReturnIpV4FlagsFromNextObjectWhenDoCheckReturnsNonEmptyAndNextFieldIsNotNull() {
    final var input = new byte[]{0x00};

    final var doCheckIpV4FlagsResponse = IpV4FlagsFixture.create();

    final var doCheckContextResponse = Context.builder()
        .data(input)
        .ipV4Flags(doCheckIpV4FlagsResponse)
        .build();

    final var nextCheckIpV4FlagsResponse = IpV4FlagsFixture.create();

    final var nextCheckContextResponse = Context.builder()
        .data(input)
        .ipV4Flags(nextCheckIpV4FlagsResponse)
        .build();

    final var mockedAbstractIpV4FlagCheckerLink = mock(AbstractIpV4FlagCheckerLink.class);
    when(mockedAbstractIpV4FlagCheckerLink.check(any(Context.class))).thenReturn(Optional.of(nextCheckContextResponse));

    final AbstractIpV4FlagCheckerLink abstractIpV4FlagCheckerLink = new AbstractIpV4FlagCheckerLinkImplementation(Optional.of(doCheckContextResponse));
    abstractIpV4FlagCheckerLink.setNext(mockedAbstractIpV4FlagCheckerLink);

    final var actual = abstractIpV4FlagCheckerLink.check(input);

    assertThat(actual).contains(nextCheckIpV4FlagsResponse);
  }
}