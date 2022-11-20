package com.db.mimicdb.controller;


import com.db.mimicdb.model.Employee;
import com.db.mimicdb.model.EmployeeRequestDto;
import com.db.mimicdb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        try {
            Employee newEmployee = employeeService.save(employeeRequestDto);
            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee findByIds(@PathVariable long id) {
        return employeeService.findById(id);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable long id,@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
          employeeService.update(id , employeeRequestDto);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public void updateEmployee(@PathVariable long id) {
        employeeService.delete(id);
    }

}
