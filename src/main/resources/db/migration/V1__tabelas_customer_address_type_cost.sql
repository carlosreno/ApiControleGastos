CREATE TABLE IF NOT EXISTS customer(
                                       customer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                       age INT(3) NOT NULL ,
                                       name VARCHAR(200) NOT NULL,
                                       email VARCHAR(200) NOT NULL UNIQUE ,
                                       cpf VARCHAR(11) NOT NULL UNIQUE ,
                                       customer_type_id INT NOT NULL
);
CREATE TABLE IF NOT EXISTS customer_type(
                                        customer_type_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(200) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS address(
                                      address_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                      cep VARCHAR(8) NOT NULL,
                                      rua VARCHAR(200) NOT NULL,
                                      bairro VARCHAR(200) NOT NULL,
                                      cidade VARCHAR(200) NOT NULL,
                                      uf VARCHAR(8) NOT NULL,
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

CREATE TABLE IF NOT EXISTS wallet(
                                     wallet_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                                     name VARCHAR(50) NOT NULL ,
                                     value DECIMAL(10,2) NOT NULL,
                                     customer_id INT NOT NULL
);
CREATE TABLE IF NOT EXISTS tell(
                                     tell_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                                     country_code INT(4) NOT NULL ,
                                     ddd INT(3) NOT NULL ,
                                     number VARCHAR(9) NOT NULL,
                                     customer_id INT NOT NULL
);


ALTER TABLE customer ADD CONSTRAINT fk_customer_type_id
    FOREIGN KEY (customer_type_id)
        REFERENCES customer_type(customer_type_id);

ALTER TABLE address ADD CONSTRAINT fk_customer_id
    FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id);

ALTER TABLE tell ADD CONSTRAINT fk_customer_tell_id
    FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id);

ALTER TABLE cost ADD CONSTRAINT fk_customer_cost_id
    FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id);

ALTER TABLE wallet ADD CONSTRAINT fk_customer_customer_id
    FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id);
