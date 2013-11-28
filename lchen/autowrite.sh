#!/bin/bash

echo "Start to write 2000 tuples to 5 nodes:"

python write1.py &
python write2.py &
python write3.py &
python write4.py &
python write5.py 
