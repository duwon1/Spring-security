# Mysql DB 및 사용자 생성
```mysql
create user 'cos'@'%' identified by '1234';
grant all privileges on *.* to 'cos'@'%';
create database security;
use security;
```
