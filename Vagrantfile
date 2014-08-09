github_url = "https://raw.githubusercontent.com/fideloper/Vaprobash/1.0.1"

server_timezone  = "Europe/Brussels"
server_ip = "192.168.20.200"
server_memory = "384"
server_swap  = "768"

mysql_root_password = "root"
mysql_version = "5.5"
mysql_enable_remote = "true"

Vagrant.configure("2") do |config|

  config.vm.box = "ubuntu/trusty64"

  config.vm.provision "shell", inline: "echo setting timezone to #{server_timezone}; ln -sf /usr/share/zoneinfo/#{server_timezone} /etc/localtime"

  config.vm.network :private_network, ip: server_ip
  config.vm.synced_folder ".", "/vagrant", id: "core"

  config.vm.provider :virtualbox do |vb|

    vb.customize ["modifyvm", :id, "--memory", server_memory]
    vb.customize ["guestproperty", "set", :id, "/VirtualBox/GuestAdd/VBoxService/--timesync-set-threshold", 10000]
    vb.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
    vb.customize ["modifyvm", :id, "--natdnsproxy1", "on"]

  end

  config.vm.provision "shell", path: "#{github_url}/scripts/base.sh", args: [github_url, server_swap]
  config.vm.provision "shell", path: "#{github_url}/scripts/mysql.sh", args: [mysql_root_password, mysql_version, mysql_enable_remote]
  config.vm.provision "shell", path: "./Database.sh"

end
