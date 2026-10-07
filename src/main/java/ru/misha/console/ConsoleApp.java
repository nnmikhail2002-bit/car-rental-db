package ru.misha.console;

import ru.misha.model.Car;
import ru.misha.service.CarService;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    private final CarService carService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleApp(CarService carService) {
        this.carService = carService;
    }

    public void run() {

        boolean running = true;

        while (running) {

            printMenu();

            String command = scanner.nextLine();

            try {

                switch (command) {
                    case "1" -> showAllCars();
                    case "2" -> findCarById();
                    case "3" -> addCar();
                    case "4" -> updateCar();
                    case "5" -> deleteCar();
                    case "0" -> running = false;

                    default ->
                            System.out.println("Неизвестная команда.");
                }

            } catch (RuntimeException e) {

                System.out.println(
                        "Ошибка: " + e.getMessage()
                );
            }

            System.out.println();
        }

        System.out.println("Программа завершена.");
    }

    private void printMenu() {

        System.out.println("""
                
                ===== CAR RENTAL DATABASE =====
                
                1. Показать все автомобили
                2. Найти автомобиль по ID
                3. Добавить автомобиль
                4. Изменить автомобиль
                5. Удалить автомобиль
                0. Выход
                
                Выберите действие:
                """);
    }

    private void showAllCars() {

        List<Car> cars = carService.getAllCars();

        if (cars.isEmpty()) {
            System.out.println("Автомобилей нет.");
            return;
        }

        System.out.println("=== АВТОМОБИЛИ ===");

        for (Car car : cars) {
            printCar(car);
        }
    }

    private void findCarById() {

        long id = readLong("Введите ID автомобиля: ");

        Car car = carService.getCarById(id);

        if (car == null) {
            System.out.println("Автомобиль не найден.");
            return;
        }

        printCar(car);
    }

    private void addCar() {

        System.out.println("=== ДОБАВЛЕНИЕ АВТОМОБИЛЯ ===");

        long categoryId =
                readLong("ID категории: ");

        String brand =
                readString("Марка: ");

        String model =
                readString("Модель: ");

        int year =
                readInt("Год выпуска: ");

        String registrationNumber =
                readString("Гос. номер: ");

        String vin =
                readString("VIN: ");

        int mileage =
                readInt("Пробег: ");

        Car car = new Car(
                0,
                categoryId,
                brand,
                model,
                year,
                registrationNumber,
                vin,
                "AVAILABLE",
                mileage
        );

        long id = carService.addCar(car);

        System.out.println(
                "Автомобиль добавлен. ID = " + id
        );
    }

    private void updateCar() {

        long id =
                readLong("Введите ID автомобиля: ");

        Car oldCar = carService.getCarById(id);

        if (oldCar == null) {
            System.out.println("Автомобиль не найден.");
            return;
        }

        System.out.println("Текущие данные:");
        printCar(oldCar);

        String status =
                readString(
                        "Новый статус (AVAILABLE / RENTED / SERVICE): "
                );

        int mileage =
                readInt("Новый пробег: ");

        Car updatedCar = new Car(
                oldCar.id(),
                oldCar.categoryId(),
                oldCar.brand(),
                oldCar.model(),
                oldCar.productionYear(),
                oldCar.registrationNumber(),
                oldCar.vin(),
                status,
                mileage
        );

        boolean updated =
                carService.updateCar(updatedCar);

        if (updated) {
            System.out.println("Автомобиль обновлён.");
        } else {
            System.out.println("Автомобиль не найден.");
        }
    }

    private void deleteCar() {

        long id =
                readLong("Введите ID автомобиля: ");

        Car car = carService.getCarById(id);

        if (car == null) {
            System.out.println("Автомобиль не найден.");
            return;
        }

        System.out.println("Будет удалён:");
        printCar(car);

        String answer =
                readString("Удалить? (yes/no): ");

        if (!answer.equalsIgnoreCase("yes")) {
            System.out.println("Удаление отменено.");
            return;
        }

        boolean deleted =
                carService.deleteCar(id);

        if (deleted) {
            System.out.println("Автомобиль удалён.");
        } else {
            System.out.println("Удалить автомобиль не удалось.");
        }
    }

    private void printCar(Car car) {

        System.out.println(
                car.id() + " | " +
                        car.brand() + " " +
                        car.model() + " | " +
                        car.productionYear() + " | " +
                        car.registrationNumber() + " | " +
                        car.status() + " | " +
                        car.mileage() + " км"
        );
    }

    private String readString(String message) {

        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private long readLong(String message) {

        while (true) {

            try {
                return Long.parseLong(
                        readString(message)
                );

            } catch (NumberFormatException e) {
                System.out.println(
                        "Введите целое число."
                );
            }
        }
    }

    private int readInt(String message) {

        while (true) {

            try {
                return Integer.parseInt(
                        readString(message)
                );

            } catch (NumberFormatException e) {
                System.out.println(
                        "Введите целое число."
                );
            }
        }
    }
}