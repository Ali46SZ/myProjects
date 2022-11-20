package com.db.mimicdb.dao;

import com.db.mimicdb.model.Employee;
import com.db.mimicdb.model.EmployeeRequestDto;

import java.util.List;

public interface EmployeeDao {
    Employee save(EmployeeRequestDto employeeRequestDto);
    Employee findById(long primaryKey) ;
    List<Employee> findAll();
    void delete(long primaryKey);
    void update(long id , EmployeeRequestDto employeeRequestDto);
    boolean existsById(long primaryKey);
}
