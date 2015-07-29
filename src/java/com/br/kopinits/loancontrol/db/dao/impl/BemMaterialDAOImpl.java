/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.db.dao.impl;

import com.br.kopinits.loancontrol.beans.BemMaterial;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.db.SelectObjectException;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Markus
 */
public class BemMaterialDAOImpl extends LCDAOImpl {

    public BemMaterialDAOImpl(Usuario usr) {
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

    public Collection selectFiltro(BemMaterial filtro) throws SelectObjectException {
        Criteria criteria = createCriteria();
        filtroPorDescricao(filtro, criteria);
        filtroPorEstado(filtro, criteria);
        filtroPorCategoria(filtro, criteria);
        return criteria.list();
    }

    private void filtroPorCategoria(BemMaterial filtro, Criteria criteria) {
        if (filtro.getCategoria() != null && StringUtils.isNotBlank(filtro.getCategoria().getDescricao())) {
            criteria.add(Restrictions.ilike("cate.estado", filtro.getCategoria().getDescricao(), MatchMode.ANYWHERE));
        }
    }

    private void filtroPorEstado(BemMaterial filtro, Criteria criteria) {
        if (StringUtils.isNotBlank(filtro.getEstado())) {
            criteria.add(Restrictions.ilike("c.estado", filtro.getEstado(), MatchMode.ANYWHERE));
        }
    }

    private void filtroPorDescricao(BemMaterial filtro, Criteria criteria) {
        if (StringUtils.isNotBlank(filtro.getDescricao())) {
            criteria.add(Restrictions.ilike("c.descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
        }
    }

    public Collection selectCombo(String vazio) throws SelectObjectException {
        String hql = "select be from BemMaterial be inner join be.categoria ca  "
                + " where ca.usuario.id=:userId ";
        hql += " and be.id not in (select empr.bemMaterial.id from Emprestimo empr where empr.dataDevolucao is null)";
        hql += " and be.id not in (select v.bemMaterial.id from Venda v)";
        Session openSession = HibernateUtils.getSessionFactory().openSession();
        Query createQuery = openSession.createQuery(hql);
        createQuery.setLong("userId", getUsrLogado().getId());
        return createQuery.list();
    }

    private Criteria createCriteria() throws HibernateException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(BemMaterial.class, "c");
        criteria.createAlias("c.categoria", "cate");
        criteria.createAlias("cate.usuario", "u");
        criteria.add(Restrictions.eq("u.id", getUsrLogado().getId()));
        return criteria;
    }

}
