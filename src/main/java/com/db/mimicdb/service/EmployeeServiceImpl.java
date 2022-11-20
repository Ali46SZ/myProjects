package com.db.mimicdb.service;

import com.db.mimicdb.dao.EmployeeDao;
import com.db.mimicdb.model.Employee;
import com.db.mimicdb.model.EmployeeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee save(EmployeeRequestDto employeeRequestDto) {
        return employeeDao.save(employeeRequestDto);
    }

    @Override
    public Employee findById(long primaryKey)  {
      return employeeDao.findById(primaryKey);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public void delete(long primaryKey) {
        employeeDao.delete(primaryKey);
    }

    @Override
    public void update(long id , EmployeeRequestDto employeeRequestDto)
    {
        employeeDao.update(id,employeeRequestDto);
    }

    @Override
    public boolean existsById(Employee primaryKey) {
        return false;
    }
}
