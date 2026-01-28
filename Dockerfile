FROM eclipse-temurin:17-jdk-alpine
RUN apk update && apk add --no-cache maven dos2unix

# Копируем локальный архив Allure в директорию /app
COPY /program/allure-2.23.0 /opt/allure
RUN dos2unix /opt/allure/bin/allure && chmod +x /opt/allure/bin/allure && ln -sf /opt/allure/bin/allure /usr/local/bin/allure

WORKDIR /app
COPY . /app

RUN mvn clean install -Dmaven.test.skip=true

ENV PATH="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
ENV TestRun ""
CMD ["sh","-c","$TestRun"]

#COPY entrypoint.sh /app/entrypoint.sh
#RUN chmod +x /app/entrypoint.sh
#ENTRYPOINT ["/app/entrypoint.sh"]
