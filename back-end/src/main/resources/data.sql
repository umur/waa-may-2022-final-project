truncate table users restart identity cascade;

INSERT INTO users (id, email, first_name, last_name, password, role)
VALUES (nextval('users_id_seq'), 'admin@miu.edu', 'admin', 'inan', '$2a$10$FNCwKqpG3aseWEevoFkv4Og0iPfZH6gYqNxdOnoCWBsqLmFQKAtny','ADMIN'); --123
