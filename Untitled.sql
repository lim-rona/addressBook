drop schema if exists contactBook;

create schema contactBook;

use contactBook;

create table user(
    user_id int not null auto_increment,
    name varchar(256),
    email varchar(256),
    mobile varchar(256),

    primary key(user_id)
);


