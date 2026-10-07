package ru.misha.model;

public record Car(
        long id,
        long categoryId,
        String brand,
        String model,
        int productionYear,
        String registrationNumber,
        String vin,
        String status,
        int mileage
) {
}