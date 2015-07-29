/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.db.dao.impl;

import com.br.kopinits.loancontrol.beans.Emprestimo;
import com.br.kopinits.loancontrol.beans.Venda;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.db.SelectObjectException;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Markus
 */
public class VendaDAOImpl extends LCDAOImpl {

    public VendaDAOImpl(Usuario usr) {
        super(usr);
    }

    @Override
    public Object select(Long id) throws SelectObjectException {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("id", id));
        return criteria.uniqueResult();
    }

    @Override
    public List selectAll() throws SelectObjectException {
        Criteria criteria = createCriteria();
        return criteria.list();
    }

    public Collection selectFiltro(Venda filtro) throws SelectObjectException {
        Criteria criteria = createCriteria();
        filtroPorBemMaterial(filtro, criteria);
        filtroPorComprador(filtro, criteria);
        filtroPorPessoa(filtro, criteria);
        return criteria.list();
    }

    private Criteria createCriteria() throws HibernateException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Venda.class, "c");
        criteria.createAlias("c.bemMaterial", "bema");
        criteria.createAlias("bema.categoria", "cate");
        criteria.createAlias("cate.usuario", "u");
        criteria.add(Restrictions.eq("u.id", getUsrLogado().getId()));
        return criteria;
    }

    private void filtroPorComprador(Venda filtro, Criteria criteria) {
        if (StringUtils.isNotBlank(filtro.getNomeComprador())) {
            criteria.add(Restrictions.ilike("c.nomeComprador", filtro.getNomeComprador(), MatchMode.ANYWHERE));
        }
    }

    private void filtroPorPessoa(Venda filtro, Criteria criteria) {
        if (filtro.getPessoa() != null && StringUtils.isNotBlank(filtro.getPessoa().getNome())) {
            criteria.createAlias("c.pessoa", "p");
            criteria.add(Restrictions.ilike("p.nome", filtro.getPessoa().getNome(), MatchMode.ANYWHERE));
        }
    }

    private void filtroPorBemMaterial(Venda filtro, Criteria criteria) {
        if (filtro.getBemMaterial() != null && StringUtils.isNotBlank(filtro.getBemMaterial().getDescricao())) {
            criteria.add(Restrictions.ilike("bema.descricao", filtro.getBemMaterial().getDescricao(), MatchMode.ANYWHERE));
        }
    }
}
