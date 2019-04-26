package com.github.dmtest.quickpage.example.stepdefs;

import com.github.dmtest.quickpage.core.entrypoint.DefaultEnvironment;
import cucumber.api.java.ru.Когда;

public class CommonSteps {

    @Когда("^Открывает URL \"([^\"]*)\"$")
    public void openUrl(String url) {
        DefaultEnvironment.getDriver().get(url);
    }
}
