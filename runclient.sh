#!/usr/bin/env bash

arguments=--operation=$1
if [ $# -gt 1 ]; then
  arguments=${arguments},--parameter1=$2
  if [ $# -gt 2 ]; then
    arguments=${arguments},--parameter2=$3
  fi
fi

mvn spring-boot:run -Dspring-boot.run.profiles=local -Dspring-boot.run.arguments=$arguments
