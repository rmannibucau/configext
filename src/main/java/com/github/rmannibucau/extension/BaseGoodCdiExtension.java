package com.github.rmannibucau.extension;

import com.github.rmannibucau.ValueHolder;
import com.github.rmannibucau.api.BootstrapConfig;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public abstract class BaseGoodCdiExtension implements Extension {
    private String value;

    protected abstract String key();

    void start(@Observes final BootstrapConfig config) {
        value = config.getValues().get(key());
    }

    void addBean(@Observes final AfterBeanDiscovery afterBeanDiscovery) {
        afterBeanDiscovery.addBean(new ValueHolderBean(new ValueHolder(value), key()));
    }
}
