drop table if exists T_ACCOUNT;
drop table if exists user_roles;
drop table if exists users;

-- Tables
create table T_ACCOUNT (id bigint auto_increment, cashBalance double, name varchar(255), primary key (id));

-- Data
insert into T_ACCOUNT (cashBalance, name) values (500, 'John Smith');
insert into T_ACCOUNT (cashBalance, name) values (1000, 'Bernard Dupont');
insert into T_ACCOUNT (cashBalance, name) values (1500, 'Bart Simpson');



CREATE TABLE `users` (
  `USER_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(256) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_roles` (
  `USER_ROLE_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `AUTHORITY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles` (`USER_ID`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- INSET the administrator
INSERT INTO users (USER_ID, USERNAME,PASSWORD, ENABLED)
VALUES (1, 'siteadmin', '$2a$10$5MuOMJB1/jF3HXeakcUQZuyCCDQhcMa3sugDAFgbycGCY1yUsq1M2', TRUE); --password: 1234
 
INSERT INTO user_roles (USER_ROLE_ID, USER_ID,AUTHORITY)
VALUES (1, 1, 'ROLE_ADMIN');

-- INSERT a regular user
INSERT INTO users (USER_ID, USERNAME,PASSWORD, ENABLED)
VALUES (2, 'user1', '$2a$10$5MuOMJB1/jF3HXeakcUQZuyCCDQhcMa3sugDAFgbycGCY1yUsq1M2', TRUE); --password: 1234
 
INSERT INTO user_roles (USER_ROLE_ID, USER_ID,AUTHORITY)
VALUES (2, 2, 'ROLE_USER');