INSERT IGNORE INTO address (id, street, city, number, complement,  zip_code) VALUES
(1, '123 Main St', 'Springfield', '10','10', '62704'),
(2, '456 Elm St', 'Shelbyville', '20','', '62705');

INSERT IGNORE INTO delivery (id, name_client, name_store, address_id, status) VALUES
(1, 'John Doe', 'Fast Store', 1, 'PENDING'),
(2, 'Jane Smith', 'Tech Market', 2, 'DELIVERED');