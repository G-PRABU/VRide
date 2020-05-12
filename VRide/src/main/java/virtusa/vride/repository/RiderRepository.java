package virtusa.vride.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.vride.model.Employee;
import virtusa.vride.model.Pooling;
import virtusa.vride.model.Rider;

public interface RiderRepository extends JpaRepository<Rider,Long>{
    public Collection<Rider> findByPooling(Pooling pooling);
    public Collection<Rider> findByEmployee(Employee employee);
}
