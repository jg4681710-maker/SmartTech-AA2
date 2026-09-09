-- ============================================================
-- SmartTech - Esquema PostgreSQL
-- Actividad: Creación de CRUD para tablas base de datos
-- ============================================================

-- ============================================================
-- TABLA: brands
-- ============================================================

CREATE TABLE IF NOT EXISTS brands (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL UNIQUE
);

-- ============================================================
-- TABLA: device_types
-- Catálogo normalizado de tipos de dispositivos
-- ============================================================

CREATE TABLE IF NOT EXISTS device_types (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- ============================================================
-- TABLA: devices
-- ============================================================

CREATE TABLE IF NOT EXISTS devices (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(140) NOT NULL,
    brand_id BIGINT NOT NULL
        REFERENCES brands(id),
    type_id BIGINT NOT NULL
        REFERENCES device_types(id),
    release_date DATE NOT NULL,
    processor VARCHAR(120) NOT NULL,
    memory VARCHAR(120) NOT NULL,
    storage VARCHAR(120) NOT NULL,
    screen VARCHAR(80) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    price NUMERIC(12,2) NOT NULL
        CHECK (price >= 0)
);

-- ============================================================
-- TABLA: authors
-- Catálogo normalizado de autores de comentarios
-- ============================================================

CREATE TABLE IF NOT EXISTS authors (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL UNIQUE
);

-- ============================================================
-- TABLA: comments
-- ============================================================

CREATE TABLE IF NOT EXISTS comments (
    id BIGSERIAL PRIMARY KEY,
    device_id BIGINT NOT NULL
        REFERENCES devices(id)
        ON DELETE CASCADE,
    author_id BIGINT NOT NULL
        REFERENCES authors(id),
    content VARCHAR(1000) NOT NULL,
    rating INTEGER NOT NULL
        CHECK (rating BETWEEN 1 AND 5),
    created_at TIMESTAMP NOT NULL
        DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================
-- TABLA: admin_users
-- Usuarios autorizados para acceder al módulo administrativo
-- ============================================================

CREATE TABLE IF NOT EXISTS admin_users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE
);

-- ============================================================
-- ÍNDICES
-- ============================================================

CREATE INDEX IF NOT EXISTS idx_devices_brand
    ON devices(brand_id);

CREATE INDEX IF NOT EXISTS idx_devices_type
    ON devices(type_id);

CREATE INDEX IF NOT EXISTS idx_devices_release_date
    ON devices(release_date DESC);

CREATE INDEX IF NOT EXISTS idx_comments_device
    ON comments(device_id);

CREATE INDEX IF NOT EXISTS idx_comments_author
    ON comments(author_id);