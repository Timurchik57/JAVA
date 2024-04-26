FROM openjdk:17-alpine
RUN apk update && apk add --no-cache maven
RUN apk add --no-cache chromium-chromedriver

# Копируем локальный архив Allure в директорию /app
COPY /program/allure-2.23.0 /bin/allure

WORKDIR /app
COPY . /app

RUN mvn clean install -Dmaven.test.skip=true

COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh
ENTRYPOINT ["/app/entrypoint.sh"]




# Запуск тестов Maven
#CMD mvn clean test -Dtest="Authorization#Authorizations"
#CMD allure generate target/allure-results --clean

