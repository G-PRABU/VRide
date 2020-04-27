package virtusa.vride.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
	
    @OneToOne
	private Employee employee;
	
    @NotNull
	private String userPassword;
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public boolean isMatches(String password) {
		return userPassword.equals(password);
	}
}
