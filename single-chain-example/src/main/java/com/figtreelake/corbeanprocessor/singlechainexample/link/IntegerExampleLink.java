package com.figtreelake.corbeanprocessor.singlechainexample.link;

import org.springframework.stereotype.Component;

@Component
public class IntegerExampleLink extends AbstractExampleLink{
    @Override
    protected boolean applies(Object object) {
        return object instanceof Integer;
    }

    @Override
    protected String doFindType(Object object) {
        return "Object is an integer.";
    }
}
