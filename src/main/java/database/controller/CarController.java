package database.controller;

import database.model.Car;
import database.service.CarService;
import database.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final EmployeeService employeeService;

    private Set<Car> carList = new HashSet<>();

    @RequestMapping(value = "/car_form", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("empList", employeeService.getAll());
        return "car/car_form";
    }

    @PostMapping(value = "/save_car")
    public String save(@ModelAttribute @Valid Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("car", car);
            model.addAttribute("empList", employeeService.getAll());
            return "car/car_form";
        }

        carService.save(car);
        return "redirect:/car_list";
    }

    @PostMapping(value = "/delete_car")
    public ModelAndView delete(@RequestParam(value = "car_id") int car_id) {
        Car car = carService.getById(car_id);
        carService.delete(car);
        carList.remove(car);
        return new ModelAndView("redirect:/car_list");
    }

    @PostMapping(value = "/edit_car")
    public ModelAndView edit(@RequestParam(value = "car_id") int car_id) {
        Car car = carService.getById(car_id);
        return new ModelAndView("car/car_form", "car", car);
    }

    @RequestMapping("/car_list")
    public ModelAndView carList() {
        return new ModelAndView("car/car_list", "list", carService.getAll());
    }

}
