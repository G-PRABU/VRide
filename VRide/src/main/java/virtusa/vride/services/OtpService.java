package virtusa.vride.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {
	
	@Autowired
    private OtpGenerator otpGenerator; 
	
	public Integer generateOtp(String key) {
		if(otpGenerator.isGenerated(key)) {
			return -1;
		}
		return otpGenerator.generateOTP(key);
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
