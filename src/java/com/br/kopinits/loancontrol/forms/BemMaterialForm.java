package com.br.kopinits.loancontrol.forms;

/**
 *
 * @author Markus
 */
public class BemMaterialForm extends LCForm {

    private String descricao;
    private String estado;
    private String idCategoria;
    private String descCategoria;
    private int campoPesquisa = 0;

    public int getCampoPesquisa() {
        return campoPesquisa;
    }

    public void setCampoPesquisa(int campoPesquisa) {
        this.campoPesquisa = campoPesquisa;
    }

    public String getDescCategoria() {
        return descCategoria;
    }

    public void setDescCategoria(String descCategoria) {
        this.descCategoria = descCategoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
