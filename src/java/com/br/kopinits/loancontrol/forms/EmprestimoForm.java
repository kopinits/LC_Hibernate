package com.br.kopinits.loancontrol.forms;

/**
 *
 * @author Markus
 */
public class EmprestimoForm extends LCForm {

    private String idPessoa;
    private String idBem;
    private String estado;
    private Boolean isPessoa=Boolean.TRUE;
    private String nomePessoa;
    private String nomeBem;
    private Integer todos = Integer.valueOf(1);

    public Integer getTodos() {
        return todos;
    }

    public void setTodos(Integer todos) {
        this.todos = todos;
    }

    public String getNomeBem() {
        return nomeBem;
    }

    public void setNomeBem(String nomeBem) {
        this.nomeBem = nomeBem;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public Boolean getIsPessoa() {
        return isPessoa;
    }

    public void setIsPessoa(Boolean isPessoa) {
        this.isPessoa = isPessoa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdBem() {
        return idBem;
    }

    public void setIdBem(String idBem) {
        this.idBem = idBem;
    }

    public String getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }
}
