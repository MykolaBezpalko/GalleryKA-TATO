drop database if exists gallery;
create database gallery;
use gallery;


create table language(
id int not null primary key auto_increment,
name varchar(25) not null
);

create table admin(
id int not null primary key auto_increment,
name varchar(50) not null,
email varchar(50) unique,
password varchar(50)
);

create table user(
id int not null primary key auto_increment,
name varchar(50) default "defaultUserName",
email varchar(50) not null unique,
password varchar(50) not null,
language_id int,
FOREIGN KEY (language_id) references language (id)
);

create table description(
id int not null primary key auto_increment,
description_text text not null,
exposition_id int not null,
language_id int not null,
FOREIGN KEY (language_id) references language (id)
);

create table exposition(
id int not null primary key auto_increment,
name varchar(300) not null,
start_date date,
end_date date,
price int default 0,
language_id int default 0,
available boolean default true,
FOREIGN KEY (language_id) references language (id)
);
ALTER TABLE description ADD CONSTRAINT exposition_id FOREIGN KEY (exposition_id) REFERENCES exposition(id);

create table hall(
id int not null primary key auto_increment,
name varchar(50) not null,
exposition_id int default 0
);

create table hall_exposition(
hall_id int,
exposition_id int,
FOREIGN KEY (hall_id) references hall (id),
FOREIGN KEY (exposition_id) references exposition (id)
);

create table ticket(
id int not null primary key auto_increment,
user_id int not null,
exposition_id int not null,
created datetime DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (exposition_id) references exposition (id),
FOREIGN KEY (user_id) references user (id)
);

insert into language (name) values ('english');
insert into language (name) values ('ukrainian');

insert into admin (name,email,password) values('Default Administrator','admin@admin.com','81DC9BDB52D04DC20036DBD8313ED055');
insert into user (name,email,password) values('Default User','user@user.com','1234');
INSERT INTO hall (name) VALUES ('The Great Hall'),('The Grey Hall'),('The Katato Hall');
INSERT INTO exposition (name,start_date,end_date,price,language_id) values 
	("GRISHIGIANO. Simplicity is the highest form of sophistication", "2021-08-08", "2021-09-12", 500, 1),
    ("EGENHÄNDIGT. Ceramics sculptures and scandi style", "2021-08-13", "2021-08-28", 450, 1),
	("National Institute of American Doll Artists. Art & dolls", "2021-08-25", "2021-09-15", 420, 1),
	("KRISTIINA HAATAJA. Ancient Cubism", "2021-08-25", "2021-09-25", 400, 1);
select * from admin;
select * from language;
select * from ticket;
select * from exposition;
select * from hall;
select * from user;
select * from admin where email = 'admin@admin.com' and password = '81DC9BDB52D04DC20036DBD8313ED055';

