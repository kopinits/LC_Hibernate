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
import javax.persistence.Transient;

/**
 *
 * @author Markus Kopinits
 */
@Entity
@Table(name = "emprestimos")
public class Emprestimo extends LCEntity {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn(name = "pess_sq_pess")
    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "bema_sq_bema")
    private BemMaterial bemMaterial;
    
    @Column(name = "empr_dt_saida")
    @Temporal(value = TemporalType.DATE)
    private Date dataSaida;
    
    @Column(name = "empr_dt_devolucao")
    @Temporal(value = TemporalType.DATE)
    private Date dataDevolucao;
    
    @Column(name = "empr_tx_estado_dev")
    private String estadoDevolucao;
    
    @Transient
    private String status;
    
    @Transient
    private int opFiltro;
    
    @Column(name = "empr_dt_timestamp")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_empr_sq_empr")
    @SequenceGenerator(name = "seq_empr_sq_empr", sequenceName = "seq_empr_sq_empr")
    @Column(name = "empr_sq_empr")
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

    public String getStatus() {
        if (dataDevolucao == null) {
            return "Emprestado";
        } else {
            return "Devolvido";
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstadoDevolucao() {
        return estadoDevolucao;
    }

    public void setEstadoDevolucao(String estadoDevolucao) {
        this.estadoDevolucao = estadoDevolucao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public BemMaterial getBemMaterial() {
        return bemMaterial;
    }

    public void setBemMaterial(BemMaterial bemMaterial) {
        this.bemMaterial = bemMaterial;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getOpFiltro() {
        return opFiltro;
    }

    public void setOpFiltro(int opFiltro) {
        this.opFiltro = opFiltro;
    }
    
    
}
