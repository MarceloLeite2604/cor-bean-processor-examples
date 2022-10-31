package com.figtreelake.primarylink.service.ip;

import com.figtreelake.primarylink.service.ip.link.IpV4FlagCheckerLink;
import com.figtreelake.primarylink.test.fixture.IpV4FlagsFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IpServiceTest {

  @InjectMocks
  private IpService ipService;

  @Mock
  private IpV4FlagCheckerLink ipV4FlagCheckerLink;

  @Test
  void shouldInvokeFirstIpV4FlagCheckerLinkFiledCheckMethod() {
    final var input = new byte[]{0x00};
    final var expected = IpV4FlagsFixture.create();

    when(ipV4FlagCheckerLink.check(input)).thenReturn(Optional.of(expected));

    final var actual = ipService.retrieveIpV4Flags(input);

    assertThat(actual).contains(expected);
  }

}