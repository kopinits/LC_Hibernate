/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.negocio;

import com.br.kopinits.loancontrol.beans.BemMaterial;
import com.br.kopinits.loancontrol.beans.Pessoa;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.beans.Venda;
import com.br.kopinits.loancontrol.db.SelectObjectException;
import com.br.kopinits.loancontrol.db.dao.LCNegocio;
import com.br.kopinits.loancontrol.db.dao.impl.VendaDAOImpl;
import java.util.Collection;

/**
 *
 * @author Markus
 */
public class VendaNegocio extends LCNegocio {

    public static final int CAMPO_DESC_BEM = 0;
    public static final int CAMPO_NM_COMPRADOR = 1;
    public static final int CAMPO_NM_PESSOA = 2;

    public VendaNegocio(Usuario usr) {
        super(new VendaDAOImpl(usr));
    }

    public Collection filtrar(String valorPesquisa, int campoPesquisa) throws SelectObjectException {
        Venda filtro = new Venda();        
      
        switch (campoPesquisa) {
            case CAMPO_DESC_BEM:
                filtro.setBemMaterial(new BemMaterial());
                filtro.getBemMaterial().setDescricao(valorPesquisa);
                break;
            case CAMPO_NM_COMPRADOR:
                filtro.setNomeComprador(valorPesquisa);
                break;
            case CAMPO_NM_PESSOA:
               filtro.setPessoa(new Pessoa());
               filtro.getPessoa().setNome(valorPesquisa);
                break;
        }
        return selectMetodoAsColecao("selectFiltro", filtro, Venda.class);
    }
}
