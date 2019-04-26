package com.github.dmtest.quickpage.example.pages;

import com.github.dmtest.quickpage.api.page.Page;
import com.github.dmtest.quickpage.core.common.CommonSupport;
import com.github.dmtest.quickpage.core.factory.CustomHtmlElementDecorator;
import com.github.dmtest.quickpage.example.elements.HeaderSearch;
import com.github.dmtest.quickpage.example.elements.HeaderTop;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Named;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class AnyPage implements Page, Named {

    @Name("Хедер верхний")
    @FindBy(xpath = "//div[@class='header-top']")
    private HeaderTop headerTop;

    @Name("Хедер поиска")
    @FindBy(xpath = "//nav[@id='header-search']")
    private HeaderSearch headerSearch;

    AnyPage() {
//        WebDriver driver = DriverSupport.getDriver();
//        PageFactory.initElements(new CustomHtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Override
    public String getName() {
        return CommonSupport.getAnnotationNameValue(this.getClass());
    }

    public HeaderSearch getHeaderSearch() {
        return headerSearch;
    }

    public HeaderTop getHeaderTop() {
        return headerTop;
    }
}
