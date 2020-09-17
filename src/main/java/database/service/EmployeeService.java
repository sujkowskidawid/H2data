package database.service;

import database.model.Employee;
import database.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public void save(final Employee employee) {
        repository.save(employee);
    }

    public void delete(final Employee employee) {
        repository.delete(employee);
    }

    public Employee getById(int id){
        return repository.getOne(id);
    }

    public Set<Employee> getAll() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }

}