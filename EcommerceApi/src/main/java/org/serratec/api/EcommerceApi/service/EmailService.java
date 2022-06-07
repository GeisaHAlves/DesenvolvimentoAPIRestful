package org.serratec.api.EcommerceApi.service;

import java.util.Optional;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.serratec.api.EcommerceApi.DTO.PedidoDTO;
import org.serratec.api.EcommerceApi.exception.EmailException;
import org.serratec.api.EcommerceApi.exception.PedidoException;
import org.serratec.api.EcommerceApi.model.Cliente;
import org.serratec.api.EcommerceApi.model.Pedido;
import org.serratec.api.EcommerceApi.model.Produto;
import org.serratec.api.EcommerceApi.model.VendasItem;
import org.serratec.api.EcommerceApi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	
	@Autowired
	ClienteRepository clienteRepository;
		
	public JavaMailSender javaMailSender() {
	
	JavaMailSenderImpl enviarEmail = new JavaMailSenderImpl();
	Properties prop = new Properties();
	
	enviarEmail.setHost(host);
	enviarEmail.setPort(465);
	enviarEmail.setUsername(userName);
	enviarEmail.setPassword(password);
	enviarEmail.setProtocol("smtp");
	enviarEmail.setDefaultEncoding("UTF-8");
	prop.put("mail.smtp.auth", true);
	prop.put("mail.smtp.ssl.enable", true);
	enviarEmail.setJavaMailProperties(prop);

	return enviarEmail;
	}
	
//	public void sendMessage(String to, String subject, String text) {
//
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(emailRemetente);
//		message.setTo(to);
//		message.setSubject(subject);
//		message.setText(text);
//		emailSender.send(message);
//	}
	
	public void emailCliente(PedidoDTO pedidoDTO, Pedido pedido) 
			throws EmailException, MessagingException, PedidoException {

        this.emailSender = javaMailSender();
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
        Optional<Cliente> clienteOpt = clienteRepository.findById(pedidoDTO.getIdCliente());
        String destinatario = clienteOpt.get().getEmail();
        
			
        try {
        	
        	
        	
            helper.setFrom(emailRemetente);
            helper.setTo(destinatario);

            helper.setSubject("Novo pedido");

            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("<html>\r\n"
                            + "<body>\r\n"
                            +"<h1>ECommerce</h1>"
                            +"<h2>Compra finalizada com sucesso</h2>"
                            +"<div>\r\nSeguem as informações da compra:\r\n</div>"
                            +"<div>\r\n PRODUTOS: \n</div>");

            for(VendasItem item : pedido.getItens()) {
            	sBuilder.append("<div>\r\nProduto: " + item.getProduto().getNome()
            			+ " | Quantidade: " + item.getQuantidade() + "</div>");
            }
            
            sBuilder.append(String.format("<div>\n\r\nValor total: R$%.2f"
                            +"</div><div>\nAtt: Equipe do EcommercAPI!!</div>"
                            +"</body>"
                            +"</html>", pedido.getValorTotal()));
            helper.setText(sBuilder.toString(), true);
            emailSender.send(message);
        } catch (Exception e) {
        	throw new EmailException ("Houve erro ao enviar o email " + e.getMessage());
        }
	}
	
	
	
	public void emailProprietario(Produto produto) 
			throws EmailException, MessagingException, PedidoException {

        this.emailSender = javaMailSender();
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
      
        String destinatario = "jeffspaixao@gmail.com";
        
			
        try {
        	        	        	
            helper.setFrom(emailRemetente);
            helper.setTo(destinatario);

            helper.setSubject("Relatório de estoque baixo!");

            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("<html>\r\n"
                            + "<body>\r\n"
                            +"<h1>ECommerce</h1>"
                            +"<h2>Atenção!!</h2>"
                            +"<div>\r\nOs produtos abaixo estão com a quantidade abaixo de 5 unidades!\r\n</div>"
                            +"<div>\r\n PRODUTOS: \n</div>"
                            +produto.getNome() + "\t" + produto.getQtdEstoque()
                            +"</div><div>\nAtt: Equipe do EcommercAPI!!</div>"
                            +"</body>"
                            +"</html>");
            helper.setText(sBuilder.toString(), true);
            emailSender.send(message);

        } catch (Exception e) {
        	throw new EmailException ("Houve erro ao enviar o email " + e.getMessage());
		}
	}
}