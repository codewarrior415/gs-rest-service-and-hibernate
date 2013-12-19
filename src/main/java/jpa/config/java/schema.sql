drop table if exists T_ACCOUNT;

-- Tables
create table T_ACCOUNT (id bigint auto_increment, cashBalance double, name varchar(255), primary key (id));

-- Data
insert into T_ACCOUNT (cashBalance, name) values (500, 'John Smith');
insert into T_ACCOUNT (cashBalance, name) values (1000, 'Bernard Dupont');
insert into T_ACCOUNT (cashBalance, name) values (1500, 'Bart Simpson');
