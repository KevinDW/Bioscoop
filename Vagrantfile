Vagrant.configure("2") do |config|

  config.vm.box = "ubuntu/trusty64"

  config.vm.network :private_network, ip: "192.168.20.200"
  config.vm.synced_folder ".", "/vagrant", id: "core"

  config.vm.provider :virtualbox do |vb|

    vb.customize ["modifyvm", :id, "--memory", 384]
    vb.customize ["guestproperty", "set", :id, "/VirtualBox/GuestAdd/VBoxService/--timesync-set-threshold", 10000]
    vb.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
    vb.customize ["modifyvm", :id, "--natdnsproxy1", "on"]

  end

  config.vm.provision "shell", path: "./sh/Base.sh"
  config.vm.provision "shell", path: "./sh/MySQL.sh"
  config.vm.provision "shell", path: "./sh/ActiveMQ.sh"

end
