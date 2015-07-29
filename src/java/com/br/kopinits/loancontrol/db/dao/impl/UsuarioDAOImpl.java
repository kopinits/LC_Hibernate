/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.db.dao.impl;

import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.db.SelectObjectException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Markus
 */
public class UsuarioDAOImpl extends LCDAOImpl {

     public UsuarioDAOImpl(Usuario usr) {
        super(usr);
    }

    @Override
    public Object select(Long id) throws SelectObjectException {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("id", id));
        return criteria.uniqueResult();

    }

    public Usuario selectUsuarioLogin(Usuario usr) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("login", usr.getLogin()));
        criteria.add(Restrictions.eq("senha", usr.getSenha()));
        return (Usuario)criteria.uniqueResult();
    }

    public void updateSenha(Usuario usr) {

    }

    public void resetarSenha(Usuario usr) {

    }

    public Usuario selectLogin(String login) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("login", login));
        return (Usuario)criteria.uniqueResult();

    }

    private Criteria createCriteria() throws HibernateException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        return criteria;
    }

    @Override
    public List selectAll() throws SelectObjectException {
        return null;
    }
}
