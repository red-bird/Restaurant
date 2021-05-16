create table goods (
                             id bigserial not null,
                             food_id int8 not null,
                             quantity int4 not null,
                             client varchar(255) not null,
                             order_id int8,
                             primary key (id)
);

alter table if exists goods
    add constraint good_food_fk
    foreign key (food_id) references foods;

alter table if exists goods
    add constraint good_order_fk
    foreign key (order_id) references orders;