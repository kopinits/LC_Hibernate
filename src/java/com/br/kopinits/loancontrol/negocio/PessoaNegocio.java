/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.negocio;

import com.br.kopinits.loancontrol.beans.Pessoa;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.db.SelectObjectException;
import com.br.kopinits.loancontrol.db.dao.LCNegocio;
import com.br.kopinits.loancontrol.db.dao.impl.PessoaDAOImpl;
import java.util.Collection;

/**
 *
 * @author Markus
 */
public class PessoaNegocio extends LCNegocio {

    public PessoaNegocio(Usuario usr) {
        super(new PessoaDAOImpl(usr));
    }

    public Collection filtrar(String valorPesquisa, int campoPesquisa) throws SelectObjectException {
        Pessoa filtro = new Pessoa();
        switch (campoPesquisa) {
            case 0:
                filtro.setNome(valorPesquisa);
                break;
            case 1:
                filtro.setEmail(valorPesquisa);
                break;
            case 2:
                filtro.setTelefone(valorPesquisa);
                break;
            case 3:
                filtro.setCelular(valorPesquisa);
                break;
        }
        return selectMetodoAsColecao("selectFiltro", filtro, Pessoa.class);
    }
}
