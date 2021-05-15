create table goods (
                             id bigserial not null,
                             food_id int8 not null,
                             quantity int4 not null,
                             client varchar(255) not null,
                             primary key (id)
);

alter table if exists goods
    add constraint restaurants_food_fk
    foreign key (food_id) references foods;