package virtusa.vride.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.vride.model.Employee;
import virtusa.vride.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    public Optional<User> findByEmployee(Employee employee);
}
