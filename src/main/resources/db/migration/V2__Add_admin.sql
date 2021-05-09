insert into users (id, username, password, active, email)
    values (0, 'admin', 'admin', true, 'admin@mail.com');

insert into user_role (user_id, roles)
    values (0, 'ADMIN');