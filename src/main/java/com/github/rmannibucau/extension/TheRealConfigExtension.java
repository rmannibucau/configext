package com.github.rmannibucau.extension;

import com.github.rmannibucau.api.BootstrapConfig;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

// the actual extension we are interested in
public class TheRealConfigExtension implements Extension {
    private BootstrapConfig config;

    void addConfig(@Observes final BeforeBeanDiscovery beforeBeanDiscovery, final BeanManager bm) {
        config = new BootstrapConfig();
        // simulate load of SE sources, converters etc
        config.getValues().put("ext1", "v1");
        config.getValues().put("ext2", "v2");

        // fire it to let other extensions be config aware
        // note: we can probably also pass BeforeBeanDiscovery and the BeanManager in a wrapper event (new ConfigStartEvent(beforeBeanDiscovery, bm, config))
        //       to let consumers use this event at the right moment
        bm.fireEvent(config);

        // Note 2: some pre events can fire the config builder if that's the used pattern
    }

    void addConfig(@Observes final AfterBeanDiscovery afterBeanDiscovery, final BeanManager bm) {
        // normal bean addition
        // afterBeanDiscovery.addBean(new ConfigBean(new Config(config, or, whatever)));
    }
}
