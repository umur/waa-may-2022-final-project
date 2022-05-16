
INSERT INTO public.address (id, state, city, street, zip_code) VALUES (1, 'IOWA', 'Fairfield', '1000 N st', 52257);
INSERT INTO public.role (id, role) VALUES (1, 'admin');
INSERT INTO public.role (id, role) VALUES (2, 'tenant');
INSERT INTO public.role (id, role) VALUES (3, 'landlord');

INSERT INTO public.users (id, last_logged_in_at, active, deleted, email, first_name, lastname, password, role_id) VALUES (2, '2022-05-11', true, false, 'hassan@miu.edu', 'hassan', 'hassan', '123', 2);
INSERT INTO public.users (id, last_logged_in_at, active, deleted, email, first_name, lastname, password, role_id) VALUES (1, '2022-05-11', true, false, 'abdo@miu.edu', 'Abdelrahman', 'Freek', '123', 1);
INSERT INTO public.users (id, last_logged_in_at, active, deleted, email, first_name, lastname, password, role_id) VALUES (3, '2022-05-11', true, false, 'john@gmail.com', 'John', 'Mickel', '123', 3);



INSERT INTO public.property_type (id, type) VALUES (1, 'Appartment');
INSERT INTO public.property_type (id, type) VALUES (2, 'House');
/*
INSERT INTO public.property (id, deleted, is_occupied, number_of_bathrooms, number_of_bedrooms, property_name, rent_amount, security_deposit_amount, visible, address_id, last_rented_by, owned_by, property_type_id) VALUES (1, true, false, 2, 3, 'appartment with 3 bedrooms', 500, 0, true, 1, null, 3, 1);
INSERT INTO public.property (id, deleted, is_occupied, number_of_bathrooms, number_of_bedrooms, property_name, rent_amount, security_deposit_amount, visible, address_id, last_rented_by, owned_by, property_type_id) VALUES (4, false, false, 2, 3, 'appartment with 3 bedrooms', 500, 0, true, 1, null, 3, 1);
INSERT INTO public.property (id, deleted, is_occupied, number_of_bathrooms, number_of_bedrooms, property_name, rent_amount, security_deposit_amount, visible, address_id, last_rented_by, owned_by, property_type_id) VALUES (2, false, true, 3, 6, 'House with 3 bedrooms', 500, 0, true, 1, 2, 3, 2 );
INSERT INTO public.property (id, deleted, is_occupied, number_of_bathrooms, number_of_bedrooms, property_name, rent_amount, security_deposit_amount, visible, address_id, last_rented_by, owned_by, property_type_id) VALUES (3, false, true, 1, 3, 'appartment with 2 bedrooms', 500, 0, true, 1, 2, 3, 1);



INSERT INTO public.property_photos (id, photo_url, property_id) VALUES (1, 'https://cdn.apartmenttherapy.info/image/upload/f_auto,q_auto:eco/at%2Fhouse%20tours%2F2021-07%2FAdam%20K%2FApartment_Therapy_Adam_Kavalin_14', 1);
INSERT INTO public.property_photos (id, photo_url, property_id) VALUES (2, 'https://sevencapitals3bucket-6bb5.kxcdn.com/wp-content/uploads/2020/07/01150010/living-room-full2.jpg', 1);
INSERT INTO public.property_photos (id, photo_url, property_id) VALUES (3, 'https://wp-tid.zillowstatic.com/trulia/wp-content/uploads/sites/1/2015/11/Tiny-Studio-Massive-Style-11-23-HERO.jpg', 2);
INSERT INTO public.property_photos (id, photo_url, property_id) VALUES (4, 'https://design-milk.com/images/2020/08/Studio-Apartment-Rio-Cite-Arquitetura-4.jpg', 3);


INSERT INTO public.property_rent_history (serial, active, rented_amount, rented_from, rented_to, property_id, rented_by_id) VALUES (1, false, 25000, '2022-01-01', '2022-03-31', 1, 2);
INSERT INTO public.property_rent_history (serial, active, rented_amount, rented_from, rented_to, property_id, rented_by_id) VALUES (2, true, 5000, '2022-01-01', '2022-06-30', 2, 2);
INSERT INTO public.property_rent_history (serial, active, rented_amount, rented_from, rented_to, property_id, rented_by_id) VALUES (3, true, 1500, '2021-04-01', '2022-05-31', 3, 2);
*/