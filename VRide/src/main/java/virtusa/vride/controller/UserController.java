package virtusa.vride.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import virtusa.vride.model.Car;
import virtusa.vride.model.Employee;
import virtusa.vride.model.Location;
import virtusa.vride.model.User;
import virtusa.vride.repository.CarRepository;
import virtusa.vride.repository.EmployeeRepository;
import virtusa.vride.repository.LocationRepository;
import virtusa.vride.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired 
	private CarRepository carRepository;
	
	@Autowired
	private LocationRepository locationRepository;
    
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/employees")
	Collection<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	@PostMapping("/user/signup/{empid}/{pass}")
	ResponseEntity<?> createUer(@PathVariable String empid,@PathVariable String pass) throws URISyntaxException{
		if(employeeRepository.findById(empid).isPresent()){
			if(!userRepository.findByEmployee(employeeRepository.findByEmpId(empid)).isPresent()) {
		        User user = new User();
		        user.setEmployee(employeeRepository.findByEmpId(empid));
		        user.setUserPassword(pass);
	            User result = userRepository.save(user);
	            return ResponseEntity.created(new URI("/api/employee" + result.getEmployee().getEmpId())).body(result.getEmployee()); 
		
			} else {
			    return new ResponseEntity<>(HttpStatus.FOUND);	
			} 
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/user/login/{empid}/{pass}")
	ResponseEntity<?> userLogin(@PathVariable String empid,@PathVariable String pass) throws URISyntaxException {
	    Optional<User> user = userRepository.findByEmployee(employeeRepository.findByEmpId(empid));
		if(user.isPresent()) {
			if(user.get().isMatches(pass)) {
				return ResponseEntity.created(new URI("/api/employee" + user.get().getEmployee().getEmpId())).body(user.get().getEmployee());
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/employee/{id}")
	ResponseEntity<?> getEmployee(@PathVariable String id){
	    return employeeRepository.findById(id).map(response -> 
	    ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/add/location")
	ResponseEntity<Location> addCar(@Valid @RequestBody Location location) throws URISyntaxException {
		Location result = locationRepository.save(location);
		return ResponseEntity.created(new URI("/api/location/"+result.getLocationId())).body(result);
	}
	
	@PostMapping("/add/car/")
	ResponseEntity<Car> addCar(@Valid @RequestBody Car car) throws URISyntaxException {
		Car result = carRepository.save(car);
		return ResponseEntity.created(new URI("/api/car/"+result.getCarId())).body(result);
	}
	
	@GetMapping("/{empid}/locations")
	Collection<Location> getLocations(@PathVariable String empid){
	    return 	locationRepository.findByEmployee(employeeRepository.findByEmpId(empid));
	}
	
	@PutMapping("update/car/{empid}")
	Collection<Car> updateCar(@PathVariable String empid, @Valid @RequestBody Car car) throws URISyntaxException {
		carRepository.save(car);
		return carRepository.findByEmployee(employeeRepository.findByEmpId(empid));
	}
	
	@GetMapping("/{empid}/cars")
	Collection<Car> getCars(@PathVariable String empid){
	    return 	carRepository.findByEmployee(employeeRepository.findByEmpId(empid));
	}
	
	@DeleteMapping("/car/delete/{empid}/{id}")
	Collection<Car> deleteCar(@PathVariable String empid,@PathVariable Long id){
		carRepository.deleteById(id);
		return carRepository.findByEmployee(employeeRepository.findByEmpId(empid));
	}
}
