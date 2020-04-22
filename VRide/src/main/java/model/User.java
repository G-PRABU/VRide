package model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {
 
	@OneToOne
	private Employee employee;
	
	@NotNull
	private String userPassword;
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = new BCryptPasswordEncoder().encode(userPassword);
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public boolean isMatches(String password) {
		return new BCryptPasswordEncoder().matches(password, userPassword);
	}
}
