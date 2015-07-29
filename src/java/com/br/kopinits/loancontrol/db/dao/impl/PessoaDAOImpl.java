/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.db.dao.impl;

import com.br.kopinits.loancontrol.beans.Pessoa;
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
public class PessoaDAOImpl extends LCDAOImpl {

    public PessoaDAOImpl(Usuario usr) {
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

    public Collection selectFiltro(Pessoa filtro) throws SelectObjectException {
        Criteria criteria = createCriteria();
        filtroPorNome(filtro, criteria);
        filtroPorEmail(filtro, criteria);
        filtroPorTelefone(filtro, criteria);
        filtroPorCelular(filtro, criteria);
        return criteria.list();
    }

    private void filtroPorNome(Pessoa filtro, Criteria criteria) {
        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("c.nome", filtro.getNome(), MatchMode.ANYWHERE));
        }
    }

    private void filtroPorEmail(Pessoa filtro, Criteria criteria) {
        if (StringUtils.isNotBlank(filtro.getEmail())) {
            criteria.add(Restrictions.ilike("c.email", filtro.getEmail(), MatchMode.ANYWHERE));
        }
    }

    private void filtroPorTelefone(Pessoa filtro, Criteria criteria) {
        if (StringUtils.isNotBlank(filtro.getTelefone())) {
            criteria.add(Restrictions.ilike("c.telefone", filtro.getTelefone(), MatchMode.ANYWHERE));
        }
    }

    private void filtroPorCelular(Pessoa filtro, Criteria criteria) {
        if (StringUtils.isNotBlank(filtro.getCelular())) {
            criteria.add(Restrictions.ilike("c.celular", filtro.getCelular(), MatchMode.ANYWHERE));
        }
    }

    private Criteria createCriteria() throws HibernateException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Pessoa.class, "c");
        criteria.createAlias("usuario", "u");
        criteria.add(Restrictions.eq("u.id", getUsrLogado().getId()));
        return criteria;
    }

}
