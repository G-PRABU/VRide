package virtusa.vride.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.vride.model.Pooling;
import virtusa.vride.model.VirtusaBranch;

public interface PoolingRepository extends JpaRepository<Pooling,Long>{
    public Collection<Pooling> findByDestinationLocation(VirtusaBranch virtusaBranch);
    public Pooling findByPoolingId(Long id);
}
