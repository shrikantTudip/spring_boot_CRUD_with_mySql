create database `spring_demo`;
use `spring_demo`;
create table `Product` ( `id` int(11) unsigned NOT NULL, `name` varchar(100) DEFAULT NULL, `image` varchar(500) DEFAULT NULL, `color` varchar(100) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;