#!/bin/bash
#set -x
ARGS_NUM=5
if [[ ( $# -lt $ARGS_NUM ) ]]; then
  echo "Usage: script.sh PARAM1 PARAM2 PARAM3 PARAM4 PARAM5"
  exit 1
fi

java -jar target/players-chat-0.0.1-SNAPSHOT-jar-with-dependencies.jar $@
