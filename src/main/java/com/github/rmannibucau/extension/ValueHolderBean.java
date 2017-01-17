package com.github.rmannibucau.extension;

import com.github.rmannibucau.ValueHolder;
import org.apache.webbeans.annotation.NamedLiteral;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;

import static java.util.Collections.singleton;

class ValueHolderBean implements Bean<ValueHolder> {
    private final ValueHolder instance;
    private final String key;
    private final Set<Annotation> qualifiers;

    ValueHolderBean(final ValueHolder instance, final String key) {
        this.instance = instance;
        this.key = key;
        // this is an OWB literal but nothing really specific to OWB, just used as shortcut
        this.qualifiers = singleton(new NamedLiteral(key));
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return Collections.emptySet();
    }

    @Override
    public Class<?> getBeanClass() {
        return ValueHolder.class;
    }

    @Override
    public boolean isNullable() {
        return false;
    }

    @Override
    public ValueHolder create(final CreationalContext<ValueHolder> context) {
        return instance;
    }

    @Override
    public void destroy(final ValueHolder instance, final CreationalContext<ValueHolder> context) {
        // no-op
    }

    @Override
    public Set<Type> getTypes() {
        return singleton(ValueHolder.class);
    }

    @Override
    public Set<Annotation> getQualifiers() {
        return qualifiers;
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return ApplicationScoped.class;
    }

    @Override
    public String getName() {
        return key;
    }

    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return Collections.emptySet();
    }

    @Override
    public boolean isAlternative() {
        return false;
    }
}
