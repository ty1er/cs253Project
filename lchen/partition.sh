#!/bin/bash

# execute in n3 n4 n5
sudo iptables -I INPUT -s n1 -j DROP
sudo iptables -I INPUT -s n2 -j DROP
sleep 10
sudo iptables -D INPUT -s n1 -j DROP
sudo iptables -D INPUT -s n2 -j DROP