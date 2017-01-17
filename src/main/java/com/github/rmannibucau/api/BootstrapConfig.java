package com.github.rmannibucau.api;

import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

// this design is not important, this poc is about the lifecycle more than anything else
public class BootstrapConfig {
    // real impl would likely use a Map<Thread> with eviction?
    private static final ThreadLocal<BootstrapConfig> CURRENT = new ThreadLocal<>();

    private Map<String, String> values = new HashMap<>();

    public Map<String, String> getValues() {
        return values;
    }

    public static BootstrapConfig current() {
        return ofNullable(CURRENT.get()).orElseThrow(() -> new IllegalStateException("Not in CDI bootstrap, you should use @Inject Config config;"));
    }

    // @Internal in a real impl
    public static void current(final BootstrapConfig config) {
        if (config == null) {
            CURRENT.remove();
            return;
        }
        CURRENT.set(config);
    }
}
