FROM eclipse-temurin:17-jdk-alpine
RUN apk update && apk add --no-cache maven

# Копируем локальный архив Allure в директорию /app
COPY /program/allure-2.23.0 /opt/allure
RUN chmod +x /opt/allure/bin/allure \
 && ln -s /opt/allure/bin/allure /usr/local/bin/allure

# Добавляем Allure в PATH
ENV PATH="/opt/allure-2.23.0/bin:${PATH}"

WORKDIR /app
COPY . /app

RUN mvn clean install -Dmaven.test.skip=true

ENV TestRun ''
CMD $TestRun

#COPY entrypoint.sh /app/entrypoint.sh
#RUN chmod +x /app/entrypoint.sh
#ENTRYPOINT ["/app/entrypoint.sh"]
