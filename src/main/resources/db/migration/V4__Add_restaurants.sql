create table restaurants (
                       id bigserial not null,
                       address varchar(64) not null,
                       phone varchar(63) not null,
                       worktime varchar(64) not null,
                       description varchar(1023),
                       primary key (id)
);