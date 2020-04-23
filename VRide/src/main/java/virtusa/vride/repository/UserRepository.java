package virtusa.vride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.vride.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
