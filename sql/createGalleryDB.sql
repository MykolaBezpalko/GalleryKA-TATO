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
available boolean default true
);
ALTER TABLE description ADD CONSTRAINT exposition_id FOREIGN KEY (exposition_id) REFERENCES exposition(id) on delete cascade;

create table hall(
id int not null primary key auto_increment,
name varchar(50) not null
);

create table hall_exposition(
hall_id int,
exposition_id int,
FOREIGN KEY (hall_id) references hall (id),
FOREIGN KEY (exposition_id) references exposition (id) on delete cascade
);

create table ticket(
id int not null primary key auto_increment,
user_id int not null,
exposition_id int not null,
created datetime DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (exposition_id) references exposition (id) on delete cascade,
FOREIGN KEY (user_id) references user (id) on delete cascade
);

insert into language (name) values ('english');
insert into language (name) values ('ukrainian');

insert into admin (name,email,password) values('Default Administrator','admin@admin.com','81DC9BDB52D04DC20036DBD8313ED055');
insert into user (name,email,password) values('Default User','user@user.com','81DC9BDB52D04DC20036DBD8313ED055');
INSERT INTO hall (name) VALUES ('The Great Hall'),('The Grey Hall'),('The Katato Hall');
INSERT INTO exposition (name,start_date,end_date,price) values 
	("GRISHIGIANO. Simplicity is the highest form of sophistication", "2021-06-02", "2021-06-12", 500),
    ("EGENHÄNDIGT. Ceramics sculptures and scandi style", "2021-06-13", "2021-06-20", 450),
	("National Institute of American Doll Artists. Art & dolls", "2021-06-02", "2021-06-10", 420),
	("KRISTIINA HAATAJA. Ancient Cubism", "2021-06-02", "2021-06-10", 400);
INSERT INTO hall_exposition (hall_id, exposition_id) values (1,1),(1,2),(2,3),(3,4);
use gallery;
select * from admin;
select * from language;
select * from ticket;
select * from exposition;
select * from hall;
select * from user;
select * from description;

select * from hall_exposition;

select distinct * from hall
right outer join hall_exposition on hall.id = hall_id
right outer join exposition  on hall_exposition.exposition_id = exposition.id;

select distinct * from hall
right outer join hall_exposition on hall.id = hall_id
right outer join exposition  on hall_exposition.exposition_id = exposition.id
cross join description on exposition.id = description.exposition_id;
