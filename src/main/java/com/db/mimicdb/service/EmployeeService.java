package com.db.mimicdb.service;

import com.db.mimicdb.model.Employee;
import com.db.mimicdb.model.EmployeeRequestDto;

import java.util.List;

public interface EmployeeService {

    Employee save(EmployeeRequestDto employeeRequestDto);

     Employee findById(long primaryKey);

    List<Employee> findAll();

    void delete(long primaryKey);

    void update(long id , EmployeeRequestDto employeeRequestDto);
    boolean existsById(Employee primaryKey);
}
