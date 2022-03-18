package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.dto.pessoa.PessoaDTO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;

    private static final String MAIL_TO = "gbdsantos0@gmail.com";

    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender emailSender;

    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(MAIL_TO);
        message.setSubject("TESTE");
        message.setText("Teste\n minha mensagem \n\nAtt,\nEu.");
        emailSender.send(message);
    }

    public void sendWithAttachment() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message,
                true);

        helper.setFrom(from);
        helper.setTo(MAIL_TO);
        helper.setSubject("TESTE");
        helper.setText("Teste\n minha mensagem \n\nAtt,\nEu.");

        File file1 = new File("src/main/images/spodertwins.jpg");

        FileSystemResource file
                = new FileSystemResource(file1);
        helper.addAttachment(file1.getName(), file);

        emailSender.send(message);
    }

    public void sendEmail() {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(MAIL_TO);
            mimeMessageHelper.setSubject("TESTE");
            mimeMessageHelper.setText(geContentFromTemplate(), true);

            log.info("TO DENTRO DO EMAIL");

            emailSender.send(mimeMessageHelper.getMimeMessage());

            log.info("ENVIEI O EMAIL");
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public String geContentFromTemplate() throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", "MeuNome");
        //setar caminho
        fmConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        Template template = fmConfiguration.getTemplate("email-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }

    public void sendEmailToNewUser(PessoaDTO pessoa) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(pessoa.getEmail());
            mimeMessageHelper.setSubject("Cadastro Realizado!");
            mimeMessageHelper.setText(geContentFromTemplateCadastro(pessoa.getNome(), pessoa.getIdPessoa()), true);

            File file1 = new File("src/main/resources/images/spodertwins.jpg");

            FileSystemResource file
                    = new FileSystemResource(file1);
            mimeMessageHelper.addAttachment(file1.getName(), file);

            log.info("TO DENTRO DO EMAIL");

            emailSender.send(mimeMessageHelper.getMimeMessage());

            log.info("ENVIEI O EMAIL");
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public String geContentFromTemplateCadastro(String nome, Integer id) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nome);
        dados.put("id", id);
        dados.put("suporteMail",from);
        //setar caminho
        fmConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        Template template = fmConfiguration.getTemplate("email-cadastro-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }

    public void sendEmailToUpdatedUser(PessoaDTO pessoa) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(pessoa.getEmail());
            mimeMessageHelper.setSubject("Atualização de Cadastro Realizado!");
            mimeMessageHelper.setText(geContentFromTemplateUpdate(pessoa.getNome(), pessoa.getIdPessoa()), true);

            log.info("TO DENTRO DO EMAIL (update)");

            emailSender.send(mimeMessageHelper.getMimeMessage());

            log.info("ENVIEI O EMAIL (update)");
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public String geContentFromTemplateUpdate(String nome, Integer id) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nome);
        dados.put("id", id);
        dados.put("suporteMail",from);
        //setar caminho
        fmConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        Template template = fmConfiguration.getTemplate("email-atualizacao-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }

    public void sendEmailToDeletedUser(PessoaDTO pessoa) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(pessoa.getEmail());
            mimeMessageHelper.setSubject("Remoção de Cadastro Realizado!");
            mimeMessageHelper.setText(geContentFromTemplateDelete(pessoa.getNome(), pessoa.getIdPessoa()), true);

            log.info("TO DENTRO DO EMAIL (delete)");

            emailSender.send(mimeMessageHelper.getMimeMessage());

            log.info("ENVIEI O EMAIL (delete)");
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public String geContentFromTemplateDelete(String nome, Integer id) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nome);
        dados.put("id", id);
        dados.put("suporteMail",from);
        //setar caminho
        fmConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        Template template = fmConfiguration.getTemplate("email-delete-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }

    public void sendEmailMessage(PessoaDTO pessoa, String subject, String message) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(pessoa.getEmail());
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(geContentFromTemplateMessage(pessoa.getNome(), message), true);

            log.info("TO DENTRO DO EMAIL (message)");

            emailSender.send(mimeMessageHelper.getMimeMessage());

            log.info("ENVIEI O EMAIL (message)");
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public String geContentFromTemplateMessage(String nome, String message) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nome);
        dados.put("message", message);
        dados.put("suporteMail",from);
        //setar caminho
        fmConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        Template template = fmConfiguration.getTemplate("email-template-padrao.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
}
