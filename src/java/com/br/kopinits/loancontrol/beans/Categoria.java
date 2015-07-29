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
@Table(name = "categoria")
public class Categoria extends LCEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "cate_tx_descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name="usua_sq_usuario")
    private Usuario usuario;

    @Column(name = "cate_dt_timestamp")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_cate_sq_cate")
    @SequenceGenerator(name = "seq_cate_sq_cate", sequenceName = "seq_cate_sq_cate")
    @Column(name = "cate_sq_cate")
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
