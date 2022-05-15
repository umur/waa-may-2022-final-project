truncate table users restart identity cascade;

INSERT INTO users (id, email, first_name, last_name, password, role) VALUES (nextval('user_id'), 'admin@miu.edu', 'Johnny', 'Depp', 'qwerty','ADMIN');
INSERT INTO users (id, email, first_name, last_name, password, role) VALUES (nextval('user_id'), 'sisikli@miu.edu', 'Serdar', 'Isikli', '123456','TENANT');
INSERT INTO users (id, email, first_name, last_name, password, role) VALUES (nextval('user_id'), 'admin@miu.edu', 'Lebap', 'Akmyradov', 'qwerty123','LANDLORD');
INSERT INTO users (id, email, first_name, last_name, password, role) VALUES (nextval('user_id'), 'admin@miu.edu', 'Melissa', 'Bill', 'qwerty','TENANT');

INSERT INTO property(id, city, is_occupied, number_of_bathrooms, number_of_bedrooms, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, owned_by_id, tenant_id)
VALUES (nextval('property_id'), 'Fairfield', false, 3, 2, 'Diamond', 'HOUSE', 1000, 500, 'IA', '480 W Adem', 52556, 3, 2);
INSERT INTO property(id, city, is_occupied, number_of_bathrooms, number_of_bedrooms, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, owned_by_id, tenant_id)
VALUES (nextval('property_id'), 'Tampa', false, 3, 2, 'Kingston', 'APARTMENT', 1000, 500, 'FL', '123 Madison', 43122, 3, 4);
INSERT INTO property(id, city, is_occupied, number_of_bathrooms, number_of_bedrooms, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, owned_by_id, tenant_id)
VALUES (nextval('property_id'), 'San Jose', false, 3, 2, 'Silence', 'HOUSE', 1000, 500, 'CA', '480 E Corner', 22121, 3, 1);



