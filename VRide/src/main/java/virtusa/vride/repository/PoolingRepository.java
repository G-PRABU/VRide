package virtusa.vride.repository;

import java.time.Instant;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import virtusa.vride.model.Employee;
import virtusa.vride.model.Pooling;
import virtusa.vride.model.VirtusaBranch;

public interface PoolingRepository extends JpaRepository<Pooling,Long>{
    
	@Query("SELECT p FROM Pooling p WHERE p.destinationLocation = ?1 AND p.availableSeats > 0 and p.startTime > ?2 ORDER BY costPerHead")
	public Collection<Pooling> findByDestinationLocation(VirtusaBranch virtusaBranch,Instant current);
   
	public Pooling findByPoolingId(Long id);
    
	@Query("SELECT p FROM Pooling p WHERE p.employee = ?1 ORDER BY p.startTime DESC")
	public Collection<Pooling> findByEmployee(Employee employee);
	
}
