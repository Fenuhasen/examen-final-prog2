-- =========================
-- ENUMERATION
-- =========================

CREATE TYPE movement_type AS ENUM (
    'IN',
    'OUT'
);


-- =========================
-- TABLE PRODUCT
-- =========================

CREATE TABLE product (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    unit_price NUMERIC(15, 2) NOT NULL
);


-- =========================
-- TABLE STOCK MOVEMENT
-- =========================

CREATE TABLE stock_movement (
    id VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    movement_type movement_type NOT NULL,
    quantity INTEGER NOT NULL,
    
    product_id VARCHAR(255) NOT NULL,

    CONSTRAINT fk_stock_movement_product
        FOREIGN KEY (product_id)
        REFERENCES product(id)
        ON DELETE CASCADE,

    CONSTRAINT check_quantity_positive
        CHECK (quantity > 0)
);