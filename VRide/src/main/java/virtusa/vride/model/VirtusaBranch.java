package virtusa.vride.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class VirtusaBranch {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long branchId;
    
    @NotNull
    private String branchName;
    
    @NotNull
    private String branchCity;
    
    @NotNull
    private Double branchLatitude;
    
    @NotNull
    private Double branchLongitude;
    
    public void setBranchId(Long branchId) {
    	this.branchId = branchId;
    }
    
    public void setBranchName(String branchName) {
    	this.branchName = branchName;
    }
    
    public void setBranchCity(String branchCity) {
    	this.branchCity = branchCity;
    }
    
    public void setBranchLatitude(Double branchLatitude) {
    	this.branchLatitude = branchLatitude;
    }
    
    public void setBranchLongitude(Double branchLongitude) {
    	this.branchLongitude = branchLongitude;
    }
    
    public Long getBranchId() {
    	return branchId;
    }
    
    public String getBranchName() {
    	return branchName;
    }
    
    public String getBranchCity() {
    	return branchCity;
    }
    
    public Double getBranchLatitude() {
    	return branchLatitude;
    }
    
    public Double getBranchLongitude() {
    	return branchLongitude;
    }    
}
