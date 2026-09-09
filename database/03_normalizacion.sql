-- SmartTech - Normalización de type y author
-- Migración de la estructura existente

BEGIN;

-- ============================================================
-- 1. Crear catálogo de tipos de dispositivo
-- ============================================================

CREATE TABLE IF NOT EXISTS device_types (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- ============================================================
-- 2. Crear catálogo de autores
-- ============================================================

CREATE TABLE IF NOT EXISTS authors (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL UNIQUE
);

-- ============================================================
-- 3. Migrar los tipos existentes
-- ============================================================

INSERT INTO device_types (name)
SELECT DISTINCT type
FROM devices
WHERE type IS NOT NULL
ON CONFLICT (name) DO NOTHING;

-- ============================================================
-- 4. Migrar los autores existentes
-- ============================================================

INSERT INTO authors (name)
SELECT DISTINCT author
FROM comments
WHERE author IS NOT NULL
ON CONFLICT (name) DO NOTHING;

-- ============================================================
-- 5. Agregar las nuevas claves foráneas
-- ============================================================

ALTER TABLE devices
ADD COLUMN IF NOT EXISTS type_id BIGINT;

ALTER TABLE comments
ADD COLUMN IF NOT EXISTS author_id BIGINT;

-- ============================================================
-- 6. Relacionar los dispositivos con device_types
-- ============================================================

UPDATE devices d
SET type_id = dt.id
FROM device_types dt
WHERE d.type = dt.name;

-- ============================================================
-- 7. Relacionar los comentarios con authors
-- ============================================================

UPDATE comments c
SET author_id = a.id
FROM authors a
WHERE c.author = a.name;

-- ============================================================
-- 8. Hacer obligatorias las nuevas relaciones
-- ============================================================

ALTER TABLE devices
ALTER COLUMN type_id SET NOT NULL;

ALTER TABLE comments
ALTER COLUMN author_id SET NOT NULL;

-- ============================================================
-- 9. Crear las claves foráneas
-- ============================================================

ALTER TABLE devices
ADD CONSTRAINT fk_devices_device_type
FOREIGN KEY (type_id)
REFERENCES device_types(id);

ALTER TABLE comments
ADD CONSTRAINT fk_comments_author
FOREIGN KEY (author_id)
REFERENCES authors(id);

-- ============================================================
-- 10. Crear índices
-- ============================================================

CREATE INDEX IF NOT EXISTS idx_devices_type
ON devices(type_id);

CREATE INDEX IF NOT EXISTS idx_comments_author
ON comments(author_id);

-- ============================================================
-- 11. Eliminar los campos no normalizados
-- ============================================================

ALTER TABLE devices
DROP COLUMN type;

ALTER TABLE comments
DROP COLUMN author;

COMMIT;