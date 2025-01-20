CREATE TABLE tb_user
(
    id       BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    role     VARCHAR(255) NOT NULL CHECK (role IN ('ADMIN', 'USER'))
);

-- Inserir um administrador
INSERT INTO tb_user (email, password, name, role)
VALUES ('email@email.com', '$2a$10$0gVx7uw80kbnsX.z9Sina.wtnRv8iHL0gaBmHXp6TJCHBPOhf082a', 'admin', 'ADMIN');

-- Inserir um usuário comum
INSERT INTO tb_user (email, password, name, role)
VALUES ('email2@email.com', '$2a$10$0gVx7uw80kbnsX.z9Sina.wtnRv8iHL0gaBmHXp6TJCHBPOhf082a', 'user', 'USER');
