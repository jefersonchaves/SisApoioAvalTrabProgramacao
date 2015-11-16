SAATP-ExemploJava
=============

This github repository is used by a tool called 'Sistema de Apoio a Avaliação de Trabalhos de Programação'. That start vagrant boxes for a certain github project. This project is using another project as base for application provisioning, see [vagrant-devel](https://github.com/pussinboots/vagrant-devel).

##Base Box

The base box is uploaded to dropbox drive and can be downloaded by [vagrantcloud](https://vagrantcloud.com/pussinboots/ubuntu-truly-jdk8). It is refrenced in the Vagrantfile
```ruby
config.vm.box = "pussinboots/ubuntu-truly-jdk8"
```
that tells vagrant to download it from vagrantcloud by using the url mentioned above. The download can take a while the file is 1.0 GB big. 

A tutorial to create a vagrant base box look [here](https://docs.vagrantup.com/v2/boxes/base.html).

##Login Shell Problem

There is a problem with nodejs and npm when you open a terminal.
The workaround is to run ```su -l vagrant```enter the password vagrant and than npm and nodejs 
can be used. Has to be done for every terminal. Maybe someone has a complete solution for that.