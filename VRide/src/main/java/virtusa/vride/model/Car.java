package virtusa.vride.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long carId;
	
	@NotNull
	private String carBrand;
	
	@NotNull
	private String carModel;
	
	@ManyToOne
	private Employee employee;

	public void setCarId(Long carId) {
		this.carId = carId;
	}
	
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Long getCarId() {
		return carId;
	}
	
	public String getCarBrand() {
		return carBrand;
	}
	
	public String getCarModel() {
		return carModel;
	}
	
	public Employee getEmployee() {
		return employee;
	}
}
