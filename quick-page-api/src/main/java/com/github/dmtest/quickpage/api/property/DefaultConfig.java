package com.github.dmtest.quickpage.api.property;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:application.properties")
public interface DefaultConfig extends Config {

    @Key("page.package")
    String pagePackage();

}
