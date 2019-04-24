package com.github.dmtest.quickpage.api.element;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface SearchManager {

    <T extends WebElement> T searchElement(Object context, String path);

    <T extends WebElement> T searchElement(String path);

    <E extends WebElement> List<E> searchElementList(Object context, String path);

    <E extends WebElement> List<E> searchElementList(String path);

}
