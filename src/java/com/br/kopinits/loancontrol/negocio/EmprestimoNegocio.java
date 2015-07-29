/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.negocio;

import com.br.kopinits.loancontrol.beans.BemMaterial;
import com.br.kopinits.loancontrol.beans.Emprestimo;
import com.br.kopinits.loancontrol.beans.Pessoa;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.db.LCException;
import com.br.kopinits.loancontrol.db.SelectObjectException;
import com.br.kopinits.loancontrol.db.dao.LCNegocio;
import com.br.kopinits.loancontrol.db.dao.impl.EmprestimoDAOImpl;
import java.util.Collection;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Markus
 */
public class EmprestimoNegocio extends LCNegocio {

    public EmprestimoNegocio(Usuario usr) {
        super(new EmprestimoDAOImpl(usr));
    }

    public void processaSaida(Emprestimo emprestimo) throws LCException {
        Emprestimo beanBanco = (Emprestimo)select(emprestimo.getId());
        beanBanco.setDataDevolucao(new Date());
        update(beanBanco);
        if (StringUtils.isNotBlank(emprestimo.getEstadoDevolucao())) {
            BemMaterialNegocio bemNegocio = new BemMaterialNegocio(getDaoImpl().getUsrLogado());
            BemMaterial bem = (BemMaterial) bemNegocio.select(emprestimo.getBemMaterial().getId());
            bem.setEstado(emprestimo.getEstadoDevolucao());
            bemNegocio.update(bem);
        }
    }

    public Collection filtrar(String valorPesquisa, boolean isPessoa, Integer todos) throws SelectObjectException {
        Emprestimo filtro = new Emprestimo();
        if (isPessoa) {
            filtro.setPessoa(new Pessoa());
            filtro.getPessoa().setNome(valorPesquisa);
        } else {
            filtro.setBemMaterial(new BemMaterial());
            filtro.getBemMaterial().setDescricao(valorPesquisa);
        }
        filtro.setOpFiltro(todos);

        return selectMetodoAsColecao("selectFiltro", filtro, Emprestimo.class);
    }
}
