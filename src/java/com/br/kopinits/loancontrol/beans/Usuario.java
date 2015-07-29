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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Markus
 */
@Entity
@Table(name = "usuario")
public class Usuario extends LCEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "usua_tx_login")
    private String login;
    
    @Column(name = "usua_tx_senha")
    private String senha;
    
    @Column(name = "usua_tx_nome")
    private String nome;
    
    @Column(name = "usua_tx_email")
    private String email;

    @Column(name = "usua_dt_timestamp")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_usua_sq_usua")
    @SequenceGenerator(name = "seq_usua_sq_usua", sequenceName = "seq_usua_sq_usua")
    @Column(name = "usua_sq_usuario")
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
}
