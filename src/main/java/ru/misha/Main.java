package ru.misha;

import ru.misha.console.ConsoleApp;
import ru.misha.repository.CarRepository;
import ru.misha.service.CarService;

public class Main {

    public static void main(String[] args) {

        CarRepository carRepository =
                new CarRepository();

        CarService carService =
                new CarService(carRepository);

        ConsoleApp consoleApp =
                new ConsoleApp(carService);

        consoleApp.run();
    }
}