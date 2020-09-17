package database.repository;

import database.model.Car;
import database.model.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class CarRepositoryTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private Car car;

    @Before
    public void init() {
        System.out.println("Rozpoczęcie testów CarRepository");
    }


    @Test
    public void saveCarTest() {
        carRepository.save(car);
        Mockito.verify(carRepository).save(car);

        // Mockito.when(carRepository.findAll()).thenReturn(Stream.of(car).collect(Collectors.toSet()));
        assertEquals(carRepository.findAll(), Stream.of(car).collect(Collectors.toSet()));

        carRepository.delete(car);
        Mockito.verify(carRepository).delete(car);
    }


    @After
    public void done() {
        System.out.println("Zakończenie testów CarRepository");
    }
}

