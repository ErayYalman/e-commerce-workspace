CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, --şifrelenmiş şifreyi saklamak için yeterli uzunlukta olmalı
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT now(),
    updated_at TIMESTAMPTZ DEFAULT now()
    
);