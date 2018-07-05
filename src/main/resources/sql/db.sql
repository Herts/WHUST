# noinspection SqlNoDataSourceInspection
CREATE DATABASE WHUStore;
USE WHUStore;

CREATE TABLE user(
iduser INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(40) NOT NULL UNIQUE,
password VARCHAR(40) NOT NULL,
email VARCHAR(100) NOT NULL UNIQUE,
phone VARCHAR(20) UNIQUE);


CREATE TABLE customer(
idcustomer INT PRIMARY KEY AUTO_INCREMENT,
iduser INT NOT NULL,
fname VARCHAR(100),
lname VARCHAR(100),
sex ENUM('F','M'),
birthdate DATETIME,
addr VARCHAR(100),
tel VARCHAR(20),
email VARCHAR(50),
membersince DATETIME,
CONSTRAINT FOREIGN KEY FK_iduser(iduser)
REFERENCES user(iduser));

CREATE TABLE team(
idteam INT PRIMARY KEY KEY AUTO_INCREMENT,
tname VARCHAR(50),
membersince DATETIME);

CREATE TABLE cart(
idcart INT PRIMARY KEY AUTO_INCREMENT,
iduser INT,
createdsince timestamp,
CONSTRAINT FOREIGN KEY FK_iduser_2(iduser)
REFERENCES user(iduser));

CREATE TABLE cartitem(
idcartitem INT PRIMARY KEY AUTO_INCREMENT,
idcart INT,
idproduct INT,
amount INT,
CONSTRAINT FOREIGN KEY FK_item_cart(idcart)
REFERENCES cart(idcart),
CONSTRAINT FOREIGN KEY FK_item_pro(idproduct)
REFERENCES product(idproduct)
);

CREATE TABLE orders(
idorder INT PRIMARY KEY AUTO_INCREMENT,
iduser INT NOT NULL,
otype VARCHAR(50),
createdsince DATETIME,
CONSTRAINT FOREIGN KEY FK_order_iduser(iduser)
REFERENCES user(iduser)
);

ALTER TABLE orders ADD otype VARCHAR(50);
CREATE TABLE orderitem(
idorderitem INT PRIMARY KEY AUTO_INCREMENT,
idorder INT,
idproduct INT,
amount INT,
CONSTRAINT FOREIGN KEY FK_item_order(idorder)
REFERENCES orders(idorder),
CONSTRAINT FOREIGN KEY FK_item_pro_2(idproduct)
REFERENCES product(idproduct)
);

CREATE TABLE product(
idproduct INT PRIMARY KEY AUTO_INCREMENT,
idteam INT NOT NULL,
pname VARCHAR(50),
description VARCHAR(50),
quantity INT,
CONSTRAINT FOREIGN KEY FK_pro_team(idteam)
REFERENCES team(idteam));

CREATE TABLE picture(
idpicture INT PRIMARY KEY AUTO_INCREMENT,
ppath VARCHAR(100),
ptype VARCHAR(100));

CREATE TABLE productpic(
idpp INT PRIMARY KEY AUTO_INCREMENT,
idproduct INT NOT NULL,
idpicture INT NOT NULL,
CONSTRAINT FOREIGN KEY FK_pp_product(idproduct)
REFERENCES product(idproduct),
CONSTRAINT FOREIGN KEY FK_pp_pic(idpicture)
REFERENCES picture(idpicture));

CREATE TABLE activity(
idactivity INT PRIMARY KEY AUTO_INCREMENT,
idteam INT NOT NULL,
aname VARCHAR(50),
astarttime DATETIME,
aendtime DATETIME,
apendtime DATETIME,
atype VARCHAR(100),
description VARCHAR(200),
CONSTRAINT FOREIGN KEY FK_act_team(idteam)
REFERENCES team(idteam));

CREATE TABLE activitypic(
idap INT PRIMARY KEY AUTO_INCREMENT,
idactivity INT NOT NULL,
idpicture INT NOT NULL,
CONSTRAINT FOREIGN KEY FK_ap_activity(idactivity)
REFERENCES activity(idactivity)
);

CREATE TABLE customerInfo(
idcustomerInfo INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50),
addr VARCHAR(50),
tel VARCHAR(20));

CREATE TABLE category(
idcategory INT PRIMARY KEY AUTO_INCREMENT,
cname VARCHAR(50),
cdescription VARCHAR(100));

CREATE TABLE procat(
idprocat INT PRIMARY KEY AUTO_INCREMENT,
idproduct INT NOT NULL,
idcategory INT NOT NULL,
CONSTRAINT FOREIGN KEY FK_procat_product(idproduct)
REFERENCES product(idproduct),
CONSTRAINT FOREIGN KEY FK_procat_category(idcategory)
REFERENCES category(idcategory));

drop table product;
drop table picture;
drop table productpic;

ALTER TABLE team ADD iduser INT;
ALTER TABLE team ADD CONSTRAINT FOREIGN KEY FK_team_user(iduser) REFERENCES user(iduser);

ALTER TABLE activitypic  ADD CONSTRAINT FOREIGN KEY FK_ap_pic(idpicture)
REFERENCES picture(idpicture);

ALTER TABLE product ADD idteam INT;
ALTER TABLE product ADD CONSTRAINT FOREIGN KEY FK_pro_team(idteam)
REFERENCES team(idteam);

