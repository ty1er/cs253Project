#!/bin/bash

sleep 5

echo "Start to write 2000 tuples to 5 nodes:"

python write5.py  &
python write4.py  &
python write3.py  &
python write2.py  &
python write1.py 
