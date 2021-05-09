create sequence hibernate_sequence start 2 increment 1;

create table foods (
                       id bigserial not null,
                       description varchar(1023),
                       filename varchar(255),
                       name varchar(63) not null,
                       price float8 not null,
                       type varchar(31),
                       primary key (id)
    );

create table orders (
                        id bigserial not null,
                        date varchar(63) not null,
                        client_id int8 not null,
                        primary key (id)
    );

create table orders_foods (
                              order_id bigserial not null,
                              food_id bigserial not null
);

create table user_role (
                           user_id int8 not null,
                           roles varchar(255) not null
    );

create table users (
                       id bigserial not null,
                       activation_code varchar(255),
                       active boolean not null,
                       email varchar(255) not null unique,
                       password varchar(255) not null,
                       username varchar(255) not null unique,
                       primary key (id)
    );

alter table if exists orders
    add constraint orders_users_fk
    foreign key (client_id) references users;

alter table if exists orders_foods
    add constraint ordersfood_food_fk
    foreign key (food_id) references foods;

alter table if exists orders_foods
    add constraint ordersfood_order_fk
    foreign key (order_id) references orders;

alter table if exists user_role
    add constraint userrole_user_fk
    foreign key (user_id) references users;