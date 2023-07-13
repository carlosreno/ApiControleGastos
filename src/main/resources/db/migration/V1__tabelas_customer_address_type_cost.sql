CREATE TABLE IF NOT EXISTS customer(
                                       customer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                       age INT(3) NOT NULL ,
                                       name VARCHAR(200) NOT NULL,
                                       cpf VARCHAR(11) NOT NULL,
                                       customer_type_id INT NOT NULL
);
CREATE TABLE IF NOT EXISTS customer_type(
                                        customer_type INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(200) NOT NULL
);


CREATE TABLE IF NOT EXISTS address(
                                       address_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                       cep VARCHAR(200) NOT NULL,
                                       rua VARCHAR(8) NOT NULL,
                                       bairro VARCHAR(8) NOT NULL,
                                       cidade VARCHAR(8) NOT NULL,
                                       numero INT NOT NULL,
                                       customer_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS cost(
                                      cost_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(200) NOT NULL,
                                      value DECIMAL(10,2) NOT NULL,
                                      initial_date date NOT NULL,
                                      final_date date NOT NULL,
                                      customer_id INT NOT NULL
);
ALTER TABLE customer ADD CONSTRAINT fk_customer_type_id
    FOREIGN KEY (customer_type_id)
        REFERENCES customer_type(customer_type);

ALTER TABLE address ADD CONSTRAINT fk_customer_id
    FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id);

ALTER TABLE cost ADD CONSTRAINT fk_customer_cost_id
    FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id)
