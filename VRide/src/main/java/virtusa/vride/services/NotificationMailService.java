package virtusa.vride.services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import virtusa.vride.model.Employee;
import virtusa.vride.model.Pooling;
import virtusa.vride.model.Rider;


@Service
public class NotificationMailService {

	@Autowired
	private JavaMailSender javaMailSender;		
	
	private static final String FROM_MAIL = "VRIDE";
	
	private static final String VRIDE_SUBJECT_OTP = "VRIDE OTP";
	
	private static final String VRIDE_SUBJECT_CANCELLATION = "VRIDE CANCELLATION";

	private static final String VRIDE_SUBJECT_MODIFIED = "VRIDE MODIFIED";
	
	private static final String VRIDE_SUBJECT_BOOKING = "VRIDE BOOKED";
	
	public void sendOTPNotificationMail(Employee employee, Integer otp) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(employee.getEmpEmail());
		mail.setFrom(FROM_MAIL);
		mail.setSubject(VRIDE_SUBJECT_OTP);
		mail.setText(employee.getEmpName()+" Vride OTP : "+otp
				+"\n\nNote : This OTP will expire with 5 mins.");
		javaMailSender.send(mail);
	}
	
	public void sendPoolingModifiedMail(Pooling pooling,Collection<Rider> riders) {
	    riders.stream().forEach(r->{
	    	SimpleMailMessage mail = new SimpleMailMessage();
		    mail.setTo(r.getEmployee().getEmpEmail());
		    mail.setFrom(FROM_MAIL);
		    mail.setSubject(VRIDE_SUBJECT_MODIFIED);
		    mail.setText(r.getEmployee().getEmpName()+" Your ride on "+ Date.from(r.getPooling().getStartTime()) + "has been modified by provider.  please check "
		    		+ "your ride detailes for conformation.");
		    javaMailSender.send(mail);
	    });
	    SimpleMailMessage mail = new SimpleMailMessage();
	    mail.setTo(pooling.getEmployee().getEmpEmail());
	    mail.setFrom(FROM_MAIL);
	    mail.setSubject(VRIDE_SUBJECT_MODIFIED);
	    mail.setText(pooling.getEmployee().getEmpName()+" Your ride on "+ Date.from(pooling.getStartTime()) + "has been modified.");
	    javaMailSender.send(mail);
	}
	
	public void sendPoolingCancellationMail(Pooling pooling,Collection<Rider> riders) {
	    riders.stream().forEach(r->{
	    	SimpleMailMessage mail = new SimpleMailMessage();
		    mail.setTo(r.getEmployee().getEmpEmail());
		    mail.setFrom(FROM_MAIL);
		    mail.setSubject(VRIDE_SUBJECT_CANCELLATION);
		    mail.setText(r.getEmployee().getEmpName()+" Your ride on "+ Date.from(r.getPooling().getStartTime()) + "has been cancelled by provider.");
		    javaMailSender.send(mail);
	    });
	    SimpleMailMessage mail = new SimpleMailMessage();
	    mail.setTo(pooling.getEmployee().getEmpEmail());
	    mail.setFrom(FROM_MAIL);
	    mail.setSubject(VRIDE_SUBJECT_CANCELLATION);
	    mail.setText(pooling.getEmployee().getEmpName()+" Your ride on "+ Date.from(pooling.getStartTime()) + "has been cancelled.");
	    javaMailSender.send(mail);
	}
	
	public void sendRiderConfirmationMail(Rider rider) {
		SimpleMailMessage mailRider = new SimpleMailMessage();
	    mailRider.setTo(rider.getEmployee().getEmpEmail());
	    mailRider.setFrom(FROM_MAIL);
	    mailRider.setSubject(VRIDE_SUBJECT_BOOKING);
	    mailRider.setText(rider.getEmployee().getEmpName()+" Your ride on "+ Date.from(rider.getPooling().getStartTime()) + "has been booked.");
	    javaMailSender.send(mailRider);
	    
	    SimpleMailMessage mailProvider = new SimpleMailMessage();
	    mailProvider.setTo(rider.getPooling().getEmployee().getEmpEmail());
	    mailProvider.setFrom("Vride");
	    mailProvider.setSubject(VRIDE_SUBJECT_BOOKING);
	    mailProvider.setText(rider.getPooling().getEmployee().getEmpName()+" Your ride on "+ Date.from(rider.getPooling().getStartTime()) + "has been booked by rider.");
	    javaMailSender.send(mailProvider);
	}
	
	public void sendRiderCancelationMail(Rider rider) {
		SimpleMailMessage mailRider = new SimpleMailMessage();
	    mailRider.setTo(rider.getEmployee().getEmpEmail());
	    mailRider.setFrom(FROM_MAIL);
	    mailRider.setSubject(VRIDE_SUBJECT_CANCELLATION);
	    mailRider.setText(rider.getEmployee().getEmpName()+" Your ride on "+ Date.from(rider.getPooling().getStartTime()) + "has been cancelled.");
	    javaMailSender.send(mailRider);
	    
	    SimpleMailMessage mailProvider = new SimpleMailMessage();
	    mailProvider.setTo(rider.getPooling().getEmployee().getEmpEmail());
	    mailProvider.setFrom(FROM_MAIL);
	    mailProvider.setSubject(VRIDE_SUBJECT_CANCELLATION);
	    mailProvider.setText(rider.getPooling().getEmployee().getEmpName()+" Your ride on "+ Date.from(rider.getPooling().getStartTime()) + "has been cancelled by rider.");
	    javaMailSender.send(mailProvider);
	}
}
