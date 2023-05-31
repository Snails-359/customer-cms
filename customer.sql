# 创建数据库
CREATE DATABASE IF NOT EXISTS customer_db DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

create table if not exists t_customer
(
    id     int(11)          not null auto_increment comment '客户ID',
    name   varchar(50)      not null comment '姓名',
    gender varchar(10)      not null comment '性别',
    age    int(11) unsigned not null comment '年龄',
    phone  varchar(20)      not null unique comment '电话',
    email  varchar(50)      not null unique comment '邮箱',
    primary key (id)
) engine = InnoDB
  default charset = utf8;


alter table t_customer modify age int(11) not null;


desc t_customer;
