#!/bin/sh
export UrlChrome
mvn clean test -Dmaven.test.skip=false -Dtest="JAVATest#Attribute" -DUrlChrome=$UrlChrome -Dbrowser="Chrome"
../bin/allure/bin/allure generate target/allure-results --clean