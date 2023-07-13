CREATE TABLE IF NOT EXISTS wallet(
                                     wallet_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                                     name VARCHAR(50) NOT NULL UNIQUE ,
                                     value DECIMAL(10,2) NOT NULL
);
ALTER TABLE customer ADD wallet_id INT NOT NULL;
ALTER TABLE customer ADD CONSTRAINT fk_wallet
    FOREIGN KEY (wallet_id) REFERENCES wallet(wallet_id);