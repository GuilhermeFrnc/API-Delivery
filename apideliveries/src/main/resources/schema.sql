CREATE DATABASE IF NOT EXISTS deliveries_db;

USE deliveries_db;

CREATE TABLE address (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         street VARCHAR(255) NOT NULL,
                         city VARCHAR(255) NOT NULL,
                         number VARCHAR(50),
                         complement VARCHAR(50),
                         zip_code VARCHAR(20)
);

CREATE TABLE delivery (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name_client VARCHAR(255) NOT NULL,
                          name_store VARCHAR(255) NOT NULL,
                          address_id BIGINT NOT NULL,
                          date_create DATETIME DEFAULT CURRENT_TIMESTAMP,
                          date_delivery DATETIME,
                          status ENUM('PENDING', 'DELIVERED') NOT NULL,
                          FOREIGN KEY (address_id) REFERENCES address (id)
);