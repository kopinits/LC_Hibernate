/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.negocio;

import com.br.kopinits.loancontrol.beans.BemMaterial;
import com.br.kopinits.loancontrol.beans.Categoria;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.db.SelectObjectException;
import com.br.kopinits.loancontrol.db.dao.LCNegocio;
import com.br.kopinits.loancontrol.db.dao.impl.BemMaterialDAOImpl;
import java.util.Collection;

/**
 *
 * @author Markus
 */
public class BemMaterialNegocio extends LCNegocio {

    public BemMaterialNegocio(Usuario usr) {
        super(new BemMaterialDAOImpl(usr));
    }

    public Collection filtrar(String nomePesquisa, int campoPesquisa) throws SelectObjectException {
        BemMaterial filtro = new BemMaterial();
        switch (campoPesquisa) {
            case 0:
                filtro.setDescricao(nomePesquisa);
                break;
            case 1:
                filtro.setCategoria(new Categoria());
                filtro.getCategoria().setDescricao(nomePesquisa);
                break;
            case 2:
                filtro.setEstado(nomePesquisa);
                break;
        }
        return selectMetodoAsColecao("selectFiltro", filtro, BemMaterial.class);
    }

    public Collection selectAllCombo() throws SelectObjectException {
        return selectMetodoAsColecao("selectCombo", "", String.class);
    }

}
