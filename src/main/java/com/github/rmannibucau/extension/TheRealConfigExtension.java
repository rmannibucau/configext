package com.github.rmannibucau.extension;

import com.github.rmannibucau.api.BootstrapConfig;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;

// the actual extension we are interested in
public class TheRealConfigExtension implements Extension {
    private final BootstrapConfig config;

    public TheRealConfigExtension() { // loaded by a ServiceLoader "by spec" this has to work
        config = new BootstrapConfig();
        // simulate load of SE sources, converters etc
        config.getValues().put("ext1", "v1");
        config.getValues().put("ext2", "v2");
        BootstrapConfig.current(config);
    }

    void addConfig(@Observes final AfterBeanDiscovery afterBeanDiscovery, final BeanManager bm) {
        // normal bean addition
        // afterBeanDiscovery.addBean(new ConfigBean(new Config(config, or, whatever)));
    }

    void afterStart(@Observes final AfterDeploymentValidation adv, final BeanManager bm) {
        // from there we can lookup Config directly so reset the bootstrap one
        // note: if startup fails we are not there and don't clean up the thread local,
        //       this is "ok" since the BootstrapConfig should be responsible to clean it up by itself with a timer or equivalent
        BootstrapConfig.current(null);
    }
}
