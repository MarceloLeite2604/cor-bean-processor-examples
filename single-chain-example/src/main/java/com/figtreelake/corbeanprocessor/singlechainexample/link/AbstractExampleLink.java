package com.figtreelake.corbeanprocessor.singlechainexample.link;

import com.figtreelake.corbeanprocessor.autoconfigure.link.ChainLink;
import lombok.Setter;

public abstract class AbstractExampleLink implements ChainLink<AbstractExampleLink>, ExampleLink {

    @Setter
    private AbstractExampleLink next;

    @Override
    public String findType(Object object) {
        if (applies(object)) {
            return doFindType(object);
        }

        if (next != null) {
            return next.findType(object);
        }

        final  var message = String.format("Cannot defined type of %s object.", object);
        throw new IllegalStateException(message);
    }

    protected abstract boolean applies(Object object);

    protected abstract String doFindType(Object object);
}
