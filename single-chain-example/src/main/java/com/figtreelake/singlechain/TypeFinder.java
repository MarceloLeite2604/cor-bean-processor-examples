package com.figtreelake.singlechain;

import com.figtreelake.singlechain.link.TypeFinderLink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypeFinder {

    private final TypeFinderLink first;

    public String findType(Object object) {
        return first.findType(object);
    }
}
