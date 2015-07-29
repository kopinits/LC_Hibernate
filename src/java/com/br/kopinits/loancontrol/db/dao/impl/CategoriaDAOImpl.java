/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.db.dao.impl;

import com.br.kopinits.loancontrol.beans.Categoria;
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
public class CategoriaDAOImpl extends LCDAOImpl {

    public CategoriaDAOImpl(Usuario usr) {
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

   
    public Collection selectFiltro(Categoria filtro) throws SelectObjectException {
        Criteria criteria = createCriteria();
        filtroPorDescricao(filtro, criteria);
        return criteria.list();
    }

    private void filtroPorDescricao(Categoria filtro, Criteria criteria) {
        if (StringUtils.isNotBlank(filtro.getDescricao())){
            criteria.add(Restrictions.ilike("c.descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
        }
    }

    private Criteria createCriteria() throws HibernateException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Categoria.class, "c");
        criteria.createAlias("usuario", "u");
        criteria.add(Restrictions.eq("u.id", getUsrLogado().getId()));
        return criteria;
    }
}
