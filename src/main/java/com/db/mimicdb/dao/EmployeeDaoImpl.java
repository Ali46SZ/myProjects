package com.db.mimicdb.dao;

import com.db.mimicdb.config.Const;
import com.db.mimicdb.exception.NotFoundElement;
import com.db.mimicdb.model.Employee;
import com.db.mimicdb.model.EmployeeRequestDto;
import com.db.mimicdb.util.FileUtil;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@Component
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public Employee save(EmployeeRequestDto employeeRequestDto) {
        Employee employee=new Employee();
        try {
            long id =FileUtil.getLastId();
            employee.setId(id);
            employee.setName(employeeRequestDto.getName());
            employee.setPhone(employeeRequestDto.getPhone());
            employee.setEmail(employeeRequestDto.getEmail());
            FileUtil.Save(employee,id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public  Employee findById(long primaryKey)  {

        Employee employee = null;
        try {
            if (existsById(primaryKey)) {
                employee = (Employee) FileUtil.getObject(primaryKey);
        } else {
                throw new NotFoundElement("Not Found");
        }
        }catch (IOException ex)
        {
          throw new RuntimeException(ex) ;
        }

        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> list =new ArrayList<Employee>();
        Employee employee;
        File f=new File(Const.directoryName);
        File[] allSubFiles=f.listFiles();
        try
        {
            for(File file : allSubFiles ) { 
                employee = (Employee) FileUtil.getObjectByFile(file);
                list.add(employee);

            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }

        return list;
    }
    @Override
    public void delete(long primaryKey) {
        try {
            String pathName = FileUtil.getFullPath(primaryKey);
            File file  = new File(pathName);
            if (file.exists()) {
                file.delete();
            }
            else {
                throw new NotFoundElement("Not Found");
            }
           } catch (IOException ex)
           {
            throw new RuntimeException(ex) ;
           }
    }

    public void update(long id , EmployeeRequestDto employeeRequestDto)
    {
        Employee employee=new Employee();
        try{
        String pathName = FileUtil.getFullPath(id);
        File filename = new File(pathName);
        if (filename.exists()) {
            employee.setId(id);
            employee.setName(employeeRequestDto.getName());
            employee.setPhone(employeeRequestDto.getPhone());
            employee.setEmail(employeeRequestDto.getEmail());
            FileUtil.Save(employee,id);
        }
        else {
            throw new NotFoundElement("Not Found");
        }}catch (IOException ex)
        {
        throw new RuntimeException(ex) ;
        }
    }
    @Override
    public boolean existsById(long primaryKey) {
        try {
            String pathName = FileUtil.getFullPath(primaryKey);
            File filename = new File(pathName);
            if (filename.exists()) {
                System.out.println("The file " + pathName + " exists.");
                return true;
            } else {
                System.out.println("The file " + pathName + " Does Not exist.");
                return false;
                    }
             }catch (Exception ex)
             {
               throw new RuntimeException();
             }
    }

    long getLastId( ) throws IOException {
        return FileUtil.getLastId();
    }
}
