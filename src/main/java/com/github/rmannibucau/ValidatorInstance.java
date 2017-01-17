package com.github.rmannibucau;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ValidatorInstance {
    @Inject
    @Named("ext1")
    private ValueHolder v1;

    @Inject
    @Named("ext2")
    private ValueHolder v2;

    public ValueHolder getV1() {
        return v1;
    }

    public ValueHolder getV2() {
        return v2;
    }
}
