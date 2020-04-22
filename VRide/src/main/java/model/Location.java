package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long locationId;
    
    @NotNull
    private String locationName;
    
    @NotNull
    private Double locationLatitude;
    
    @NotNull
    private Double locationLongitude;
    
    @ManyToOne
    private Employee employee;
    
    public void setLocationId(Long locationId) {
    	this.locationId = locationId;
    }
    
    public void setLocationName(String locationName) {
    	this.locationName = locationName;
    }
    
    public void setLocationLatitude(Double locationLatitude) {
    	this.locationLatitude = locationLatitude;
    }
    
    public void setLocationLongitude(Double locationLongitude) {
    	this.locationLongitude = locationLongitude;
    }
    
    public void setEmployee(Employee employee) {
    	this.employee = employee;
    }
    
    public Long getLocationId() {
    	return locationId;
    }
    
    public String getLocationName() {
    	return locationName;
    }
    
    public Double getLocationLatitude() {
    	return locationLatitude;
    }
    
    public Double getLocationLongitude() {
    	return locationLongitude;
    }
    
    public Employee getEmployee() {
    	return employee;
    }
}
