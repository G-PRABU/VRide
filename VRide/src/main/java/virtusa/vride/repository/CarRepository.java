package virtusa.vride.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.vride.model.Car;
import virtusa.vride.model.Employee;

public interface CarRepository extends JpaRepository<Car, Long> {
    public Collection<Car> findByEmployee(Employee employee);
}
