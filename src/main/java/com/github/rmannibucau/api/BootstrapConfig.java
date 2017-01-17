package com.github.rmannibucau.api;

import java.util.HashMap;
import java.util.Map;

// this design is not important, this poc is about the lifecycle more than anything else
public class BootstrapConfig {
    private Map<String, String> values = new HashMap<>();

    public Map<String, String> getValues() {
        return values;
    }
}
