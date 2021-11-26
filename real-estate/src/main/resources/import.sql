--drop sequence if exists hibernate_sequence;
--create sequence hibernate_sequence start with 1000 increment by 1;

-- Contraseña: Admin1
insert into users (id, full_name, email, password, avatar, created_at, last_password_change_at, role) values (uuid(), 'Admin admin', 'admin@openwebinars.net','$2a$12$cNocjmlKpOA/iMQZAq5bAeV/zrTepR1xr2pKi6lIRBEiAqrV2/6Ya','https://api.adorable.io/avatars/285/admin@openwebinars.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'ADMIN');

--Contraseña: MariGomez1
insert into users (id, full_name, email, password, avatar, created_at, last_password_change_at, role) values (uuid(), 'Mari Gomez', 'manoligomez@openwebinars.net','$2a$12$pv1sBMIJLEl4ZBXxu3MtaOPQO9QcHffarpdS0TQEjKk07TPVbCbAW','https://api.adorable.io/avatars/285/admin@openwebinars.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'PROPIETARIO');

--Contraseña: JoseManuelGomez1
insert into users (id, full_name, email, password, avatar, created_at, last_password_change_at, role) values (uuid(), 'Jose Manuel Gomez', 'josemanuelgomez@openwebinars.net','$2a$12$ysLQIfeA213mwizK1TcjJeqIdP8QghPiGZMxROd6WZBcdrp48EwF2','https://api.adorable.io/avatars/285/admin@openwebinars.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'PROPIETARIO');


--Contraseña: LuismiLopez1
insert into users (id, full_name, email, password, avatar, created_at, last_password_change_at, role) values (uuid(), 'Luismi López', 'luismilopez@openwebinars.net','$2a$12$dOmMyKGcAr/8aFn8XUMq1uh1S6ivW2Q0G1TgYlBVQpP5hbIeyGd22','https://api.adorable.io/avatars/285/admin@openwebinars.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'GESTOR');

insert into vivienda (id, titulo, descripcion, avatar, lating, direccion, codigoPostal, poblacion, provincia, tipo, precio, numHabitaciones, metrosCuadrados, numBanios, tienePiscina, tieneAscensor, tieneGaraje, users ) values (4, 'Casa1', 'Una casa cualquiera', 'avatar.com.org', '45,34', 'Calle calle1', '41010', 'Sevilla', 'Sevilla', 'casa', 30000000.00, 5, 70, 2, true, true, true, uuid('ce1710cfa8d54727b35a59bbf3bf436c') );

-- Contraseña: Marialopez1
--insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at)
--values (2, 'María López', 'maria.lopez@openwebinars.net','marialopez','$2a$10$ev.rv6yUA.UE9.Ndw4aSC.wRo6UlP6OkjAe48SmEN.elw4WAyfT0S','https://api.adorable.io/avatars/285/maria.lopez@openwebinars.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--insert into user_entity_roles (user_entity_id, roles) values (2,'USER');