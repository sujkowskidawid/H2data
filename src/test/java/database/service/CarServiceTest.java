package database.service;

import database.model.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
    @Mock
    private CarService carService;

    @Mock
    private Car car;

    @Test
    public void saveCarTest() {
        carService.save(car);
        Mockito.verify(carService).save(car);

        Mockito.when(carService.getAll()).thenReturn(Stream.of(car).collect(Collectors.toSet()));
        assertEquals(carService.getAll(), Stream.of(car).collect(Collectors.toSet()));

        carService.delete(car);
        Mockito.verify(carService).delete(car);
    }

}
