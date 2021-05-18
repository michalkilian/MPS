#!/bin/bash
# Simple grid visualization script
for file in vis-*.txt
do
	cat $file
	sleep 1
	clear
done