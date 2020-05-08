package virtusa.vride.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rider {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long riderId;
	
	@ManyToOne
	private Location startLocation;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private Pooling pooling;
	
	public void setRiderId(Long riderId) {
		this.riderId = riderId;
	}
	
	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void setPooling(Pooling pooling) {
		this.pooling = pooling;
	}
	
	public Long getRiderId() {
		return riderId;
	}
	
	public Location getStartLocation() {
		return startLocation;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public Pooling getPooling() {
		return pooling;
	}
}
