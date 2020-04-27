package virtusa.vride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.vride.model.VirtusaBranch;

public interface VirtusaBranchRepository extends JpaRepository<VirtusaBranch,Long>{
    public VirtusaBranch findByBranchId(Long id);
}
