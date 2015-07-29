/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author Markus
 */
public abstract class LCEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract Date getDataAtualizacao();

    public abstract void setDataAtualizacao(Date dataAtualizacao);

}
