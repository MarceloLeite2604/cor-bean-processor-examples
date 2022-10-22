package com.figtreelake.singlechain.link;

import com.figtreelake.corbeanprocessor.autoconfigure.link.ChainLink;
import lombok.Setter;

public abstract class AbstractTypeFinderLink implements ChainLink<AbstractTypeFinderLink>, TypeFinderLink {

    @Setter
    private AbstractTypeFinderLink next;

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
