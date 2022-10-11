#!/bin/bash
# Simple grid visualization script
for file in $(seq 0 $(( $(ls gridVisualizations | wc -l) - 1 )) )
do
	cat gridVisualizations/vis-$file.txt
	sleep 1
	clear
done