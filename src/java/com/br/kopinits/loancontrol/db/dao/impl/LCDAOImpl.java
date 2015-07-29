/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.db.dao.impl;

import com.br.kopinits.loancontrol.beans.LCEntity;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.db.DBObjectException;
import com.br.kopinits.loancontrol.db.SelectObjectException;
import java.util.List;
import org.apache.log4j.LogManager;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

/**
 *
 * @author Markus
 */
public abstract class LCDAOImpl {

    private final Usuario usrLogado;

    public Usuario getUsrLogado() {
        return usrLogado;
    }

    public LCDAOImpl(Usuario usr) {
        usrLogado = usr;
    }

    public boolean create(LCEntity lcEntity, Session session) throws DBObjectException {
        boolean retorno = true;
        try {
            session.save(lcEntity);
        } catch (HibernateException e) {
            log(e);
            throw new DBObjectException(e);
        } finally {
            session.flush();
        }
        return retorno;
    }

    public boolean update(LCEntity lcEntity, Session session) throws DBObjectException {
        boolean retorno = true;
        try {
            session.merge(lcEntity);
        } catch (HibernateException e) {
            log(e);
            throw new DBObjectException(e);
        } finally {
            session.flush();
        }
        return retorno;
    }

    public boolean delete(LCEntity lcEntity, Session session) throws DBObjectException {
        boolean retorno = true;
        try {
            session.delete(lcEntity);
        } catch (HibernateException e) {
            log(e);
            throw new DBObjectException(e);
        } finally {
            session.flush();
        }
        return retorno;
    }

    public abstract Object select(Long id) throws SelectObjectException;

    public abstract List selectAll() throws SelectObjectException;

    protected void log(Exception e) {
        LogManager.getLogger(getClass()).error(e);
    }

    protected String retornaSQLLike(String parametro) {
        return " TRANSLATE(UPPER(" + parametro + "), 'ÁÉÍÓÚÀÈÌÒÙÄËÏÖÜÂÊÎÔÛÇÃÕ', 'AEIOUAEIOUAEIOUAEIOUCAO') LIKE '%'||TRANSLATE(UPPER(?), 'ÁÉÍÓÚÀÈÌÒÙÄËÏÖÜÂÊÎÔÛÇÃÕ', 'AEIOUAEIOUAEIOUAEIOUCAO') ||'%' ";
    }
}
