package com.github.dmtest.quickpage.example.stepdefs;

import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.core.entrypoint.TestDefaultEnvironment;
import cucumber.api.java.ru.Когда;

public class CommonSteps {
    private Environment testDefaultEnvironment;

    @Когда("^Открывает URL \"([^\"]*)\"$")
    public void openUrl(String url) {
        testDefaultEnvironment.getDriver().get(url);
        System.out.println(testDefaultEnvironment.toString());
    }

    public CommonSteps(TestDefaultEnvironment testDefaultEnvironment) {
        this.testDefaultEnvironment = testDefaultEnvironment;
    }
}
