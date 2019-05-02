package com.github.dmtest.quickpage.core.property;

import com.github.dmtest.quickpage.api.property.DefaultConfig;
import com.github.dmtest.quickpage.api.property.PropertyManager;
import org.aeonbits.owner.ConfigFactory;

import java.util.Objects;

public class DefaultPropertyManager<T extends DefaultConfig> implements PropertyManager<T> {
    private Class<T> configClass;
    private T config;

    public DefaultPropertyManager(Class<T> configClass) {
        this.configClass = configClass;
    }

    @Override
    public T getConfig() {
       if (Objects.isNull(config)) {
           config = ConfigFactory.create(configClass);
       }
        return config;
    }
}
