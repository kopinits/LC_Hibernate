/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.utils;

import com.br.kopinits.loancontrol.beans.Usuario;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Markus
 */
public final class EmailUtils {
    
    private EmailUtils(){
        super();
    }

    public static void enviarEmailLogin(Usuario usr) {
        String msg = "Prezado " + usr.getNome() + ", seu cadastro no LoanControl foi realizado com sucesso. Seus dados de acesso são:";
        msg += "login: " + usr.getLogin();
        msg += "senha: " + usr.getSenha();
        enviarEmail("Cadastro no LoanControl", msg, usr.getEmail());
    }

    private static final String patternEmail = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";

    public static Boolean validaEmail(String email) {
        Boolean returnCode = null;

        if (email != null) {
            returnCode = Boolean.valueOf(Pattern.compile(patternEmail).matcher(email).find());
        }

        return returnCode;
    }

    /**
     * Envia um email em txt simples
     *
     * @param assunto Assunto do email
     * @param mensagem mensagem(corpo do email) do email
     * @param destinatario arraylis dos email dos destinatarioa
     * @return verdadeiro caso o email seja enviado com sucesso
     */
    public static boolean enviarEmail(String assunto, String mensagem, String destinatario) {
        boolean retorno = validaEmail(destinatario);
        /*Session session = null;
        MimeMessage message = null;
        try {
            if (retorno) {

                String from = "lc@kopinits.com";
                String host = "mail.kopinits.com";
                Properties props = System.getProperties();

                props.setProperty("mail.smtp.auth", "false");
                props.setProperty("mail.smtp.host", host);
                props.setProperty("mail.smtp.port", "26");
                props.setProperty("mail.smtp.user", from);
                props.setProperty("mail.smtp.ehlo", "false");
                
                session = Session.getDefaultInstance(props, null);
                message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(destinatario));
                message.setSubject(assunto);

                message.setText(mensagem);

                Transport.send(message);
            }
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
*/
        return retorno;
    }
}
