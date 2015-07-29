/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.beans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 *
 * @author Markus Kopinits
 */
@Entity
@Table(name = "bem_material")
public class BemMaterial extends LCEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "bema_tx_descricao")
    private String descricao;
    
    @Column(name = "bema_tx_estado")
    private String estado;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cate_sq_cate")
    private Categoria categoria;
    
    @Transient
    private Boolean vendido;
    
    @Column(name = "bema_dt_timestamp")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_bema_sq_bema")
    @SequenceGenerator(name = "seq_bema_sq_bema", sequenceName = "seq_bema_sq_bema")
    @Column(name = "bema_sq_bema")
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

    public Boolean isVendido() {
        return vendido;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
