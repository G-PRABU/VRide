package virtusa.vride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.vride.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
