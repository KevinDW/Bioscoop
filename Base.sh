#!/usr/bin/env bash

sudo apt-get update
sudo apt-get install -qq curl unzip ack-grep default-jre

shopt -s nocasematch

fallocate -l 768M /swapfile
chmod 600 /swapfile
mkswap /swapfile
swapon /swapfile

echo "/swapfile   none    swap    sw    0   0" | tee -a /etc/fstab
printf "vm.swappiness=10\nvm.vfs_cache_pressure=50" | tee -a /etc/sysctl.conf && sysctl -p

shopt -u nocasematch
