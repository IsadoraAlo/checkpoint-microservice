package com.br.fiap.ativo.service;

import java.time.LocalDateTime;

import com.br.fiap.ativo.model.EmailModel;
import com.br.fiap.ativo.model.StatusEmail;
import com.br.fiap.ativo.repository.EmailRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) {

        log.info("Enviando email");
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

            emailSender.send(message);
            emailModel.setStatusEmail(StatusEmail.SEND);

            log.info("Email enviado com sucesso");
        }catch (Exception e){
            log.info("Nao foi possivel enviar email!");
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }finally {
            return emailRepository.save(emailModel);
        }

    }
}
