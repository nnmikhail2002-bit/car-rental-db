CREATE TABLE car_categories (
                                id BIGSERIAL PRIMARY KEY,

                                name VARCHAR(50) NOT NULL UNIQUE,

                                daily_rate NUMERIC(10, 2) NOT NULL,

                                deposit NUMERIC(10, 2) NOT NULL DEFAULT 0,

                                CONSTRAINT chk_car_categories_daily_rate
                                    CHECK (daily_rate > 0),

                                CONSTRAINT chk_car_categories_deposit
                                    CHECK (deposit >= 0)
);

CREATE TABLE clients (
                         id BIGSERIAL PRIMARY KEY,

                         full_name VARCHAR(150) NOT NULL,

                         phone VARCHAR(20) NOT NULL UNIQUE,

                         email VARCHAR(100),

                         driver_license VARCHAR(30) NOT NULL UNIQUE,

                         birth_date DATE NOT NULL,

                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cars (
                      id BIGSERIAL PRIMARY KEY,

                      category_id BIGINT NOT NULL,

                      brand VARCHAR(50) NOT NULL,

                      model VARCHAR(50) NOT NULL,

                      production_year INTEGER NOT NULL,

                      registration_number VARCHAR(15) NOT NULL UNIQUE,

                      vin VARCHAR(17) NOT NULL UNIQUE,

                      status VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE',

                      mileage INTEGER NOT NULL DEFAULT 0,

                      CONSTRAINT chk_cars_production_year
                          CHECK (production_year >= 1990),

                      CONSTRAINT chk_cars_mileage
                          CHECK (mileage >= 0),

                      CONSTRAINT chk_cars_status
                          CHECK (status IN ('AVAILABLE', 'RENTED', 'SERVICE')),

                      CONSTRAINT fk_cars_category
                          FOREIGN KEY (category_id)
                              REFERENCES car_categories(id)
                              ON UPDATE CASCADE
                              ON DELETE RESTRICT
);


CREATE TABLE rentals (
                         id BIGSERIAL PRIMARY KEY,

                         client_id BIGINT NOT NULL,

                         car_id BIGINT NOT NULL,

                         start_date DATE NOT NULL,

                         planned_return_date DATE NOT NULL,

                         actual_return_date DATE,

                         status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

                         total_price NUMERIC(10, 2) NOT NULL,

                         CONSTRAINT chk_rentals_dates
                             CHECK (planned_return_date >= start_date),

                         CONSTRAINT chk_rentals_actual_return_date
                             CHECK (
                                 actual_return_date IS NULL
                                     OR actual_return_date >= start_date
                                 ),

                         CONSTRAINT chk_rentals_status
                             CHECK (status IN ('ACTIVE', 'COMPLETED', 'CANCELLED')),

                         CONSTRAINT chk_rentals_total_price
                             CHECK (total_price >= 0),

                         CONSTRAINT fk_rentals_client
                             FOREIGN KEY (client_id)
                                 REFERENCES clients(id)
                                 ON UPDATE CASCADE
                                 ON DELETE RESTRICT,

                         CONSTRAINT fk_rentals_car
                             FOREIGN KEY (car_id)
                                 REFERENCES cars(id)
                                 ON UPDATE CASCADE
                                 ON DELETE RESTRICT
);


CREATE TABLE payments (
                          id BIGSERIAL PRIMARY KEY,

                          rental_id BIGINT NOT NULL,

                          amount NUMERIC(10, 2) NOT NULL,

                          payment_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          payment_method VARCHAR(20) NOT NULL,

                          status VARCHAR(20) NOT NULL DEFAULT 'PAID',

                          CONSTRAINT chk_payments_amount
                              CHECK (amount > 0),

                          CONSTRAINT chk_payments_method
                              CHECK (payment_method IN ('CARD', 'CASH', 'TRANSFER')),

                          CONSTRAINT chk_payments_status
                              CHECK (status IN ('PAID', 'REFUNDED', 'PENDING')),

                          CONSTRAINT fk_payments_rental
                              FOREIGN KEY (rental_id)
                                  REFERENCES rentals(id)
                                  ON UPDATE CASCADE
                                  ON DELETE CASCADE
);