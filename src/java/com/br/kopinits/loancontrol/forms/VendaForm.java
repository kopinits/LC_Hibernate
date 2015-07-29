package com.br.kopinits.loancontrol.forms;

import java.util.Date;

/**
 *
 * @author Markus
 */
public class VendaForm extends LCForm {

    private Long idBem;
    private Double valor;
    private Date dataVenda;
    private String motivoVenda;
    private Long idPessoa;
    private String nomeComprador;
    private String nomePessoa;
    private String descBem;
    private String descCategoria;
    private int campoPesquisa = 0;

    public int getCampoPesquisa() {
        return campoPesquisa;
    }

    public void setCampoPesquisa(int campoPesquisa) {
        this.campoPesquisa = campoPesquisa;
    }
    

    public Long getIdBem() {
        return idBem;
    }

    public void setIdBem(Long idBem) {
        this.idBem = idBem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getMotivoVenda() {
        return motivoVenda;
    }

    public void setMotivoVenda(String motivoVenda) {
        this.motivoVenda = motivoVenda;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getDescBem() {
        return descBem;
    }

    public void setDescBem(String descBem) {
        this.descBem = descBem;
    }

    public String getDescCategoria() {
        return descCategoria;
    }

    public void setDescCategoria(String descCategoria) {
        this.descCategoria = descCategoria;
    }
    
    
    
}
