#!/bin/sh
mvn clean test -Dmaven.test.skip=false -Dtest="JAVATest#forGitHub7"
../bin/allure/bin/allure generate target/allure-results --clean