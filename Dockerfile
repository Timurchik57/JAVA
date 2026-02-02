FROM eclipse-temurin:17-jdk-alpine
ARG ALLURE_VERSION=2.34.1

RUN apk add --no-cache maven dos2unix curl tar

# Скачиваем allure для отчёта
RUN curl -fsSL -o /tmp/allure.tgz \
      "https://github.com/allure-framework/allure2/releases/download/${ALLURE_VERSION}/allure-${ALLURE_VERSION}.tgz" \
    && mkdir -p /opt \
    && tar -xzf /tmp/allure.tgz -C /opt \
    && dos2unix /opt/allure-${ALLURE_VERSION}/bin/allure \
    && chmod +x /opt/allure-${ALLURE_VERSION}/bin/allure \
    && ln -sf /opt/allure-${ALLURE_VERSION}/bin/allure /usr/local/bin/allure \
    && rm -f /tmp/allure.tgz

WORKDIR /app
COPY . /app

# чтобы компиляция не проходила во время сборки контейнера
RUN mvn clean install -Dmaven.test.skip=true

ENV TestRun=""
CMD ["sh","-c","$TestRun"]



#COPY entrypoint.sh /app/entrypoint.sh
#RUN chmod +x /app/entrypoint.sh
#ENTRYPOINT ["/app/entrypoint.sh"]
