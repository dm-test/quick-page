package com.github.dmtest.quickpage.api.page;

import com.github.dmtest.quickpage.api.entrypoint.Environment;
import ru.yandex.qatools.htmlelements.element.Named;

public abstract class Page implements Named {
    private Environment environment;

    protected Page(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
