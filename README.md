# QUICK-PAGE
Автоматизация UI. Selenium + Page-object + HTML-elements

## Обзор возможностей
Библиотека представляет собой реализацию паттернов **page-object** и **page-factory** и предназначается для автоматизации UI.
Она предоставляет API для управления web-driver, создания и управления page-object, поиска элементов внутри page-object.
Основные принципы библиотеки - легковесность и возможность полного конфигурирования из проекта-клиента.

## Структура
Библиотека состоит из 2 модулей: **quick-page-api** и **quick-page-core**. Соответственно, пользовательский интерфейс и
дефолтная реализация.

<p><b>Интерфейс библиотеки</b></p>

| Наименование     | Описание                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
|------------------|---------------------------------------------------------------------------------------------|
| DriverManager    | Управляет поведением и предоставляет доступ к org.openqa.selenium.WebDriver                 |
| SearchManager    | Управляет поиском элементов в page-object и элементов в других элементах                    |
| PageManager      | Управляет инстанцированием page-object и хранит информацию о текущем page-object контексте  |
| PropertyManager  | Управляет настройками проекта                                                               |
| Environment      | Точка входа. Агрегатор для реализаций менеджеров и провайдер доступа к ним                  |

<p><b>Реализация библиотеки</b></p>
Все перечисленные интерфейсы имеют дефолтные реализации

## Использование
В своем проекте создайте инстанс Environment из расчета: каждый новый сценарий - новый Environment.
Инициализируйте созданный инстанс Environment инстансами DriverManager, SearchManager, PageManager и PropertyManager.

## Примечания
Библиотека отсутствует в Maven-central. Для ее использования необходимо сначала собрать и  инсталлировать ее в
локальный репозиторий. Воспользуйтесь командой:
```
mvn clean install
```