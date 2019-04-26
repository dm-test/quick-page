package com.github.dmtest.quickpage.core.page;

import com.github.dmtest.quickpage.api.entrypoint.Environment;
import com.github.dmtest.quickpage.api.page.Page;
import com.github.dmtest.quickpage.core.common.CommonSupport;
import com.github.dmtest.quickpage.core.factory.CustomHtmlElementDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class AbstractPage implements Page {

    protected AbstractPage(Environment environment) {
        initPageElements(environment);
    }

    protected void initPageElements(Environment environment) {
        WebDriver driver = environment.getDriverManager().getDriver();
        PageFactory.initElements(new CustomHtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Override
    public String getName() {
        return CommonSupport.getAnnotationNameValue(this.getClass());
    }
}
