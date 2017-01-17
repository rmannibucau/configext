package com.github.rmannibucau;

import javax.enterprise.inject.Vetoed;

@Vetoed
public class ValueHolder {
    private final String value;

    protected ValueHolder() {
        this(null); // for proxies (@AppScoped)
    }

    public ValueHolder(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
