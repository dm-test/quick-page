package com.github.dmtest.quickpage.api.property;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:application.properties")
public interface DefaultConfig extends Config {
//    General
    @Key("page.package")
    String pagePackage();

//    Remote webdriver support
    @Key("remote.webdriver.url")
    String remoteWebdriverUrl();

    @Key("remote.webdriver.browser.version")
    String remoteWebdriverBrowserVersion();

    @Key("remote.webdriver.enable.vnc")
    @DefaultValue("false")
    String remoteWebDriverEnableVNC();

    @Key("remote.webdriver.screen.resolution")
    @DefaultValue("1920x1080x24")
    String remoteWebDriverScreenResolution();

    @Key("remote.webdriver.project.name")
    @DefaultValue("MyProject")
    String remoteWebdriverProjectName();

    @Key("remote.webdriver.timezone")
    @DefaultValue("Europe/Moscow")
    String remoteWebdriverTimezone();

}
