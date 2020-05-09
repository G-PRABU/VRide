package virtusa.vride.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import virtusa.vride.model.Employee;

@Service
public class OtpService {
	
	@Autowired
    private OtpGenerator otpGenerator; 
	
	@Autowired 
	private NotificationMailService notificationMailService;
	
	public Integer generateOtp(Employee employee) {
		String key = employee.getEmpId();
		if(otpGenerator.isGenerated(key)) {
			return -1;
		}
		Integer otp = otpGenerator.generateOTP(key);
		try {
		    notificationMailService.sendOTPNotificationMail(employee, otp);
		}catch(MailException e){
			return -1;
		}
		return otp;
	}
	
	public Boolean validateOTP(String key, Integer otp) {
		Integer cacheOtp = otpGenerator.getOTPByKey(key);
		if(cacheOtp.equals(otp)) {
			otpGenerator.clearOTPCache(key);
			return true;
		} else {
			return false;
		}
	}
}
