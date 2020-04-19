#!/usr/bin/env bash

# start master & slave
redis-server redis6379.conf
redis-server redis6380.conf
redis-server redis6381.conf

# start sentinel
redis-sentinel sentinel.conf
