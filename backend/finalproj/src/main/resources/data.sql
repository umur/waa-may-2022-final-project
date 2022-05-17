INSERT INTO property_type(name) VALUES('Single-Family Homes'), ('Multifamily Homes'), ('Apartments'), ('Condominiums'), ('Tiny Home ');

INSERT  INTO users(firstname, lastname, email, password, role, active) VALUES ('John', 'Doe', 'admin@admin.com', '$2a$10$gTzdyHNlPK9G/PDPK.JskudKYmB/HGEb0nTrWVDTIsUQyDUvEsYSe', 'ADMIN', true);
INSERT  INTO users(firstname, lastname, email, password, role, active) VALUES ('Jane', 'Smith', 'tenant@tenant.com', '$2a$10$gTzdyHNlPK9G/PDPK.JskudKYmB/HGEb0nTrWVDTIsUQyDUvEsYSe', 'TENANT', true);
INSERT  INTO users(firstname, lastname, email, password, role, active) VALUES ('Justin', 'Bieber', 'landlord@landlord.com', '$2a$10$gTzdyHNlPK9G/PDPK.JskudKYmB/HGEb0nTrWVDTIsUQyDUvEsYSe', 'LANDLORD', true);