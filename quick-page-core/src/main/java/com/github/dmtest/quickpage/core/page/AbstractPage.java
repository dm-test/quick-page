package com.github.dmtest.quickpage.core.page;

import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.Page;
import com.github.dmtest.quickpage.core.common.CommonSupport;
import com.github.dmtest.quickpage.core.factory.CustomHtmlElementDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class AbstractPage extends Page {

    protected AbstractPage(Environment environment) {
        super(environment);
        initPageElements();
    }

    protected void initPageElements() {
        WebDriver driver = getEnvironment().getDriver();
        PageFactory.initElements(new CustomHtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Override
    public String getName() {
        return CommonSupport.getAnnotationNameValue(this.getClass());
    }
}
