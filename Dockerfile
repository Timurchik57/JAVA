FROM openjdk:17-alpine
RUN apk update && apk add --no-cache maven

# Копируем локальный архив Allure в директорию /app
COPY /program/allure-2.23.0 /bin/allure

WORKDIR /app
COPY . /app

RUN mvn clean install -Dmaven.test.skip=true

COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh
ENTRYPOINT ["/app/entrypoint.sh"]