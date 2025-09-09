package com.example.springjdbc.Repository;

import com.example.springjdbc.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByRoleIgnoreCase(String role);
}
