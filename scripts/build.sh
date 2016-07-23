#!/usr/bin/env bash

cd src/scala;

mvn clean test cobertura:cobertura coveralls:report;