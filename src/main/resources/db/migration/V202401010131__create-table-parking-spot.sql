CREATE TABLE parking_spot(
    id BINARY(16) PRIMARY KEY,
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