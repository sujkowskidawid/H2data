package database.service;

import database.model.Car;
import database.model.Employee;
import database.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository repository;

    public void save(final Car car) {
        repository.save(car);
    }

    public void delete(final Car car) {
        repository.delete(car);
    }

    public Car getById(int id){
        return repository.getOne(id);
    }

    public Set<Car> getAll() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }

}