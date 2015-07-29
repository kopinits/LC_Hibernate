/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.beans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Markus Kopinits
 */
@Entity
@Table(name = "pessoa")
public class Pessoa extends LCEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "pess_tx_nome")
    private String nome;
    
    @Column(name = "pess_nr_ddd")
    private String ddd;
    
    @Column(name = "pess_nr_telefone")
    private String telefone;
    
    @Column(name = "pess_nr_ddd_cel")
    private String dddCel;
    
    @Column(name = "pess_nr_celular")
    private String celular;
    
    @Column(name = "pess_tx_email")
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "usua_sq_usuario")
    private Usuario usuario;
    
    @Column(name = "pess_dt_timestamp")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pess_sq_pess")
    @SequenceGenerator(name = "seq_pess_sq_pess", sequenceName = "seq_pess_sq_pess")
    @Column(name = "pess_sq_pess")
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    @Override
    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDddCel() {
        return dddCel;
    }

    public void setDddCel(String dddCel) {
        this.dddCel = dddCel;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
