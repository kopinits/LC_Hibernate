package com.br.kopinits.loancontrol.forms;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author Markus
 */
public class LCForm extends ActionForm{
    private Long id;
    private String acao;
    private String nomePesquisa;

    public String getNomePesquisa() {
        return nomePesquisa;
    }

    public void setNomePesquisa(String nomePesquisa) {
        this.nomePesquisa = nomePesquisa;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
}
