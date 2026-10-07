BEGIN;

-- До изменения
SELECT id, brand, model, category_id
FROM cars
WHERE category_id = 2;

-- Меняем PK категории
UPDATE car_categories
SET id = 20
WHERE id = 2;

-- Проверяем, изменились ли внешние ключи автоматически
SELECT id, brand, model, category_id
FROM cars
WHERE category_id = 20;

ROLLBACK;

BEGIN;

-- Смотрим платежи аренды №1 до удаления
SELECT *
FROM payments
WHERE rental_id = 1;

-- Удаляем аренду
DELETE FROM rentals
WHERE id = 1;

-- Проверяем платежи после удаления аренды
SELECT *
FROM payments
WHERE rental_id = 1;

ROLLBACK;

BEGIN;

ALTER TABLE cars
DROP CONSTRAINT fk_cars_category;

INSERT INTO cars (
    category_id,
    brand,
    model,
    production_year,
    registration_number,
    vin,
    mileage
)
VALUES (
           999,
           'Test',
           'Without FK',
           2024,
           'X999XX52',
           'TESTVIN0000000002',
           0
       );

SELECT *
FROM cars
WHERE category_id = 999;

ROLLBACK;

SELECT *
FROM cars
WHERE category_id = 999;

SELECT *
FROM car_categories
WHERE id IN (2, 20);