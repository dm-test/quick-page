package com.github.dmtest.quickpage.example.stepdefs;

import com.github.dmtest.quickpage.api.entrypoint.Environment;
import cucumber.api.java.ru.Когда;

public class CommonSteps {
    private Environment environment;

    public CommonSteps(Environment environment) {
        this.environment = environment;
    }

    @Когда("^Открывает URL \"([^\"]*)\"$")
    public void openUrl(String url) {
        environment.getDriverManager().getDriver().get(url);
    }
}
