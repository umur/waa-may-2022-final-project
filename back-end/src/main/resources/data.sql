truncate table users restart identity cascade;

ALTER SEQUENCE users_id_seq RESTART WITH 1;

ALTER SEQUENCE address_id_seq RESTART WITH 1;

ALTER SEQUENCE property_id_seq RESTART WITH 1;

ALTER SEQUENCE photo_id_seq RESTART WITH 1;

ALTER SEQUENCE tenant_property_id_seq RESTART WITH 1;


INSERT INTO users (id, email, first_name, last_name, password, role,account_created_at)
VALUES (nextval('users_id_seq'), 'admin@miu.edu', 'admin', 'inan', '$2a$10$SMWsXTmkvaEvKBFUkezdGepl/9ZDBvVUTyKd4oJRYTwmVIZo80IT.','ADMIN','2022-02-01'); --123


INSERT INTO users (id, email, first_name, last_name, password, role,account_created_at)
VALUES (nextval('users_id_seq'), 'landlord@miu.edu', 'shree', 'krishna', '$2a$10$SMWsXTmkvaEvKBFUkezdGepl/9ZDBvVUTyKd4oJRYTwmVIZo80IT.','LANDLORD','2022-01-15'); --123

INSERT INTO users (id, email, first_name, last_name, password, role,account_created_at)
VALUES (nextval('users_id_seq'), 'ram@miu.edu', 'ram', 'krishna', '$2a$10$SMWsXTmkvaEvKBFUkezdGepl/9ZDBvVUTyKd4oJRYTwmVIZo80IT.','LANDLORD','2020-01-01'); --123




INSERT INTO users (id, email, first_name, last_name, password, role,account_created_at)
VALUES (nextval('users_id_seq'), 'hari@miu.edu', 'hari', 'krishna', '$2a$10$SMWsXTmkvaEvKBFUkezdGepl/9ZDBvVUTyKd4oJRYTwmVIZo80IT.','TENANT','2022-01-14'); --123


INSERT INTO users (id, email, first_name, last_name, password, role,account_created_at)
VALUES (nextval('users_id_seq'), 'ashok@miu.edu', 'ashok', 'sharma', '$2a$10$SMWsXTmkvaEvKBFUkezdGepl/9ZDBvVUTyKd4oJRYTwmVIZo80IT.','TENANT','2022-03-18'); --123


INSERT INTO users (id, email, first_name, last_name, password, role,account_created_at)
VALUES (nextval('users_id_seq'), 'chandan@miu.edu', 'chandan', 'karmacharya', '$2a$10$SMWsXTmkvaEvKBFUkezdGepl/9ZDBvVUTyKd4oJRYTwmVIZo80IT.','TENANT','2022-05-01'); --123

INSERT INTO users (id, email, first_name, last_name, password, role,account_created_at)
VALUES (nextval('users_id_seq'), 'sagar@miu.edu', 'Sagar', 'Nepali', '$2a$10$SMWsXTmkvaEvKBFUkezdGepl/9ZDBvVUTyKd4oJRYTwmVIZo80IT.','TENANT','2022-04-01'); --123


truncate table address restart identity cascade ;

INSERT INTO address(id,city,state,street,zipcode) values (nextval('address_id_seq'),'FairField','IOWA','1000 N 4TH','52557');

INSERT INTO address(id,city,state,street,zipcode) values (nextval('address_id_seq'),'Ottumwa','IOWA','1000 N 4TH','52257');

INSERT INTO address(id,city,state,street,zipcode) values (nextval('address_id_seq'),'Akron','OHIO','1000 N 4TH','52657');

INSERT INTO address(id,city,state,street,zipcode) values (nextval('address_id_seq'),'Cleveland','OHIO','1000 N 4TH','52597');

truncate table property restart identity cascade;

INSERT INTO property(id, no_of_bath_room,no_of_bed_room,property_name,property_type,rent_amount,security_deposit_amount,land_lord_id,address_id) values
    (nextval('property_id_seq'),2,3,'Housing Royal','House',5000,500,2,1);

INSERT INTO property(id, no_of_bath_room,no_of_bed_room,property_name,property_type,rent_amount,security_deposit_amount,land_lord_id,address_id) values
    (nextval('property_id_seq'),4,7,'Royal House','House',2000,100,2,2);


INSERT INTO property(id, no_of_bath_room,no_of_bed_room,property_name,property_type,rent_amount,security_deposit_amount,land_lord_id,address_id) values
    (nextval('property_id_seq'),4,7,'Moto House','House',4000,100,3,3);

INSERT INTO property(id, no_of_bath_room,no_of_bed_room,property_name,property_type,rent_amount,security_deposit_amount,land_lord_id,address_id) values
    (nextval('property_id_seq'),2,5,'Super House','House',5000,300,2,4);

truncate table photo restart identity cascade ;

INSERT INTO photo(id,is_deleted,key_name,url,property_id) values (nextval('photo_id_seq'),false,'2yjamkqutym\files','https://property-management-system.s3.amazonaws.com/2yjamkqutym%5Cfiles',1);

truncate table property_rent restart identity cascade;

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id, created_at)

values (nextval('tenant_property_id_seq'),6000,'2022-06-10',1,4,'2022-05-09');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),8000,'2020-10-10',1,4,'2020-09-09');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),6000,'2020-05-10',2,5,'2020-04-10');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),8000,'2022-05-30',2,5,'2022-05-09');


INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),6000,'2020-05-10',3,6,'2020-04-10');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),8000,'2020-10-10',3,6,'2020-10-10');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),6000,'2022-07-10',4,7,'2022-05-10');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),8000,'2020-10-10',4,7,'2020-09-10');



INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),3000,'2020-11-10',1,5,'2020-10-10');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),6000,'2021-05-10',1,5,'2021-04-10');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),3000,'2020-11-10',2,4,'2020-10-10');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),6000,'2021-05-10',2,4,'2021-04-10');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),3300,'2020-11-10',3,7,'2020-10-10');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),5500,'2021-05-10',3,7,'2021-04-10');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),2500,'2020-11-10',4,6,'2020-10-10');

INSERT INTO property_rent(id,paid_rent_amount,rent_end_date,property_id,tenant_id,created_at)

values (nextval('tenant_property_id_seq'),6100,'2021-05-10',4,6,'2021-04-10');