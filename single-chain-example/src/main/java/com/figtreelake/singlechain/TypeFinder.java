package com.figtreelake.singlechain;

import com.figtreelake.singlechain.link.TypeFinderLink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypeFinder {

    private final TypeFinderLink firstLink;

    public String findType(Object object) {
        return firstLink.findType(object);
    }
}
