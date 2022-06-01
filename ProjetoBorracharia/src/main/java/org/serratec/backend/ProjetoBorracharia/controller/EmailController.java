package org.serratec.backend.ProjetoBorracharia.controller;

import javax.mail.MessagingException;

import org.serratec.backend.ProjetoBorracharia.DTO.ServicoDTO;
import org.serratec.backend.ProjetoBorracharia.exception.EmailException;
import org.serratec.backend.ProjetoBorracharia.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/email")
	public void enviarEmail(ServicoDTO servicoDTO) throws EmailException, MessagingException {
		emailService.emailTeste(servicoDTO);
	}
}

