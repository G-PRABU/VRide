package model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Pooling {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long poolingId;
	
	@ManyToOne
	private Location startLocation;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp startTime;
	
	@ManyToOne
	private VirtusaBranch destinationLocation;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private Car car;
	
	@NotNull
	private Integer availableSeats;
	
	public void setPoolingId(Long poolingId) {
		this.poolingId = poolingId;
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
	
	public void setCar(Car car) {
		this.car = car;
	}
	
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	
	public Long getPoolingId() {
		return poolingId;
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
	
	public Car getCar() {
		return car;
	}
	
	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void riderBooked() {
		availableSeats--;
	}
}
