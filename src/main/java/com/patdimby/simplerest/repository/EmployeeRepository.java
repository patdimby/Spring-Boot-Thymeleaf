package com.patdimby.simplerest.repository;

import com.patdimby.simplerest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/** This interface represents a repository for employees. */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  /**
   * Find all employees with their departments.
   *
   * @return List of all employees with their departments
   */
  @Query("select e FROM Employee e JOIN FETCH e.department")
  List<Employee> findAllWithDepartments();
}
