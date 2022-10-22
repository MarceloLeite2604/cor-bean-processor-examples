package com.figtreelake.singlechain;

import com.figtreelake.singlechain.link.TypeFinderLink;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class TypeFinderTest {

  @InjectMocks
  private TypeFinder typeFinder;

  @Mock
  private TypeFinderLink typeFinderLink;

  @Test
  void shouldReturnTypeFromObject() {
    final var input = new Object();
    final var expected = "This is the expected output.";
    when(typeFinderLink.findType(input)).thenReturn(expected);

    final var actual = typeFinder.findType(input);

    assertThat(actual).isEqualTo(expected);
  }
}