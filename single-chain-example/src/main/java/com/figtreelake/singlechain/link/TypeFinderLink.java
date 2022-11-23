package com.figtreelake.singlechain.link;

import com.figtreelake.corbeanprocessor.autoconfigure.link.ChainLink;

public interface TypeFinderLink extends ChainLink<TypeFinderLink> {

    String findType(Object object);
}
