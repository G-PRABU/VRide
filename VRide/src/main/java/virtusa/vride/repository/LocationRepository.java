package virtusa.vride.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.vride.model.Employee;
import virtusa.vride.model.Location;

public interface LocationRepository extends JpaRepository<Location,Long>{
    public Collection<Location> findByEmployee(Employee employee);
}
