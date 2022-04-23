#!/bin/zsh

WORKING_DIR=$(pwd)
cd ..
# print current directory
echo "Current directory: $(pwd)"

#move target directory
# shellcheck disable=SC2046
cd $(pwd)/target || exit

java -jar ecommerce-0.0.1-SNAPSHOT.jar