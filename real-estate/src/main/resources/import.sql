drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1000 increment by 1;

-- Contraseña: Admin1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at)
values (1, 'Admin admin', 'admin@openwebinars.net','admin','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','https://api.adorable.io/avatars/285/admin@openwebinars.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into user_entity_roles (user_entity_id, roles) values (1,'USER');
insert into user_entity_roles (user_entity_id, roles) values (1,'ADMIN');

-- Contraseña: Marialopez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at)
values (2, 'María López', 'maria.lopez@openwebinars.net','marialopez','$2a$10$ev.rv6yUA.UE9.Ndw4aSC.wRo6UlP6OkjAe48SmEN.elw4WAyfT0S','https://api.adorable.io/avatars/285/maria.lopez@openwebinars.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into user_entity_roles (user_entity_id, roles) values (2,'USER');