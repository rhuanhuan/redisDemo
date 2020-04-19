#!/usr/bin/env bash

# start node
for (( i = 79; i < 85; ++i )); do
    redis-server redis63${i}.conf
done
