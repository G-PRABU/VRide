package virtusa.vride.model;

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
    private String locationNickName;
    
    @NotNull 
    private String locationAddress;
    
    @NotNull
    private Double locationLatitude;
    
    @NotNull
    private Double locationLongitude;
    
    @ManyToOne
    private Employee employee;
    
    public void setLocationId(Long locationId) {
    	this.locationId = locationId;
    }
    
    public void setLocationNickName(String locationNickName) {
    	this.locationNickName = locationNickName;
    }
    
    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;	
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
    
    public String getLocationNickName() {
    	return locationNickName;
    }
    
    public String getLocationAddress() {
    	return locationAddress;
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
