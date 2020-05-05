package virtusa.vride.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import virtusa.vride.model.Pooling;
import virtusa.vride.model.Rider;
import virtusa.vride.model.VirtusaBranch;
import virtusa.vride.repository.EmployeeRepository;
import virtusa.vride.repository.PoolingRepository;
import virtusa.vride.repository.RiderRepository;
import virtusa.vride.repository.VirtusaBranchRepository;

@RestController
@RequestMapping("/api")
public class PoolingController {

	@Autowired
	private PoolingRepository poolingRepository;
	
	@Autowired
	private VirtusaBranchRepository virtusaBranchRepository;
	
    @Autowired
    private RiderRepository riderRepository;
    
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@GetMapping("/pooling/destination")
	public Collection<VirtusaBranch> getDistination() {
		return virtusaBranchRepository.findAll();
	}
	
    @PostMapping("/add/pooling")
    public ResponseEntity<Pooling> createPooling(@Valid @RequestBody Pooling pooling) throws URISyntaxException{
        Pooling result = poolingRepository.save(pooling);
        return ResponseEntity.created(new URI("/api/pooling" + result.getPoolingId())).body(result); 
    }
    
    @GetMapping("/poolings/{id}")
    public Collection<Pooling> getPoolings(@PathVariable Long id){
    	return poolingRepository.findByDestinationLocation(virtusaBranchRepository.findByBranchId(id));
    }
    
    @GetMapping("/poolings/{empid}")
    public Collection<Pooling> getUserPoolings(@PathVariable String empid){
    	return poolingRepository.findByEmployee(employeeRepository.findByEmpId(empid));
    }
    
    @PutMapping("/update/pooling/")
    public ResponseEntity<Pooling> updatePooling(@Valid @RequestBody Pooling pooling){
    	Pooling result = poolingRepository.save(pooling);
    	return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("/delete/pooling/{id}")
    public ResponseEntity<?> deletePooing(@PathVariable Long id){
    	poolingRepository.deleteById(id);
    	return ResponseEntity.ok().build();
    }
    
    @PostMapping("/add/rider")
    public ResponseEntity<Pooling> createRider(@Valid @RequestBody Pooling pooling) throws URISyntaxException{
        Pooling result = poolingRepository.save(pooling);
        return ResponseEntity.created(new URI("/api/pooling" + result.getPoolingId())).body(result); 
    }
    
    @GetMapping("/rider/{id}")
    public Collection<Rider> getRiders(@PathVariable Long id){
    	return riderRepository.findByPooling(poolingRepository.findByPoolingId(id));
    }
    
    @PutMapping("/update/rider/")
    public ResponseEntity<Rider> updateRider(@Valid @RequestBody Rider rider){
    	Rider result = riderRepository.save(rider);
    	return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("/delete/rider/{id}")
    public ResponseEntity<?> deleteRider(@PathVariable Long id){
    	riderRepository.deleteById(id);
    	return ResponseEntity.ok().build();
    }
}
