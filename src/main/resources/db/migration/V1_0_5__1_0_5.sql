CREATE TABLE usuario
(
    id              VARCHAR(255) NOT NULL,
    nome            VARCHAR(255),
    data_nascimento date,
    cpf             INT,
    email           VARCHAR(255),
    senha           VARCHAR(255),
    salario         DOUBLE PRECISION,
    situacao        SMALLINT,
    fk_endereco     INT,
    fk_cargo        INT          NOT NULL,
    fk_filial       INT          NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

ALTER TABLE usuario
    ADD CONSTRAINT uc_usuario_fk_endereco UNIQUE (fk_endereco);

ALTER TABLE usuario
    ADD CONSTRAINT FK_CARGO_USER FOREIGN KEY (fk_cargo) REFERENCES cargo (id);

ALTER TABLE usuario
    ADD CONSTRAINT FK_FILIAL_USUARIO FOREIGN KEY (fk_filial) REFERENCES filial (id);

ALTER TABLE usuario
    ADD CONSTRAINT FK_USUARIO_ON_FK_ENDERECO FOREIGN KEY (fk_endereco) REFERENCES endereco (id);