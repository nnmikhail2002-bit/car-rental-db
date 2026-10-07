package ru.misha.service;

import ru.misha.model.Car;
import ru.misha.repository.CarRepository;

import java.util.List;

public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(long id) {
        return carRepository.findById(id);
    }

    public long addCar(Car car) {
        validateCar(car);
        return carRepository.add(car);
    }

    public boolean updateCar(Car car) {
        validateCar(car);
        return carRepository.update(car);
    }

    public boolean deleteCar(long id) {
        return carRepository.deleteById(id);
    }

    private void validateCar(Car car) {

        if (car.brand() == null || car.brand().isBlank()) {
            throw new IllegalArgumentException(
                    "Марка автомобиля не может быть пустой"
            );
        }

        if (car.model() == null || car.model().isBlank()) {
            throw new IllegalArgumentException(
                    "Модель автомобиля не может быть пустой"
            );
        }

        if (car.productionYear() < 1990) {
            throw new IllegalArgumentException(
                    "Год выпуска не может быть меньше 1990"
            );
        }

        if (car.mileage() < 0) {
            throw new IllegalArgumentException(
                    "Пробег не может быть отрицательным"
            );
        }
    }
}