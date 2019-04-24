package com.github.dmtest.quickpage.core.common;

import com.github.dmtest.quickpage.api.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.util.Optional;

public class CommonSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CommonSupport.class);

    private CommonSupport() {
    }

    public static String getAnnotationNameValue(AnnotatedElement annotatedElement) {
        return Optional.ofNullable(annotatedElement.getAnnotation(Name.class))
                .map(Name::value)
                .orElse("");
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class clazz) {
        try {
            Constructor<? extends Page> constructor = clazz.getConstructor();
            constructor.setAccessible(true);
            return (T) (constructor.newInstance());
        } catch (ReflectiveOperationException e) {
            LOG.error("Failed to instantiate class with name '{}'", clazz.getSimpleName());
            throw new RuntimeException(e);
        }
    }
}
