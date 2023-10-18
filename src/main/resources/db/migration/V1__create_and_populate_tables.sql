DROP TABLE IF EXISTS brands;
DROP TABLE IF EXISTS prices;
CREATE TABLE IF NOT EXISTS brands(
    brand_id INT PRIMARY KEY,
    brand_name VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS prices (
    price_list SERIAL PRIMARY KEY,
    brand_id  INT,
    price DECIMAL(7,2),
    priority INT,
    product_id BIGINT,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    currency VARCHAR(255),
    FOREIGN KEY (brand_id) REFERENCES brands (brand_id)
);

INSERT INTO brands (brand_id, brand_name)
VALUES (1, 'Zara');

INSERT INTO prices (brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES
    (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
    (1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
    (1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
    (1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');

