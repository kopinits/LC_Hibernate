package com.br.kopinits.loancontrol.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Markus
 */
public class LoginForm extends LCForm {

    private String login;
    private String senha;
    private String repeteSenha;
    private String nome;
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getLogin() == null || getLogin().length() < 1) {
            errors.add("login", new ActionMessage("error.login.obrigatorio"));
        }
        if (getSenha() == null || getSenha().length() < 1) {
            errors.add("senha", new ActionMessage("error.senha.obrigatorio"));
        }
        return errors;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the repeteSenha
     */
    public String getRepeteSenha() {
        return repeteSenha;
    }

    /**
     * @param repeteSenha the repeteSenha to set
     */
    public void setRepeteSenha(String repeteSenha) {
        this.repeteSenha = repeteSenha;
    }
}
