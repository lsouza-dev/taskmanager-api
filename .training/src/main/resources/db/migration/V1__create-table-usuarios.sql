CREATE TABLE usuarios(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    login VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);
