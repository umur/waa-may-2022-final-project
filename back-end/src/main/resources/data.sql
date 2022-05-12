truncate table users restart identity cascade;

INSERT INTO users (id, email, first_name, last_name, password, role)
VALUES (nextval('users_id_seq'), 'admin@miu.edu', 'admin', 'inan', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','ADMIN'); --123
