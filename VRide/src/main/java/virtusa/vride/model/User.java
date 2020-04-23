package virtusa.vride.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
		this.userPassword = new BCryptPasswordEncoder().encode(userPassword);
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public boolean isMatches(String password) {
		return new BCryptPasswordEncoder().matches(password, userPassword);
	}
}
