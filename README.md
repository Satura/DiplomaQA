# Дипломный проект по курсу «Тестировщик ПО»

## Документация

- [План](https://github.com/Satura/DiplomaQA/blob/master/documents/Plan.md)
- [Отчет по результатам тестирования](https://github.com/Satura/DiplomaQA/blob/master/documents/Report.md)
- [Отчет по итогам автоматизации](https://github.com/Satura/DiplomaQA/blob/master/documents/Summary.md)

## Запуск

Для запуска приложения и тестов  должен быть установлен Docker или Docker Toolbox.
Для загрузки репозитория используется команда: `git clone https://github.com/Satura/DiplomaQA.git`

Для запуска docker-контейнера с СУБД MySQL и PostgreSQL, а также Node.js требуется открыть терминал в папке проекта и ввести команду `docker-compose up -d`

Для запуска приложения и тестов с MySQL используются следующие команды:

- Запуск приложения `java -jar aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app`. Дождаться появления строки `ru.netology.shop.ShopApplication : Started ShopApplication ...`
- Запуск автотестов `./gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app allureReport`

Для запуска приложения и тестов с PostgreSQL используются следующие команды:

- Запуск приложения `java -jar aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app`. Дождаться появления строки `ru.netology.shop.ShopApplication : Started ShopApplication ...`
- Запуск автотестов `./gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app allureReport`

## Отчеты

Чтобы посмотреть результаты прогона автотестов запустить в терминале: `./gradlew allureServe`, результаты будут доступны по адресу: `http://localhost:37653/index.html`

## Остановка сервисов

Для остановки allureServe и приложения используется сочетание клавиш **CRTL + C**

#### [Задание на работу](https://github.com/netology-code/qa-diploma)
