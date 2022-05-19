truncate table users restart identity cascade;
ALTER SEQUENCE user_id RESTART WITH 1;

INSERT INTO users (id, email, first_name, last_name, password, role, active, register_time) VALUES (nextval('user_id'), 'admin@miu.edu', 'Johnny', 'Depp', 'qwerty','ADMIN', true, '2022-05-17 12:31:40.000000');
INSERT INTO users (id, email, first_name, last_name, password, role, active, register_time) VALUES (nextval('user_id'), 'sisikli@miu.edu', 'Serdar', 'Isikli', '123456','TENANT', true, '2022-05-16 12:31:40.000000');
INSERT INTO users (id, email, first_name, last_name, password, role, active, register_time) VALUES (nextval('user_id'), 'admin@miu.edu', 'Lebap', 'Akmyradov', 'qwerty123','LANDLORD', true, '2022-05-15 12:31:40.000000');
INSERT INTO users (id, email, first_name, last_name, password, role, active, register_time) VALUES (nextval('user_id'), 'admin@miu.edu', 'Melissa', 'Bill', 'qwerty','TENANT', true, '2022-05-14 12:31:40.000000');

truncate table property restart identity cascade;
ALTER SEQUENCE property_id RESTART WITH 1;

INSERT INTO property(id, city, is_occupied, number_of_bathrooms, number_of_bedrooms, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, owned_by_id, tenant_id)
VALUES (nextval('property_id'), 'Fairfield', false, 3, 2, 'Diamond', 'HOUSE', 1000, 500, 'IA', '480 W Adem', 52556, 3, 2);
INSERT INTO property(id, city, is_occupied, number_of_bathrooms, number_of_bedrooms, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, owned_by_id, tenant_id)
VALUES (nextval('property_id'), 'Tampa', false, 3, 2, 'Kingston', 'APARTMENT', 1000, 500, 'FL', '123 Madison', 43122, 3, 4);
INSERT INTO property(id, city, is_occupied, number_of_bathrooms, number_of_bedrooms, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, owned_by_id, tenant_id)
VALUES (nextval('property_id'), 'San Jose', false, 3, 2, 'Silence', 'HOUSE', 1000, 500, 'CA', '480 E Corner', 22121, 3, 1);

truncate table rental_activity restart identity cascade;
ALTER SEQUENCE rentalactivity_id RESTART WITH 1;

INSERT INTO rental_activity(id, end_date, start_date, property_id, rented_by, tenant_id)
VALUES (nextval('rentalactivity_id'), '2022-05-25', '2022-05-10' , 1, 2, 1);
INSERT INTO rental_activity(id, end_date, start_date, property_id, rented_by, tenant_id)
VALUES (nextval('rentalactivity_id'), '2022-05-25', '2022-05-10' , 2, 4, 3);


