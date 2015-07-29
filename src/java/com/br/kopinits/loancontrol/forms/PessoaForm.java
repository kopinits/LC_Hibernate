package com.br.kopinits.loancontrol.forms;

/**
 *
 * @author Markus
 */
public class PessoaForm extends LCForm {

    private String nome;
    private String ddd;
    private String telefone;
    private String dddCelular;
    private String celular;
    private String email;
    private int campoPesquisa = 0;

    public int getCampoPesquisa() {
        return campoPesquisa;
    }

    public void setCampoPesquisa(int campoPesquisa) {
        this.campoPesquisa = campoPesquisa;
    }


    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getDddCelular() {
        return dddCelular;
    }

    public void setDddCelular(String dddCelular) {
        this.dddCelular = dddCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
