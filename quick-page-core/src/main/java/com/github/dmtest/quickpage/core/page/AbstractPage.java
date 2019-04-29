package com.github.dmtest.quickpage.core.page;

import com.github.dmtest.quickpage.api.driver.DriverManager;
import com.github.dmtest.quickpage.api.element.SearchManager;
import com.github.dmtest.quickpage.api.page.Page;
import com.github.dmtest.quickpage.core.common.CommonSupport;
import com.github.dmtest.quickpage.core.factory.CustomHtmlElementDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class AbstractPage implements Page {
    private DriverManager driverManager;
    private SearchManager searchManager;

    protected AbstractPage(DriverManager driverManager, SearchManager searchManager) {
        this.driverManager = driverManager;
        this.searchManager = searchManager;
        initPageElements();
    }

    protected void initPageElements() {
        WebDriver driver = driverManager.getDriver();
        PageFactory.initElements(new CustomHtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Override
    public String getName() {
        return CommonSupport.getAnnotationNameValue(this.getClass());
    }

    protected DriverManager getDriverManager() {
        return driverManager;
    }

    protected SearchManager getSearchManager() {
        return searchManager;
    }
}
