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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Markus Kopinits
 */
@Entity
@Table(name = "venda")
public class Venda extends LCEntity {

    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "bema_sq_bema")
    private BemMaterial bemMaterial;

    @Column(name = "vend_vl_valor")
    private Double valor;

    @Column(name = "vend_dt_venda")
    @Temporal(value = TemporalType.DATE)
    private Date dataVenda;

    @Column(name = "vend_tx_motivo")
    private String motivoVenda;

    @ManyToOne
    @JoinColumn(name = "pess_sq_pess")
    private Pessoa pessoa;

    @Column(name = "vend_nm_comprador")
    private String nomeComprador;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "vend_sq_vend")
    @SequenceGenerator(name = "vend_sq_vend", sequenceName = "vend_sq_vend")
    @Column(name = "vend_sq_vend")
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
        return null;
    }

    @Override
    public void setDataAtualizacao(Date dataAtualizacao) {

    }

    public BemMaterial getBemMaterial() {
        return bemMaterial;
    }

    public void setBemMaterial(BemMaterial bemMaterial) {
        this.bemMaterial = bemMaterial;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }
}
