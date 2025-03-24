package com.p1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.p1.service.EmailService;

//   http://localhost:8080/sendEmail?toEmail=anildhuvan@gmail.com&subject=Greetings&body=Hello every one my name anil choudhary


@RestController
public class EmailController {
	@Autowired
	private EmailService emailService;

	@GetMapping("/sendEmail")
	public String sendEmail(@RequestParam String toEmail, @RequestParam String subject, @RequestParam String body) {
		emailService.sendSimpleEmail(toEmail, subject, body);
		return "Email sent successfully to " + toEmail;
	}
}

//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendHtmlEmail(String toEmail, String subject, String htmlBody) throws MessagingException {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//        helper.setFrom("your-email@gmail.com");
//        helper.setTo(toEmail);
//        helper.setSubject(subject);
//        helper.setText(htmlBody, true);  // 'true' for HTML content
//
//        mailSender.send(mimeMessage);
//        System.out.println("HTML Email Sent Successfully...");
//    }
//}


