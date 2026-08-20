INSERT INTO product (id, name, description, unit_price)
VALUES
('p001', 'Clavier', 'Clavier mécanique', 75.00),
('p002', 'Souris', 'Souris sans fil', 35.50),
('p003', 'Ecran', 'Ecran 24 pouces', 180.00);

INSERT INTO stock_movement
    (id, movement_type, quantity, product_id)
VALUES
('m001', 'IN', 10, 'p001'),
('m002', 'OUT', 2, 'p001'),
('m003', 'IN', 20, 'p002'),
('m004', 'IN', 5, 'p003');

