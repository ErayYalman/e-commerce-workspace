CREATE TABLE products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    sku VARCHAR(50) UNIQUE NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0), -- fiyat negatif olamaz
    stock_quantity INTEGER NOT NULL DEFAULT 0 CHECK (stock_quantity >= 0) -- stok miktarı negatif olamaz

);