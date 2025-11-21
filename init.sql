-- init.sql: creates schema expected by the application
DROP DATABASE IF EXISTS rede_mais_social;
CREATE DATABASE rede_mais_social CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE rede_mais_social;

-- pedido_afiliacao table: status is VARCHAR to match JPA EnumType.STRING mapping
DROP TABLE IF EXISTS pedido_afiliacao;
CREATE TABLE pedido_afiliacao (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  requester_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  status VARCHAR(255) NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- sample seed
INSERT INTO pedido_afiliacao (requester_name, email, status) VALUES
('João Silva','joao@example.com','PENDING'),
('ONG Verde','contato@ongverde.org','APPROVED');
