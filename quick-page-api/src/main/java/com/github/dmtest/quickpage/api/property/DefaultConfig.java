package com.github.dmtest.quickpage.api.property;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:application.properties")
public interface DefaultConfig extends Config {
//    General
    @Key("page.package")
    String pagePackage();

    @Key("wait.timeout")
    int waitTimeout();

    @Key("browser.name")
    String browserName();

//    Remote webdriver support
    @Key("remote.webdriver.url")
    String remoteWebdriverUrl();

    @Key("remote.webdriver.browser.version")
    String remoteWebdriverBrowserVersion();

    @Key("remote.webdriver.enable.vnc")
    @DefaultValue("false")
    boolean remoteWebDriverEnableVNC();

    @Key("remote.webdriver.screen.resolution")
    @DefaultValue("1920x1080x24")
    String remoteWebDriverScreenResolution();

    @Key("remote.webdriver.project.name")
    @DefaultValue("MyProject")
    String remoteWebdriverProjectName();

}
