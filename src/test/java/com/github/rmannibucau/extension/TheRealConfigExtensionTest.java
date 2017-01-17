package com.github.rmannibucau.extension;

import com.github.rmannibucau.ValidatorInstance;
import org.apache.meecrowave.junit.MeecrowaveRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

public class TheRealConfigExtensionTest {
    @ClassRule
    public static final MeecrowaveRule MEECROWAVE = new MeecrowaveRule();

    @Inject
    private ValidatorInstance instance;

    @Test
    public void run() {
        MEECROWAVE.inject(this);
        assertEquals("v1", instance.getV1().getValue());
        assertEquals("v2", instance.getV2().getValue());
    }
}
