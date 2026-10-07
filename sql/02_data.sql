INSERT INTO car_categories (name, daily_rate, deposit)
VALUES
    ('Economy', 2500.00, 5000.00),
    ('Comfort', 3500.00, 7000.00),
    ('Business', 6000.00, 12000.00);

INSERT INTO car_categories (name, daily_rate)
VALUES ('SUV', 5000.00);

INSERT INTO clients (
    full_name,
    phone,
    email,
    driver_license,
    birth_date
)
VALUES
    (
        'Иванов Иван Иванович',
        '+79990000001',
        'ivanov@example.com',
        'AA000001',
        '1995-03-12'
    ),
    (
        'Петров Петр Сергеевич',
        '+79990000002',
        NULL,
        'AA000002',
        '1988-07-25'
    ),
    (
        'Сидорова Анна Олеговна',
        '+79990000003',
        'sidorova@example.com',
        'AA000003',
        '1999-11-04'
    ),
    (
        'Смирнов Алексей Андреевич',
        '+79990000004',
        'smirnov@example.com',
        'AA000004',
        '1992-01-18'
    );

INSERT INTO cars (
    category_id,
    brand,
    model,
    production_year,
    registration_number,
    vin,
    mileage
)
VALUES
    (
        1,
        'Kia',
        'Rio',
        2023,
        'A101AA52',
        'XWEHC512BC0000001',
        25000
    ),
    (
        2,
        'Toyota',
        'Camry',
        2022,
        'B202BB52',
        'JTNB11HK303000002',
        42000
    ),
    (
        2,
        'Kia',
        'K5',
        2024,
        'C303CC52',
        'KNAGM4AD0R5000003',
        12000
    ),
    (
        3,
        'BMW',
        '5 Series',
        2023,
        'E404EE52',
        'WBA11BJ00PW000004',
        18000
    ),
    (
        4,
        'Toyota',
        'RAV4',
        2024,
        'K505KK52',
        'JTMB43FV10D000005',
        8000
    );

INSERT INTO rentals (
    client_id,
    car_id,
    start_date,
    planned_return_date,
    actual_return_date,
    status,
    total_price
)
VALUES
    (
        1,
        2,
        '2026-09-10',
        '2026-09-15',
        '2026-09-15',
        'COMPLETED',
        17500.00
    ),
    (
        2,
        1,
        '2026-09-20',
        '2026-09-23',
        '2026-09-23',
        'COMPLETED',
        7500.00
    ),
    (
        3,
        5,
        '2026-10-05',
        '2026-10-10',
        NULL,
        'ACTIVE',
        25000.00
    );

INSERT INTO payments (
    rental_id,
    amount,
    payment_method,
    status
)
VALUES
    (
        1,
        10000.00,
        'CARD',
        'PAID'
    ),
    (
        1,
        7500.00,
        'CARD',
        'PAID'
    ),
    (
        2,
        7500.00,
        'CASH',
        'PAID'
    ),
    (
        3,
        10000.00,
        'TRANSFER',
        'PAID'
    ),
    (
        3,
        15000.00,
        'CARD',
        'PENDING'
    );