-- SmartTech - Esquema PostgreSQL
CREATE TABLE IF NOT EXISTS brands (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS devices (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(140) NOT NULL,
    brand_id BIGINT NOT NULL REFERENCES brands(id),
    type VARCHAR(50) NOT NULL,
    release_date DATE NOT NULL,
    processor VARCHAR(120) NOT NULL,
    memory VARCHAR(120) NOT NULL,
    storage VARCHAR(120) NOT NULL,
    screen VARCHAR(80) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    price NUMERIC(12,2) NOT NULL CHECK (price >= 0)
);

CREATE TABLE IF NOT EXISTS comments (
    id BIGSERIAL PRIMARY KEY,
    device_id BIGINT NOT NULL REFERENCES devices(id) ON DELETE CASCADE,
    author VARCHAR(80) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_devices_brand ON devices(brand_id);
CREATE INDEX IF NOT EXISTS idx_devices_release_date ON devices(release_date DESC);
CREATE INDEX IF NOT EXISTS idx_comments_device ON comments(device_id);
