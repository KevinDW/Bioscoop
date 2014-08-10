#!/usr/bin/env bash

sudo debconf-set-selections <<< "mysql-server mysql-server/root_password password root"
sudo debconf-set-selections <<< "mysql-server mysql-server/root_password_again password root"

sudo apt-get install -qq mysql-server

sed -i "s/bind-address.*/bind-address = 0.0.0.0/" /etc/mysql/my.cnf

mysql -uroot -proot -e "GRANT ALL ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;FLUSH PRIVILEGES;"

service mysql restart

mysql -uroot -proot < /vagrant/sql/CreateDB.sql
mysql -uroot -proot bioscoop < /vagrant/sql/InsertDB.sql
