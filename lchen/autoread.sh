#!/bin/bash

echo "Start to read 2000 tuples from 5 nodes:"

python read1.py &
python read2.py &
python read3.py &
python read4.py &
python read5.py 
