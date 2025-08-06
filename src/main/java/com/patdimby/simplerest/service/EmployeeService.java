package com.patdimby.simplerest.service;

import java.util.List;

import com.patdimby.simplerest.model.Employee;
import org.springframework.data.domain.Page;


public interface EmployeeService {
    
	List<Employee> getAllEmployees();

    Employee saveEmployee(Employee employee);

    Employee getEmployeeById(long id);

    void deleteEmployeeById(long id);

    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
