package model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rider {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long riderId;
	
	@ManyToOne
	private Location startLocation;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp startTime;
	
	@ManyToOne
	private VirtusaBranch destinationLocation;
	
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
	
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	public void setDestinationLoction(VirtusaBranch destinationLocation) {
		this.destinationLocation = destinationLocation;
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
	
	public Timestamp getStartTime() {
		return startTime;
	}
	
	public VirtusaBranch getDestinationLocation() {
		return destinationLocation;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public Pooling getPooling() {
		return pooling;
	}
}
