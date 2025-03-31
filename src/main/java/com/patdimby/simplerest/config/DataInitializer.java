package com.patdimby.simplerest.config;


import com.patdimby.simplerest.model.Department;
import com.patdimby.simplerest.model.Employee;
import com.patdimby.simplerest.repository.DepartmentRepository;
import com.patdimby.simplerest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** This class initializes fake data for the application when it starts. */
@Configuration
public class DataInitializer implements CommandLineRunner {

  /** The department repository. */
  @Autowired private DepartmentRepository departmentRepository;

  /** The employee repository. */
  @Autowired private EmployeeRepository employeeRepository;

  private final Faker faker = new Faker();
  private final Random random = new Random();

  /**
   * This method is called when the application starts.
   *
   * @param args Command line arguments
   */
  @Override
  public void run(String... args) {
    // Always clear existing data before inserting new data
    employeeRepository.deleteAll();
    departmentRepository.deleteAll();

    // Create fake departments
    List<Department> departments = new ArrayList<>();
    for (int i = 1; i <= 50; i++) {
      Department department = new Department();
      department.setName(faker.company().industry());
      departments.add(department);
    }
    departmentRepository.saveAll(departments);

    // Create fake employees with ages
    List<Employee> employees = new ArrayList<>();
    for (int i = 1; i <= 295; i++) {
      Employee employee = new Employee();
      employee.setFirstName(faker.name().firstName());
      employee.setLastName(faker.name().lastName());
      employee.setEmail(faker.internet().emailAddress());
      employee.setAge(random.nextInt(40) + 20); // Assign a random age between 20 and 60

      // Assign a random department to each employee
      employee.setDepartment(departments.get(random.nextInt(departments.size())));
      employees.add(employee);
    }
    employeeRepository.saveAll(employees);

    System.out.println("Fake data initialized successfully, replacing any existing data!");
  }
}
