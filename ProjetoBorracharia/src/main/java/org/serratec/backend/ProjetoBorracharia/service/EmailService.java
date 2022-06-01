package org.serratec.backend.ProjetoBorracharia.service;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.serratec.backend.ProjetoBorracharia.DTO.ServicoDTO;
import org.serratec.backend.ProjetoBorracharia.exception.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Value("${spring.mail.username}")
	private String userName;

	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.password}")
	private String password;

	@Value("${spring.mail.email.remetente}")
	private String emailRemetente;

	
	public JavaMailSender javaMailSender() {

		JavaMailSenderImpl enviarEmail = new JavaMailSenderImpl();
		Properties prop = new Properties();

		enviarEmail.setHost("smtp.gmail.com");
		enviarEmail.setPort(465);
		enviarEmail.setUsername("serratecRenan@gmail.com");
		enviarEmail.setPassword("!Senha123");
		enviarEmail.setProtocol("smtp");
		enviarEmail.setDefaultEncoding("UTF-8");
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.ssl.enable", true);
		enviarEmail.setJavaMailProperties(prop);

		return enviarEmail;

	}

	public void sendMessage(String to, String subject, String text) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailRemetente);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	public void emailTeste(ServicoDTO servicoDTO) throws MessagingException, EmailException {

		this.emailSender = javaMailSender();
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		try {
			helper.setFrom("serratecRenan@gmail.com");
			helper.setTo(emailRemetente);

			helper.setSubject("Borracharia Service");

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("<html>Cartao criado com sucesso\r\n" 
					+ "<body>" 
					+ "<div>" + "Servico" + "</div>"
					+ "***************" 
					+"<div>" + "IdServico: " + servicoDTO.getIdCarro()
					+"<div>" + "IdServico: " + servicoDTO.getIdServico()
					+ "<div>" + "Servico Prestado: " + servicoDTO.getServicoPrestado() 
					+ "<div>" + "Data do Servico: " + servicoDTO.getDataServico()
					+ "</div>" + "Valor: " + servicoDTO.getValor() 
					+ "</div>" 
					+ "      ****** O F I C I N A ******      "
					+ "_______________________________________"
					+ "**************<HELI/CODE/>*************" 
					+ "</div>" 
					+ "</body>" 
					+ "</html>\r\n");

			helper.setText(sBuilder.toString(), true);

			emailSender.send(message);

		} catch (Exception e) {
			throw new EmailException("Houve erro ao enviar o email" + e.getMessage());
		}

	}

}

