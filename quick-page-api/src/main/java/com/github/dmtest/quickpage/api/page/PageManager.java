package com.github.dmtest.quickpage.api.page;

public interface PageManager {

    <T extends Page> T getNewPageByName(String name);

}
