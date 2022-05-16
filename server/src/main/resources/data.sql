--  password is 123456
INSERT INTO roles (id, created_at, is_deleted, updated_at, role_name) VALUES ('a22fcad6-a812-4d9d-b436-fbf5cc366b66', '2022-05-10 15:45:29.000000', false, '2022-05-10 15:45:37.000000', 'ROLE_ADMIN');
INSERT INTO roles (id, created_at, is_deleted, updated_at, role_name) VALUES ('0dae5989-1119-440c-bc0e-301d05401438', '2022-05-10 15:47:31.000000', false, '2022-05-10 15:47:27.000000', 'ROLE_TENANT');
INSERT INTO roles (id, created_at, is_deleted, updated_at, role_name) VALUES ('9dcfb3c6-9878-445c-984a-a94e75727efb', '2022-05-10 15:47:32.000000', false, '2022-05-10 15:47:29.000000', 'ROLE_LANDLORD');

INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('dcb07cb3-5770-44ec-8e46-ae63e1719a2f', '2022-05-10 15:55:35.000000', false, '2022-05-10 15:55:40.000000', true, 'prajapati.puru@gmail.com', 'puru', 'prajapati', '123456', 'a22fcad6-a812-4d9d-b436-fbf5cc366b66', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('1411c449-db49-40e8-bf3d-d4ac14d231a3', '2022-05-10 15:57:20.000000', false, '2022-05-10 15:57:42.000000', true, 'kkant@miu.edu', 'sai', 'kant', '123456', '0dae5989-1119-440c-bc0e-301d05401438', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('3c8c17ff-ea23-45f2-8f66-e411c972ee4c', '2022-05-10 15:57:28.000000', false, '2022-05-10 15:57:46.000000', true, 'mthuong@miu.edu', 'manh', 'thuong', '123456', '0dae5989-1119-440c-bc0e-301d05401438', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('655cb8f5-80c9-43af-830c-f8b309d9e508', '2022-05-10 15:57:29.000000', false, '2022-05-10 15:57:44.000000', true, 'pprajapati@miu.edu', 'renter', 'renter', '123456', '9dcfb3c6-9878-445c-984a-a94e75727efb', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('c01d88e8-d4bb-11ec-9d64-0242ac120002', '2022-05-11 15:57:20.000000', false, '2022-05-10 15:57:42.000000', true, 'a@miu.edu', 'a', 'kant', '123456', '0dae5989-1119-440c-bc0e-301d05401438', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('cbd02e7d-d71f-49cb-8180-8b12bacf2c51', '2022-05-12 15:57:20.000000', false, '2022-05-10 15:57:42.000000', true, 'b@miu.edu', 'b', 'kant', '123456', '0dae5989-1119-440c-bc0e-301d05401438', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('e48f8bc9-a6b1-4a44-af2f-ab5a4ed18865', '2022-05-12 15:57:20.000000', false, '2022-05-10 15:57:42.000000', true, 'c@miu.edu', 'c', 'kant', '123456', '0dae5989-1119-440c-bc0e-301d05401438', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('4cd7c604-5367-4844-9fae-fbc33f3ef908', '2022-05-12 15:57:20.000000', false, '2022-05-10 15:57:42.000000', true, 'd@miu.edu', 'd', 'kant', '123456', '0dae5989-1119-440c-bc0e-301d05401438', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('ef5a3979-4d84-48b0-99de-06a58ac438e5', '2022-05-13 15:57:20.000000', false, '2022-05-10 15:57:42.000000', true, 'e@miu.edu', 'e', 'kant', '123456', '0dae5989-1119-440c-bc0e-301d05401438', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('94ffc748-008e-4e9c-a109-084cbbe8b773', '2022-05-13 15:57:20.000000', false, '2022-05-10 15:57:42.000000', true, 'f@miu.edu', 'f', 'kant', '123456', '0dae5989-1119-440c-bc0e-301d05401438', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('a3b28ebc-fabe-49cf-a440-790ebb496106', '2022-05-14 15:57:20.000000', false, '2022-05-10 15:57:42.000000', true, 'g@miu.edu', 'g', 'kant', '123456', '0dae5989-1119-440c-bc0e-301d05401438', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('ce864a7f-c56a-4bb0-b51b-61f289873f73', '2022-05-07 15:57:20.000000', false, '2022-05-10 15:57:42.000000', true, 'h@miu.edu', 'h', 'kant', '123456', '0dae5989-1119-440c-bc0e-301d05401438', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('2154dc70-c63f-4715-ad8c-326149e0b895', '2022-05-08 15:57:20.000000', false, '2022-05-10 15:57:42.000000', true, 'i@miu.edu', 'i', 'kant', '123456', '0dae5989-1119-440c-bc0e-301d05401438', 'MALE');
INSERT INTO users (id, created_at, is_deleted, updated_at, active, email, first_name, last_name, password, role_id, gender) VALUES ('4443c16a-f61d-44cb-a065-4ca642ec5350', '2022-05-09 15:57:20.000000', false, '2022-05-10 15:57:42.000000', true, 'j@miu.edu', 'j', 'kant', '123456', '0dae5989-1119-440c-bc0e-301d05401438', 'MALE');








INSERT INTO properties (id, created_at, is_deleted, updated_at, city, description, is_occupied, no_of_bathroom, no_of_bedroom, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, last_rented_by_id, owned_by_id, property_rental_history_id) VALUES ('86220b02-5e6e-40a9-a83d-248f1b9b56f7', '2022-05-10 22:58:01.000000', false, '2022-05-10 22:58:29.000000', 'Fairfield', 'Lorem ipsum, in graphical and textual context, refers to filler text that is placed in a document or visual presentation. Lorem ipsum is derived from the Latin "dolorem ipsum" roughly translated as "pain itself."', false, 2, 2, 'MudHolland 1', 'Flat', 100, 10, 'IA', '1000 North 4th Street', 52557, null, '655cb8f5-80c9-43af-830c-f8b309d9e508', null);
INSERT INTO properties (id, created_at, is_deleted, updated_at, city, description, is_occupied, no_of_bathroom, no_of_bedroom, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, last_rented_by_id, owned_by_id, property_rental_history_id) VALUES ('0aebe6a7-074e-4d3d-b2c4-200c8e71af2d', '2022-05-10 22:58:03.000000', false, '2022-05-10 22:58:32.000000', 'Fairfield', 'Lorem ipsum, in graphical and textual context, refers to filler text that is placed in a document or visual presentation. Lorem ipsum is derived from the Latin "dolorem ipsum" roughly translated as "pain itself."', false, 2, 2, 'MudHolland 1', 'Flat', 100, 10, 'IA', '1000 North 4th Street', 52557, null, '655cb8f5-80c9-43af-830c-f8b309d9e508', null);
INSERT INTO properties (id, created_at, is_deleted, updated_at, city, description, is_occupied, no_of_bathroom, no_of_bedroom, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, last_rented_by_id, owned_by_id, property_rental_history_id) VALUES ('e007be47-2754-4bd3-b09b-cdff7aae5b4d', '2022-05-10 22:58:17.000000', false, '2022-05-10 22:58:33.000000', 'Fairfield', 'Lorem ipsum, in graphical and textual context, refers to filler text that is placed in a document or visual presentation. Lorem ipsum is derived from the Latin "dolorem ipsum" roughly translated as "pain itself."', false, 2, 2, 'MudHolland 1', 'Flat', 100, 10, 'IA', '1000 North 4th Street', 52557, null, null, null);
INSERT INTO properties (id, created_at, is_deleted, updated_at, city, description, is_occupied, no_of_bathroom, no_of_bedroom, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, last_rented_by_id, owned_by_id, property_rental_history_id) VALUES ('93d6e010-40cb-4b07-834d-a90b4adabbc3', '2022-05-10 22:58:19.000000', false, '2022-05-10 22:58:33.000000', 'Fairfield', 'Lorem ipsum, in graphical and textual context, refers to filler text that is placed in a document or visual presentation. Lorem ipsum is derived from the Latin "dolorem ipsum" roughly translated as "pain itself."', false, 2, 2, 'MudHolland 1', 'Flat', 100, 10, 'IA', '1000 North 4th Street', 52557, null, null, null);
INSERT INTO properties (id, created_at, is_deleted, updated_at, city, description, is_occupied, no_of_bathroom, no_of_bedroom, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, last_rented_by_id, owned_by_id, property_rental_history_id) VALUES ('35b51d96-c5b3-413a-ac16-533d37ecdad8', '2022-05-10 22:58:20.000000', false, '2022-05-10 22:58:34.000000', 'Fairfield', 'Lorem ipsum, in graphical and textual context, refers to filler text that is placed in a document or visual presentation. Lorem ipsum is derived from the Latin "dolorem ipsum" roughly translated as "pain itself."', false, 2, 2, 'MudHolland 1', 'Flat', 100, 10, 'IA', '1000 North 4th Street', 52557, null, null, null);
INSERT INTO properties (id, created_at, is_deleted, updated_at, city, description, is_occupied, no_of_bathroom, no_of_bedroom, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, last_rented_by_id, owned_by_id, property_rental_history_id) VALUES ('1d9a6f66-f286-4206-92ea-d4e5ee125e22', '2022-05-10 22:58:21.000000', false, '2022-05-10 22:58:35.000000', 'Fairfield', 'Lorem ipsum, in graphical and textual context, refers to filler text that is placed in a document or visual presentation. Lorem ipsum is derived from the Latin "dolorem ipsum" roughly translated as "pain itself."', false, 2, 2, 'MudHolland 1', 'Flat', 100, 10, 'IA', '1000 North 4th Street', 52557, null, null, null);
INSERT INTO properties (id, created_at, is_deleted, updated_at, city, description, is_occupied, no_of_bathroom, no_of_bedroom, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, last_rented_by_id, owned_by_id, property_rental_history_id) VALUES ('ea74f11e-f082-48ef-9e3f-a3a4d79947e3', '2022-05-10 22:58:21.000000', false, '2022-05-10 22:58:36.000000', 'Fairfield', 'Lorem ipsum, in graphical and textual context, refers to filler text that is placed in a document or visual presentation. Lorem ipsum is derived from the Latin "dolorem ipsum" roughly translated as "pain itself."', false, 2, 2, 'MudHolland 1', 'Flat', 100, 10, 'IA', '1000 North 4th Street', 52557, null, null, null);
INSERT INTO properties (id, created_at, is_deleted, updated_at, city, description, is_occupied, no_of_bathroom, no_of_bedroom, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, last_rented_by_id, owned_by_id, property_rental_history_id) VALUES ('78c53366-e9d2-4e67-8d41-8c2f2b2082ba', '2022-05-10 22:58:22.000000', false, '2022-05-10 22:58:36.000000', 'Fairfield', 'Lorem ipsum, in graphical and textual context, refers to filler text that is placed in a document or visual presentation. Lorem ipsum is derived from the Latin "dolorem ipsum" roughly translated as "pain itself."', false, 2, 2, 'MudHolland 1', 'Flat', 100, 10, 'IA', '1000 North 4th Street', 52557, null, null, null);
INSERT INTO properties (id, created_at, is_deleted, updated_at, city, description, is_occupied, no_of_bathroom, no_of_bedroom, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, last_rented_by_id, owned_by_id, property_rental_history_id) VALUES ('0f1178e3-f5c3-482c-b02f-910011b43c53', '2022-05-10 22:58:23.000000', false, '2022-05-10 22:58:37.000000', 'Fairfield', 'Lorem ipsum, in graphical and textual context, refers to filler text that is placed in a document or visual presentation. Lorem ipsum is derived from the Latin "dolorem ipsum" roughly translated as "pain itself."', false, 2, 2, 'MudHolland 1', 'Flat', 100, 10, 'IA', '1000 North 4th Street', 52557, null, null, null);
INSERT INTO properties (id, created_at, is_deleted, updated_at, city, description, is_occupied, no_of_bathroom, no_of_bedroom, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, last_rented_by_id, owned_by_id, property_rental_history_id) VALUES ('88e6bb29-6863-46bd-935e-9e6429ddd1ce', '2022-05-10 22:58:24.000000', false, '2022-05-10 22:58:40.000000', 'Fairfield', 'Lorem ipsum, in graphical and textual context, refers to filler text that is placed in a document or visual presentation. Lorem ipsum is derived from the Latin "dolorem ipsum" roughly translated as "pain itself."', false, 2, 2, 'MudHolland 1', 'Flat', 100, 10, 'IA', '1000 North 4th Street', 52557, null, null, null);
INSERT INTO properties (id, created_at, is_deleted, updated_at, city, description, is_occupied, no_of_bathroom, no_of_bedroom, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, last_rented_by_id, owned_by_id, property_rental_history_id) VALUES ('3027b9df-a3a6-4658-a470-2428a0bcc19b', '2022-05-10 22:58:26.000000', false, '2022-05-10 22:58:38.000000', 'Fairfield', 'Lorem ipsum, in graphical and textual context, refers to filler text that is placed in a document or visual presentation. Lorem ipsum is derived from the Latin "dolorem ipsum" roughly translated as "pain itself."', false, 2, 2, 'MudHolland 1', 'Flat', 100, 10, 'IA', '1000 North 4th Street', 52557, null, null, null);
INSERT INTO properties (id, created_at, is_deleted, updated_at, city, description, is_occupied, no_of_bathroom, no_of_bedroom, property_name, property_type, rent_amount, security_deposit_amount, state, street_address, zip_code, last_rented_by_id, owned_by_id, property_rental_history_id) VALUES ('0c52fa5b-9ee9-4330-b32d-060f8203cdaa', '2022-05-10 22:58:27.000000', false, '2022-05-10 22:58:39.000000', 'Fairfield', 'Lorem ipsum, in graphical and textual context, refers to filler text that is placed in a document or visual presentation. Lorem ipsum is derived from the Latin "dolorem ipsum" roughly translated as "pain itself."', false, 2, 2, 'MudHolland 1', 'Flat', 100, 10, 'IA', '1000 North 4th Street', 52557, null, null, null);

INSERT INTO property_rental_history (id, created_at, is_deleted, updated_at, end_date, start_date, property_id, rented_by, transaction_id) VALUES ('7e04c486-ee17-4fe5-ad2e-19f0b73d063e', '2022-05-15 01:40:40.000000', false, '2022-05-15 01:39:51.000000', '2022-05-20', '2022-05-18', '0c52fa5b-9ee9-4330-b32d-060f8203cdaa', '3c8c17ff-ea23-45f2-8f66-e411c972ee4c', null);
