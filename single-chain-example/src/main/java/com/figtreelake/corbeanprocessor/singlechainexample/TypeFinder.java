package com.figtreelake.corbeanprocessor.singlechainexample;

import com.figtreelake.corbeanprocessor.singlechainexample.link.ExampleLink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypeFinder {

    private final ExampleLink first;

    public String findType(Object object) {
        return first.findType(object);
    }
}
