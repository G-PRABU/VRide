package virtusa.vride.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Pooling {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long poolingId;
	
	@ManyToOne
	private Location startLocation;
	
	@NotNull
	private Instant startTime;
	
	@NotNull
	private Boolean withReturn;
	
	private Instant returnTime;
	
	@NotNull
	private Float costPerHead;
	
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
	
	public void setCostPerHead(Float costPerHead) {
		this.costPerHead = costPerHead;
	}
	
	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}
	
	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}
	
	public void setWithReturn(Boolean withReturn) {
		this.withReturn = withReturn;
	}
	
	public void setReturnTime(Instant returnTime) {
		this.returnTime = returnTime;
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
	
	public Float getCostPerHead() {
		return costPerHead;
	}
	
	public Location getStartLocation() {
		return startLocation;
	}
	
	public Instant getStartTime() {
		return startTime;
	}
	
	public Boolean getWithReturn() {
		return withReturn;
	}
	
	public Instant getReturnTime() {
		return returnTime;
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
	
	public void riderCancelled() {
		availableSeats++;
	}
}
