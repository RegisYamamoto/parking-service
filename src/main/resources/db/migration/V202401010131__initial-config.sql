CREATE TABLE parking_spot(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Id unico, autoincrement',
    uuid BINARY(16) NOT NULL COMMENT 'UUID',
    parking_spot_number VARCHAR(10) NOT NULL COMMENT 'Número da vaga do estacionamento',
    license_plate VARCHAR(8) NOT NULL COMMENT 'Placa do carro',
    car_brand VARCHAR(70) NOT NULL COMMENT 'Marca do carro',
    car_model VARCHAR(70) NOT NULL COMMENT 'Modelo do carro',
    car_color VARCHAR(70) NOT NULL COMMENT 'Cor do carro',
    registration_date TIMESTAMP NOT NULL COMMENT 'Data do cadastro',
    responsible_name VARCHAR (130) NOT NULL COMMENT 'Nome do responsavel',
    apartment VARCHAR(30) COMMENT 'Apartamento',
    block VARCHAR(30) COMMENT 'Bloco'
);

CREATE TABLE user_role (
    id INT NOT NULL PRIMARY KEY UNIQUE COMMENT 'Id unico',
    role VARCHAR(20) NOT NULL UNIQUE COMMENT 'Nivel de acesso'
);

CREATE TABLE users (
    id BIGINT PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT COMMENT 'Id unico, autoincrement',
    uuid BINARY(16) NOT NULL COMMENT 'UUID',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT 'Email de login',
    password VARCHAR(100) NOT NULL COMMENT 'Senha',
    role INT NOT NULL COMMENT 'Nivel de acesso',
    FOREIGN KEY (role) REFERENCES user_role(id)
);

INSERT INTO user_role (id, role) values (1, 'ADMIN');
INSERT INTO user_role (id, role) values (2, 'USER');