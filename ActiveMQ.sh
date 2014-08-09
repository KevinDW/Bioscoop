#!/usr/bin/env bash

cd /home/vagrant/

wget http://repository.apache.org/content/repositories/snapshots/org/apache/activemq/apache-activemq/5.10-SNAPSHOT/apache-activemq-5.10-20140603.223748-79-bin.tar.gz
tar zxvf apache-activemq-5.10-20140603.223748-79-bin.tar.gz

mv apache-activemq-5.10-SNAPSHOT activemq
cd activemq/bin

chmod 755 activemq
./activemq setup /home/vagrant/.activemqrc
./activemq start
./activemq status
