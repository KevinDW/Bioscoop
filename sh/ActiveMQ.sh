#!/usr/bin/env bash

cd /home/vagrant/

wget http://repository.apache.org/content/repositories/snapshots/org/apache/activemq/apache-activemq/5.10-SNAPSHOT/apache-activemq-5.10-20140603.223748-79-bin.tar.gz
tar zxvf apache-activemq-5.10-20140603.223748-79-bin.tar.gz
rm -f apache-activemq-5.10-20140603.223748-79-bin.tar.gz

sudo mv /home/vagrant/apache-activemq-5.10-SNAPSHOT /opt/activemq
sudo ln -sf /opt/activemq/bin/activemq /etc/init.d/
sudo update-rc.d activemq defaults

sudo chmod 755 /opt/activemq/bin/activemq

/opt/activemq/bin/activemq setup /etc/default/activemq
sudo chown root:nogroup /etc/default/activemq
sudo chmod 600 /etc/default/activemq

sudo /etc/init.d/activemq start
sudo /etc/init.d/activemq status
