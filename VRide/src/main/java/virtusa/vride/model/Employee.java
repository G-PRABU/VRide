package virtusa.vride.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Employee {

	@Id
	private String empId;
	
	@NotNull
	private String empName;
	
	@NotNull
	private String empEmail;
	
	@Temporal(TemporalType.DATE)
	private Date empDOB;
	
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	
	public void setEmpDOB(Date empDOB) {
		this.empDOB = empDOB;
	}
	
	public String getEmpId() {
		return empId;
	}
	
	public String getEmpName() {
		return empName;
	}
	
	public String getEmpEmail() {
		return empEmail;
	}
	
	public Date getEmpDOB() {
		return empDOB;
	}
	
}
