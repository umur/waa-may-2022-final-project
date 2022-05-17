
insert into public.role (id, createdat, isdeleted, updatedat, role)
values ('c998b6d0-15e2-44f0-b896-b62d7cf0f52c','1/1/2022',false,'1/1/2022','admin');

insert into public.role (id, createdat, isdeleted, updatedat, role)
values ('c998b6d0-25e2-44f0-b896-b62d7cf0f52c','1/1/2022',false,'1/1/2022','tenant');

insert into public.role (id, createdat, isdeleted, updatedat, role)
values ('c998b6d0-25e2-44f0-b896-b62d7cf0f53c','1/1/2022',false,'1/1/2022','landloard');


INSERT INTO public.address (id, createdat, isdeleted, updatedat, block, city, state, street, zipcode) VALUES ('83d9ce96-7fac-4881-8cee-22d16faca1d1', '2022-05-16 20:44:45.000000', false, '2022-05-16 20:45:01.000000', '3', 'Fairfield', 'IOWA', '3', 252557);
INSERT INTO public.address (id, createdat, isdeleted, updatedat, block, city, state, street, zipcode) VALUES ('83d9ce86-7fac-4881-8cee-22d16faca1d1', '2022-05-16 20:44:45.000000', false, '2022-05-16 20:45:01.000000', '5', 'Fairfield', 'IOWA', '3', 252557);
INSERT INTO public.address (id, createdat, isdeleted, updatedat, block, city, state, street, zipcode) VALUES ('d34ef98c-66dc-4df3-8cff-fbb37e34a02b', '2022-05-16 20:44:45.000000', false, '2022-05-16 20:45:01.000000', '3', 'Fairfield', 'IOWA', '3', 252557);
INSERT INTO public.address (id, createdat, isdeleted, updatedat, block, city, state, street, zipcode) VALUES ('97c46ff2-f58f-417b-ba37-eefa18d71263', '2022-05-16 20:44:45.000000', false, '2022-05-16 20:45:01.000000', '5', 'Fairfield', 'IOWA', '3', 252557);

INSERT INTO public.users (id, createdat, isdeleted, updatedat, firstname, lastname, password, phoneno, username, role_id) VALUES ('e3717009-4f26-44db-9e7c-95a3cc1a53dc', '2022-05-16 20:53:21.000000', false, '2022-05-16 20:53:25.000000', 'gfdfdffd', 'admin', 'admin', 'vfgfff', 'admin', 'c998b6d0-15e2-44f0-b896-b62d7cf0f52c');
INSERT INTO public.users (id, createdat, isdeleted, updatedat, firstname, lastname, password, phoneno, username, role_id) VALUES ('e3717009-4f26-44db-9e7c-95a3cc1a66dc', '2022-05-16 20:53:21.000000', false, '2022-05-16 20:53:25.000000', 'gfdfdffd', 'tenant', 'tenant', 'vfgfff', 'tenant', 'c998b6d0-25e2-44f0-b896-b62d7cf0f52c');
INSERT INTO public.users (id, createdat, isdeleted, updatedat, firstname, lastname, password, phoneno, username, role_id) VALUES ('c998b6d0-25e2-44f0-b896-b62d7cf0f53c', '2022-05-16 20:53:21.000000', false, '2022-05-16 20:53:25.000000', 'gfdfdffd', 'landloard', 'landloard', 'vfgfff', 'landloard', 'c998b6d0-25e2-44f0-b896-b62d7cf0f53c');

INSERT INTO public.landlord (id, createdat, isdeleted, updatedat, address_id, user_id) VALUES ('99b25696-506c-43f8-9990-49a650ae2e70', '2022-05-16 21:14:56.000000', false, '2022-05-16 21:15:03.000000', '97c46ff2-f58f-417b-ba37-eefa18d71263', 'c998b6d0-25e2-44f0-b896-b62d7cf0f53c');

INSERT INTO public.propertytype (id, createdat, isdeleted, updatedat, name) VALUES ('83d9ce86-7fac-4881-8cee-22d16faca1d1', '2022-05-16 21:09:04.000000', false, '2022-05-16 21:09:07.000000', 'Flat');


INSERT INTO public.property (id, createdat, isdeleted, updatedat, deposit, description, isoccupied, islisted, name, nobathrooms, nobedrooms, rent, address_id, lasttenant_id, owner_id, type_id) VALUES ('18faf1b1-ba41-4ecd-bf88-3bb245d92fb8', '2022-05-16 21:01:53.000000', false, '2022-05-16 21:01:54.000000', 1500, 'Three bedroom Apartment for Rent. $749.00/month + utilities, references, Available immediately. New laminated flooring throughout. Ground Floor No Smoking or Pets. Washer/Dryer hookup. 6 month lease minimum First month''s rent', false, true, 'T1', 2, 3, 1500, '83d9ce96-7fac-4881-8cee-22d16faca1d1', null, '99b25696-506c-43f8-9990-49a650ae2e70', '83d9ce86-7fac-4881-8cee-22d16faca1d1');
INSERT INTO public.property (id, createdat, isdeleted, updatedat, deposit, description, isoccupied, islisted, name, nobathrooms, nobedrooms, rent, address_id, lasttenant_id, owner_id, type_id) VALUES ('0e9e5460-0962-4996-bde0-c31d4e83fba4', '2022-05-16 21:01:53.000000', false, '2022-05-16 21:01:54.000000', 1500, 'Three bedroom Apartment for Rent. $749.00/month + utilities, references, Available immediately. New laminated flooring throughout. Ground Floor No Smoking or Pets. Washer/Dryer hookup. 6 month lease minimum First month''s rent', false, true, 'T1', 2, 3, 1500, '83d9ce86-7fac-4881-8cee-22d16faca1d1', null, '99b25696-506c-43f8-9990-49a650ae2e70', '83d9ce86-7fac-4881-8cee-22d16faca1d1');
INSERT INTO public.property (id, createdat, isdeleted, updatedat, deposit, description, isoccupied, islisted, name, nobathrooms, nobedrooms, rent, address_id, lasttenant_id, owner_id, type_id) VALUES ('4d2d010c-8c41-40d0-a3ca-fc560aa6b0e6', '2022-05-16 21:10:04.000000', false, '2022-05-16 21:10:14.000000', 1300, 'test', false, true, 'T3', 3, 2, 1200, 'd34ef98c-66dc-4df3-8cff-fbb37e34a02b', null, '99b25696-506c-43f8-9990-49a650ae2e70', '83d9ce86-7fac-4881-8cee-22d16faca1d1');

INSERT INTO public.propertypicture (id, createdat, isdeleted, updatedat, path, property_id) VALUES ('a9b430d7-ccfe-435e-ab0d-abbd84b50d94', '2022-05-16 21:52:21.000000', false, '2022-05-16 21:52:41.000000', 'https://www.apartments.com/images/default-source/default-album/apartment-kitchen.tmb-featuredim.jpg', '18faf1b1-ba41-4ecd-bf88-3bb245d92fb8');

