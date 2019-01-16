#!/usr/bin/env bash

PAGE=$((RANDOM%3))

http :8080/persons/?page=$PAGE
echo -e "\npage was: $PAGE"
