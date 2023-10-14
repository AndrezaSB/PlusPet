
--INSERT EMPLOYEE/USER

INSERT INTO employee (id, name, login, password, role, crmv) VALUES ('60686451-0f0f-4565-81ed-b8a13b7436b1', 'Viviane Amarante', 'drvivi', '$2a$12$6Grk9DCwVGLLxl./rLltm.3bwQZ8lzDzXQZ1/S9GO/BoQQPmJr6xy', 'VETERINARIAN', 'CE-28891');
INSERT INTO employee (id, name, login, password, role) VALUES ('60686451-0f0f-4565-81ed-b8a13b7436b2', 'Emilly Carvalho', 'em_car', '$2a$12$6Grk9DCwVGLLxl./rLltm.3bwQZ8lzDzXQZ1/S9GO/BoQQPmJr6xy', 'ATTENDANT');
INSERT INTO employee (id, name, login, password, role) VALUES ('60686451-0f0f-4565-81ed-b8a13b7436b3', 'Joaquim Vieira', 'joaquim', '$2a$12$6Grk9DCwVGLLxl./rLltm.3bwQZ8lzDzXQZ1/S9GO/BoQQPmJr6xy', 'PET_CARE');
INSERT INTO employee (id, name, login, password, role) VALUES ('60686451-0f0f-4565-81ed-b8a13b7436b4', 'Tiana Frog', 'tiana_f', '$2a$12$6Grk9DCwVGLLxl./rLltm.3bwQZ8lzDzXQZ1/S9GO/BoQQPmJr6xy', 'ADMIN');
	
--INSERT TUTOR
INSERT INTO tutor (id, birth_date, cpf, email, name, archived) VALUES ('1795e15f-df02-4500-9241-18f9b8ff8b71', '1980-04-07', '11111111111', 'joaquim@test.com', 'Joaquim', false);
INSERT INTO tutor (id, birth_date, cpf, email, name, archived) VALUES ('1795e15f-df02-4500-9241-18f9b8ff8b72', '2000-05-06', '22222222222', 'amelia@test.com', 'Amelia', false);
INSERT INTO tutor (id, birth_date, cpf, email, name, archived) VALUES ('1795e15f-df02-4500-9241-18f9b8ff8b73', '1999-08-01', '33333333333', 'tony@test.com', 'Tony', false);
INSERT INTO tutor (id, birth_date, cpf, email, name, archived) VALUES ('1795e15f-df02-4500-9241-18f9b8ff8b74', '1998-09-02', '44444444444', 'kelly@test.com', 'Kelly', true);
INSERT INTO tutor (id, birth_date, cpf, email, name, archived) VALUES ('1795e15f-df02-4500-9241-18f9b8ff8b75', '2002-03-03', '55555555555', 'maros@test.com', 'Marcos', false);
INSERT INTO tutor (id, birth_date, cpf, email, name, archived) VALUES ('1795e15f-df02-4500-9241-18f9b8ff8b76', '2000-01-04', '66666666666', 'caroline@test.com', 'Caroline', false);
INSERT INTO tutor (id, birth_date, cpf, email, name, archived) VALUES ('1795e15f-df02-4500-9241-18f9b8ff8b77', '1995-05-09', '77777777777', 'emilia@test.com', 'Emilia', false);
INSERT INTO tutor (id, birth_date, cpf, email, name, archived) VALUES ('1795e15f-df02-4500-9241-18f9b8ff8b78', '1987-07-08', '88888888888', 'jana@test.com', 'Jana', false);
INSERT INTO tutor (id, birth_date, cpf, email, name, archived) VALUES ('1795e15f-df02-4500-9241-18f9b8ff8b79', '1990-09-04', '99999999999', 'mario@test.com', 'Mario', false);
INSERT INTO tutor (id, birth_date, cpf, email, name, archived) VALUES ('1795e15f-df02-4500-9241-18f9b8ff8b81', '1983-03-05', '11111199999', 'apolo@test.com', 'Apolo', true);
INSERT INTO tutor (id, birth_date, cpf, email, name, archived) VALUES ('1795e15f-df02-4500-9241-18f9b8ff8b82', '1982-04-02', '22222288888', 'maria@test.com', 'Maria', false);

--INSERT ADDRESS
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded61', 'Teste', '61111111', 'Plus Pet City', 'PlusPet', '302', 'PlusPet Street', '1795e15f-df02-4500-9241-18f9b8ff8b71');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded62', null, '61111112', 'Plus Pet City', 'PlusPet', '303', 'PlusPet Street', '1795e15f-df02-4500-9241-18f9b8ff8b72');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded63', 'Teste 1', '61111113', 'Plus Pet City', 'PlusPet', '304', 'PlusPet Street', '1795e15f-df02-4500-9241-18f9b8ff8b72');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded64', null, '61111114', 'Plus Pet City', 'PlusPet', '305', 'PlusPet Street', '1795e15f-df02-4500-9241-18f9b8ff8b73');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded65', 'Teste 2', '61111115', 'Plus Pet City', 'PlusPet', '306', 'PlusPet Street', '1795e15f-df02-4500-9241-18f9b8ff8b71');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded66', 'Teste 3', '61111116', 'Plus Pet City', 'PlusPet', '307', 'PlusPet Street', '1795e15f-df02-4500-9241-18f9b8ff8b73');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded67', 'Teste 4', '61111117', 'Plus Pet City', 'PlusPet', '308', 'PlusPet Street', '1795e15f-df02-4500-9241-18f9b8ff8b74');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded68', null, '61111118', 'Plus Pet City', 'PlusPet', '309', 'PlusPet Street', '1795e15f-df02-4500-9241-18f9b8ff8b75');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded69', 'Teste 5', '61111119', 'Plus Pet City', 'PlusPet', '310', 'PlusPet Street', '1795e15f-df02-4500-9241-18f9b8ff8b76');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded71', null, '62222221', 'Test City', 'Test', '761', 'Test Street', '1795e15f-df02-4500-9241-18f9b8ff8b77');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded72', 'Teste 6', '62222222', 'Test City', 'Test', '762', 'Test Street', '1795e15f-df02-4500-9241-18f9b8ff8b78');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded73', null, '62222223', 'Test City', 'Test', '763', 'Test Street', '1795e15f-df02-4500-9241-18f9b8ff8b79');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded74', 'Teste 7', '62222224', 'Test City', 'Test', '764', 'Test Street', '1795e15f-df02-4500-9241-18f9b8ff8b81');
INSERT INTO address (id, additional_information, cep, city, district, number, street_name, tutor) VALUES ('f56cb17f-815b-4b97-bd55-f519355ded75', 'Teste 8', '62222225', 'Test City', 'Test', '765', 'Test Street', '1795e15f-df02-4500-9241-18f9b8ff8b82');

--INSERT TELEPHONE
INSERT INTO telephone (id, description, phone_number tutor) VALUES ('5ee31892-7df1-40af-bf6f-17eff01c4471', 'Test 1', '85111111101', '1795e15f-df02-4500-9241-18f9b8ff8b71');
INSERT INTO telephone (id, description, phone_number tutor) VALUES ('5ee31892-7df1-40af-bf6f-17eff01c4472', 'Test 2', '85111111112', '1795e15f-df02-4500-9241-18f9b8ff8b72');
INSERT INTO telephone (id, description, phone_number tutor) VALUES ('5ee31892-7df1-40af-bf6f-17eff01c4473', 'Test 3', '85111111113', '1795e15f-df02-4500-9241-18f9b8ff8b73');
INSERT INTO telephone (id, description, phone_number tutor) VALUES ('5ee31892-7df1-40af-bf6f-17eff01c4474', 'Test 4', '85111111114', '1795e15f-df02-4500-9241-18f9b8ff8b74');
INSERT INTO telephone (id, description, phone_number tutor) VALUES ('5ee31892-7df1-40af-bf6f-17eff01c4475', 'Test 5', '85111111115', '1795e15f-df02-4500-9241-18f9b8ff8b75');
INSERT INTO telephone (id, description, phone_number tutor) VALUES ('5ee31892-7df1-40af-bf6f-17eff01c4476', 'Test 6', '85111111116', '1795e15f-df02-4500-9241-18f9b8ff8b76');
INSERT INTO telephone (id, description, phone_number tutor) VALUES ('5ee31892-7df1-40af-bf6f-17eff01c4477', 'Test 7', '85111111117', '1795e15f-df02-4500-9241-18f9b8ff8b77');
INSERT INTO telephone (id, description, phone_number tutor) VALUES ('5ee31892-7df1-40af-bf6f-17eff01c4478', 'Test 8', '85111111118', '1795e15f-df02-4500-9241-18f9b8ff8b78');
INSERT INTO telephone (id, description, phone_number tutor) VALUES ('5ee31892-7df1-40af-bf6f-17eff01c4479', 'Test 9', '85111111119', '1795e15f-df02-4500-9241-18f9b8ff8b79');
INSERT INTO telephone (id, description, phone_number tutor) VALUES ('5ee31892-7df1-40af-bf6f-17eff01c4481', 'Test 10', '85111111110', '1795e15f-df02-4500-9241-18f9b8ff8b81');
INSERT INTO telephone (id, description, phone_number tutor) VALUES ('5ee31892-7df1-40af-bf6f-17eff01c4482', 'Test 11', '85111111111', '1795e15f-df02-4500-9241-18f9b8ff8b82');
