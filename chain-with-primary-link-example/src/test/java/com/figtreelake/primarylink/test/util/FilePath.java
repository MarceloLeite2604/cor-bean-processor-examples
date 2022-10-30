package com.figtreelake.primarylink.test.util;

import lombok.experimental.UtilityClass;

import java.nio.file.Path;


@UtilityClass
public class FilePath {
  private static final Path ROOT_DIRECTORY = Path.of(".")
      .resolve("input");

  public static final Path IPV6_PACKAGE_HEX_STREAM = ROOT_DIRECTORY.resolve(Path.of("ipv6", "ipv6-package.txt"));

  public static final Path INVALID_CONTENT = ROOT_DIRECTORY.resolve("invalid-content.txt");

  @UtilityClass
  public class IpV4PackageHexStream {
    private static final Path ROOT_DIRECTORY = FilePath.ROOT_DIRECTORY.resolve("ipv4");

    @UtilityClass
    public class EvilBitFlag {
      private static final Path ROOT_DIRECTORY = IpV4PackageHexStream.ROOT_DIRECTORY.resolve("evil-bit");

      public static final Path ENABLED = ROOT_DIRECTORY.resolve("ipv4-package-with-evil-bit-enabled.txt");
      public static final Path DISABLED = ROOT_DIRECTORY.resolve("ipv4-package-with-evil-bit-disabled.txt");
    }

    @UtilityClass
    public class DoNotFragmentFlag {
      private static final Path ROOT_DIRECTORY = IpV4PackageHexStream.ROOT_DIRECTORY.resolve("do-not-fragment-flag");

      public static final Path ENABLED = ROOT_DIRECTORY.resolve("ipv4-package-with-do-not-fragment-flag-enabled.txt");
      public static final Path DISABLED = ROOT_DIRECTORY.resolve("ipv4-package-with-do-not-fragment-flag-disabled.txt");
    }

    @UtilityClass
    public class MoreFragmentsFlag {
      private static final Path ROOT_DIRECTORY = IpV4PackageHexStream.ROOT_DIRECTORY.resolve("more-fragments-flag");

      public static final Path ENABLED = ROOT_DIRECTORY.resolve("ipv4-package-with-more-fragments-flag-enabled.txt");
      public static final Path DISABLED = ROOT_DIRECTORY.resolve("ipv4-package-without-more-fragments-flag-enabled.txt");
    }
  }
}
