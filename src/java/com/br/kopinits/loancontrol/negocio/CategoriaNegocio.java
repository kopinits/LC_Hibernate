/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.negocio;

import com.br.kopinits.loancontrol.beans.Categoria;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.db.SelectObjectException;
import com.br.kopinits.loancontrol.db.dao.LCNegocio;
import com.br.kopinits.loancontrol.db.dao.impl.CategoriaDAOImpl;
import java.util.Collection;

/**
 *
 * @author Markus
 */
public class CategoriaNegocio extends LCNegocio {

    public CategoriaNegocio(Usuario usr) {
        super(new CategoriaDAOImpl(usr));
    }

    public Collection filtrar(String nomePesquisa) throws SelectObjectException {
        Categoria filtro = new Categoria();
        filtro.setDescricao(nomePesquisa);
        return selectMetodoAsColecao("selectFiltro", filtro, Categoria.class);
    }
}
