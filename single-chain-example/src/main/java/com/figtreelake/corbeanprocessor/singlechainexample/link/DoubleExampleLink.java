package com.figtreelake.corbeanprocessor.singlechainexample.link;

import org.springframework.stereotype.Component;

@Component
public class DoubleExampleLink extends AbstractExampleLink{
    @Override
    protected boolean applies(Object object) {
        return object instanceof Double;
    }

    @Override
    protected String doFindType(Object object) {
        return "Object is a double.";
    }
}
