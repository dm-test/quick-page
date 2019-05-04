package com.github.dmtest.quickpage.api.element;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Интерфейс для управления поиском элементов в page-object или другом заданном контексте (например, в других элементах)
 */
public interface SearchManager {
    /**
     * Метод отдает элемент из заданного контекста по заданному пути поиска
     *
     * @param context контекст, в котором осуществляется поиск элемента (page-object, другой элемент)
     * @param path    путь до искомого элемента
     * @param <T>     искомый элемент
     * @return элемент из заданного контекста по заданному пути поиска
     */
    <T extends WebElement> T searchElement(Object context, String path);

    /**
     * Метод отдает элемент из текущего контекста по заданному пути поиска
     *
     * @param path путь до искомого элемента
     * @param <T>  тип искомого элемента
     * @return элемент из текущего контекста по заданному пути поиска
     */
    <T extends WebElement> T searchElement(String path);

    /**
     * Метод отдает {@link List<T>} элементов из заданного контекста по заданному пути поиска
     *
     * @param context контекст, в котором осуществляется поиск элемента (page-object, другой элемент)
     * @param path    путь до искомого {@link List<T>} элементов
     * @param <T>     тип искомого элемента
     * @return {@link List<T>} элементов из заданного контекста по заданному пути поиска
     */
    <T extends WebElement> List<T> searchElementList(Object context, String path);

    /**
     * Метод отдает {@link List<T>} элементов из текущего контекста по заданному пути поиска
     *
     * @param path путь до искомого {@link List<T>} элементов
     * @param <T>  тип искомого элемента
     * @return {@link List<T>} элементов из текущего контекста по заданному пути поиска
     */
    <T extends WebElement> List<T> searchElementList(String path);

}
