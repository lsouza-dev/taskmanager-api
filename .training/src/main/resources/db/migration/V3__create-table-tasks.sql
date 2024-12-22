CREATE TABLE tasks(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    created_time TIMESTAMP,
    conclusion_time TIMESTAMP
);
