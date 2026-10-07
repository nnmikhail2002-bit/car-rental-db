-- Проверка NOT NULL:
-- имя клиента является обязательным

INSERT INTO clients (
    full_name,
    phone,
    driver_license,
    birth_date
)
VALUES (
           NULL,
           '+79990000010',
           'AA000010',
           '1990-01-01'
       );

-- Проверка UNIQUE:
-- телефон клиента не должен повторяться


INSERT INTO clients (
    full_name,
    phone,
    driver_license,
    birth_date
)
VALUES (
           'Тестовый Клиент',
           '+79990000001',
           'AA000011',
           '1990-01-01'
       );

-- Проверка CHECK

INSERT INTO car_categories (
    name,
    daily_rate,
    deposit
)
VALUES (
           'Test',
           -1000,
           5000
       );


-- Проверка FOREIGN KEY

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
           'Car',
           2024,
           'T999TT52',
           'TESTVIN0000000001',
           0
       );

-- Планируемая дата возврата не может быть раньше начала аренды

INSERT INTO rentals (
    client_id,
    car_id,
    start_date,
    planned_return_date,
    total_price
)
VALUES (
           1,
           1,
           '2026-10-20',
           '2026-10-10',
           5000
       );

ELETE FROM clients
WHERE id = 1;