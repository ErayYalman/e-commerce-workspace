CREATE TABLE orders (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    total_price DECIMAL(10, 2) NOT NULL CHECK (total_price >= 0), -- toplam fiyat negatif olamaz
    status VARCHAR(50) NOT NULL DEFAULT 'pending' CHECK (status IN ('pending', 'payment_sent', 'completed', 'failed')), -- sipariş durumu (PENDING → PAYMENT_SENT → COMPLETED → FAILED)
    created_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE order_items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    order_id UUID NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    product_id UUID NOT NULL REFERENCES products(id) ON DELETE CASCADE,
    unit_price DECIMAL(10, 2) NOT NULL CHECK (unit_price >= 0), -- birim fiyat negatif olamaz
    quantity INTEGER NOT NULL CHECK (quantity > 0) -- miktar sıfırdan büyük olmalı

);


