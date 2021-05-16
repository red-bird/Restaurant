create table orders (
                       id bigserial not null,
                       client varchar(255) not null,
                       date varchar(63) not null,
                       primary key (id)
);