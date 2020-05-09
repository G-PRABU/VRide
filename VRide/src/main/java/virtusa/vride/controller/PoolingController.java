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

import virtusa.vride.model.Pooling;
import virtusa.vride.model.Rider;
import virtusa.vride.model.VirtusaBranch;
import virtusa.vride.repository.EmployeeRepository;
import virtusa.vride.repository.PoolingRepository;
import virtusa.vride.repository.RiderRepository;
import virtusa.vride.repository.VirtusaBranchRepository;
import virtusa.vride.services.NotificationMailService;

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
	
	@Autowired
	private NotificationMailService notificationMailService;
	
	@GetMapping("/pooling/destination")
	public Collection<VirtusaBranch> getDistination() {
		return virtusaBranchRepository.findAll();
	}
	
    @PostMapping("/add/pooling")
    public ResponseEntity<?> createPooling(@Valid @RequestBody Pooling pooling) throws URISyntaxException{
        Pooling result = poolingRepository.save(pooling);
        return ResponseEntity.created(new URI("/api/pooling" + result.getPoolingId())).body(result); 
    }
    
    @GetMapping("/poolings/destination/{id}")
    public Collection<Pooling> getPoolings(@PathVariable Long id){
    	return poolingRepository.findByDestinationLocation(virtusaBranchRepository.findByBranchId(id));
    }
    
    @GetMapping("/poolings/provider/{empid}")
    public Collection<Pooling> getUserPoolings(@PathVariable String empid){
    	return poolingRepository.findByEmployee(employeeRepository.findByEmpId(empid));
    }
    
    @GetMapping("pooling/{id}")
    public ResponseEntity<?> getPooling(@PathVariable Long id){
    	Optional<Pooling> pooling = poolingRepository.findById(id); 
    	if(pooling.isPresent()) {
    		return ResponseEntity.ok().body(pooling.get());
    	} else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }

    @PutMapping("/update/pooling/")
    public ResponseEntity<Pooling> updatePooling(@Valid @RequestBody Pooling pooling){
    	Pooling result = poolingRepository.save(pooling);
    	return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("/delete/pooling/{id}")
    public ResponseEntity<?> deletePooing(@PathVariable Long id){
    	Pooling pooling = poolingRepository.findByPoolingId(id);
    	Collection<Rider> riders = riderRepository.findByPooling(pooling);
    	notificationMailService.sendPoolingCancelationMail(pooling,riders);
    	riders.stream().forEach(r->{
    		riderRepository.deleteById(r.getRiderId());
    	});
    	poolingRepository.deleteById(id);
    	return ResponseEntity.ok().build();
    }
    
    @PostMapping("/add/rider")
    public ResponseEntity<Rider> createRider(@Valid @RequestBody Rider rider) throws URISyntaxException{
        Rider result = riderRepository.save(rider);
        Pooling pooling = result.getPooling();
        pooling.riderBooked();
        poolingRepository.save(pooling);
        notificationMailService.sendRiderConfirmationMail(result);
        return ResponseEntity.created(new URI("/api/rider" + result.getRiderId())).body(result); 
    }
    
    @GetMapping("/rider/{id}")
    public Collection<Rider> getRiders(@PathVariable Long id){
    	return riderRepository.findByPooling(poolingRepository.findByPoolingId(id));
    }
    
    @DeleteMapping("/delete/rider/{id}")
    public ResponseEntity<?> deleteRider(@PathVariable Long id){
    	Rider rider = riderRepository.findById(id).get();
    	Pooling pooling = rider.getPooling();
    	pooling.riderCancelled();
    	poolingRepository.save(pooling);
    	notificationMailService.sendRiderCancelationMail(rider);
    	riderRepository.deleteById(id);
    	return ResponseEntity.ok().build();
    }
}
