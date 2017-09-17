#!/usr/bin/env bash

cd src/scala;

wget https://github.com/codacy/codacy-coverage-reporter/releases/download/2.0.0/codacy-coverage-reporter-2.0.0-assembly.jar

mvn clean test cobertura:cobertura coveralls:report;

java -cp codacy-coverage-reporter-2.0.0-assembly.jar com.codacy.CodacyCoverageReporter -l Java -r target/site/cobertura/coverage.xml