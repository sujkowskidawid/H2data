package database.controller;


import database.model.Car;
import database.model.Employee;
import database.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final EmployeeService employeeService;

    private Set<Employee> employeeList = new HashSet<>();

    @GetMapping("/")
    public String index() {
        return "employee/index";
    }

    @RequestMapping(value = "/employee_form", method = RequestMethod.GET)
    public String showForm(Model model) {
        Employee employee = new Employee();
        employee.setStartJobDate(LocalDate.now());
        model.addAttribute("employee", employee);
        return "employee/employee_form";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute @Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "employee/employee_form";
        }

        if (employee.getId() == 0) {
            employeeService.save(employee);
            getEmployeeList().add(employee);
        } else {
            Employee empTemp = employeeService.getById(employee.getId());
            empTemp.setFirstName(employee.getFirstName());
            empTemp.setSalary(employee.getSalary());
            empTemp.setAddress(employee.getAddress());
            empTemp.setBenefit(employee.getBenefit());
            empTemp.setAge(employee.getAge());
            empTemp.setCity(employee.getCity());
            empTemp.setLastName(employee.getLastName());
            empTemp.setStartJobDate(employee.getStartJobDate());
            empTemp.setEmail(employee.getEmail());
            employeeService.save(employee);
        }
        return "redirect:/employee_list";
    }

    @PostMapping(value = "/delete")
    public ModelAndView delete(@RequestParam(value = "emp_id") int emp_id) {
        Employee employee = employeeService.getById(emp_id);
        employeeService.delete(employee);
        employeeList.remove(employee);
        return new ModelAndView("redirect:/employee_list");
    }

    @PostMapping(value = "/edit")
    public ModelAndView edit(@RequestParam(value = "emp_id") int emp_id) {
        Employee employee = employeeService.getById(emp_id);
        return new ModelAndView("employee/employee_form", "employee", employee);
    }

    @PostMapping(value = "/addCar")
    public String addCar(Model model, @RequestParam(value = "emp_id") int emp_id) {
        Employee employee = employeeService.getById(emp_id);
        Car car = new Car();
        car.setEmployee(employee);
        model.addAttribute("car", car);
        model.addAttribute("empList", employeeService.getAll());
        return "car/car_form";
    }

    @RequestMapping("/employee_list")
    public ModelAndView employee_list() {
        return new ModelAndView("employee/employee_list", "list", getEmployeeList());
    }

    public Set<Employee> getEmployeeList() {
        return employeeList == null ? employeeList = employeeService.getAll() : employeeList;
    }
}
