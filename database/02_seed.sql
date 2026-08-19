INSERT INTO brands (name) VALUES
('Samsung'), ('Apple'), ('Lenovo'), ('Xiaomi')
ON CONFLICT (name) DO NOTHING;

INSERT INTO devices
(name, brand_id, type, release_date, processor, memory, storage, screen, description, image_url, price)
SELECT 'Galaxy S25', id, 'Celular', '2025-01-22',
       'Snapdragon 8 Elite', '12 GB RAM', '256 GB', '6.2 pulgadas AMOLED',
       'Celular de alto rendimiento orientado a productividad, fotografía y entretenimiento.',
       'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?auto=format&fit=crop&w=900&q=80',
       3299000
FROM brands WHERE name='Samsung'
AND NOT EXISTS (SELECT 1 FROM devices WHERE name='Galaxy S25');

INSERT INTO devices
(name, brand_id, type, release_date, processor, memory, storage, screen, description, image_url, price)
SELECT 'iPhone 16', id, 'Celular', '2024-09-20',
       'Apple A18', '8 GB RAM', '128 GB', '6.1 pulgadas OLED',
       'Smartphone con enfoque en rendimiento, fotografía computacional y ecosistema Apple.',
       'https://images.unsplash.com/photo-1592899677977-9c10ca588bbd?auto=format&fit=crop&w=900&q=80',
       3999000
FROM brands WHERE name='Apple'
AND NOT EXISTS (SELECT 1 FROM devices WHERE name='iPhone 16');

INSERT INTO devices
(name, brand_id, type, release_date, processor, memory, storage, screen, description, image_url, price)
SELECT 'ThinkPad X1 Carbon', id, 'Portátil', '2025-01-07',
       'Intel Core Ultra', '16 GB RAM', '512 GB SSD', '14 pulgadas',
       'Portátil empresarial diseñado para productividad, movilidad y trabajo profesional.',
       'https://images.unsplash.com/photo-1496181133206-80ce9b88a853?auto=format&fit=crop&w=900&q=80',
       6899000
FROM brands WHERE name='Lenovo'
AND NOT EXISTS (SELECT 1 FROM devices WHERE name='ThinkPad X1 Carbon');

INSERT INTO devices
(name, brand_id, type, release_date, processor, memory, storage, screen, description, image_url, price)
SELECT 'Xiaomi 14', id, 'Celular', '2024-02-25',
       'Snapdragon 8 Gen 3', '12 GB RAM', '512 GB', '6.36 pulgadas AMOLED',
       'Dispositivo compacto de gama alta con gran capacidad de almacenamiento y cámara avanzada.',
       'https://images.unsplash.com/photo-1512499617640-c2f999098c01?auto=format&fit=crop&w=900&q=80',
       2799000
FROM brands WHERE name='Xiaomi'
AND NOT EXISTS (SELECT 1 FROM devices WHERE name='Xiaomi 14');
