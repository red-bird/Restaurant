create table goods (
                             id bigserial not null,
                             name varchar(63) not null,
                             price float8 not null,
                             quantity int4 not null,
                             client varchar(255) not null,
                             filename varchar(255),
                             order_id int8,
                             primary key (id)
);


alter table if exists goods
    add constraint good_order_fk
    foreign key (order_id) references orders;