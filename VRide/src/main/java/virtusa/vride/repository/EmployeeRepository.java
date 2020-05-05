package virtusa.vride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.vride.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,String>{
    public Employee findByEmpId(String id);
}