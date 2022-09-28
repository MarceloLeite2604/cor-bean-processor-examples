package com.figtreelake.corbeanprocessor.singlechainexample.link;

import org.springframework.stereotype.Component;

@Component
public class NullExampleLink extends AbstractExampleLink{
    @Override
    protected boolean applies(Object object) {
        return object == null;
    }

    @Override
    protected String doFindType(Object object) {
        return "Object is null.";
    }
}
