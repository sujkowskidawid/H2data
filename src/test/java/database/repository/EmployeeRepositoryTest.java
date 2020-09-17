package database.repository;

import database.model.Employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void init(){
        System.out.println("Rozpoczęcie testów EmployeeRepository");
    }

    @Test
    public void CRUDOperationsEmployee() {
        Employee employee = new Employee();
        employee.setAge(19.0);
        employee.setStartJobDate(LocalDate.now());
        employee.setAddress("Złota 52");
        employee.setBenefit(4);
        employee.setCity("Warszawa");
        employee.setEmail("email@email.com");
        employee.setFirstName("Jan");
        employee.setLastName("Nowak");
        employee.setSalary(2000);

        employeeRepository.save(employee);
        assertThat(employeeRepository.findById(employee.getId())).isNotNull();

        String newCityValue = "Radom";
        employee.setCity(newCityValue);
        employeeRepository.save(employee);
        assertThat(employeeRepository.findById(employee.getId()).get().getCity()).isEqualTo(newCityValue);

        employeeRepository.delete(employee);
        assertThat(employeeRepository.findById(employee.getId())).isEmpty();
    }

    @After
    public void done(){
        System.out.println("Zakończenie testów EmployeeRepository");
    }

}
