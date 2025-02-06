package com.betrybe.Clinica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String rementente;

  public String sendEmail(String destinario, String assunto, String mensagem) {
    try {
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      simpleMailMessage.setFrom(rementente);
      simpleMailMessage.setTo(destinario);
      simpleMailMessage.setSubject(assunto);
      simpleMailMessage.setText(mensagem);
      mailSender.send(simpleMailMessage);
      return "Email enviado com sucesso";
    } catch(Exception e) {
      return "Erro ao tentar enviar email " + e.getLocalizedMessage();
    }
  }
}
