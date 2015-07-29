/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.db.dao;

import com.br.kopinits.loancontrol.beans.LCEntity;
import com.br.kopinits.loancontrol.db.DBObjectException;
import com.br.kopinits.loancontrol.db.SelectObjectException;
import com.br.kopinits.loancontrol.db.dao.impl.HibernateUtils;
import com.br.kopinits.loancontrol.db.dao.impl.LCDAOImpl;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Markus
 */
public abstract class LCNegocio {

    public static final Logger logger = LogManager.getLogger(LCNegocio.class);

    private final LCDAOImpl daoImpl;

    public LCDAOImpl getDaoImpl() {
        return daoImpl;
    }

    public LCNegocio(LCDAOImpl daoImpl) {
        this.daoImpl = daoImpl;
    }

    public boolean create(LCEntity lcEntity) throws DBObjectException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        boolean ret = false;
        try {
            transaction.begin();
            lcEntity.setDataAtualizacao(new Date());
            ret = getDaoImpl().create(lcEntity, session);
            transaction.commit();
        } catch (DBObjectException e) {
            transaction.rollback();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new DBObjectException((e));
        } finally {
            session.close();
        }
        return ret;
    }

    public boolean update(LCEntity lcEntity) throws DBObjectException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        boolean ret = false;
        try {
            transaction.begin();
            atualizaEntidadeCom(lcEntity);
            ret = getDaoImpl().update(lcEntity, session);
            transaction.commit();
        } catch (DBObjectException e) {
            transaction.rollback();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new DBObjectException((e));
        } catch (SelectObjectException e) {
            transaction.rollback();
            throw new DBObjectException((e));
        } finally {
            session.close();
        }
        return ret;
    }

    public boolean delete(Long id) throws DBObjectException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        boolean ret = false;
        try {
            transaction.begin();
            LCEntity entity = (LCEntity) getDaoImpl().select(id);
            ret = getDaoImpl().delete(entity, session);
            transaction.commit();
        } catch (DBObjectException e) {
            transaction.rollback();
        } catch (SelectObjectException e) {
            transaction.rollback();
            throw new DBObjectException((e));
        } catch (HibernateException e) {
            transaction.rollback();
            throw new DBObjectException((e));
        } finally {
            session.close();
        }
        return ret;
    }

    public Object select(Long id) throws DBObjectException {
        Object retorno = null;
        try {
            retorno = getDaoImpl().select(id);
        } catch (SelectObjectException e) {
            throw new DBObjectException(e);
        }
        return retorno;
    }

    public List selectAll() throws DBObjectException {
        List retorno;
        try {
            retorno = getDaoImpl().selectAll();
        } catch (SelectObjectException e) {
            throw new DBObjectException(e);
        }
        return retorno;
    }

    public Object selectMetodoAsObjeto(String nomeMetodo, Object objeto, Class tipoObj) throws SelectObjectException {
        return selectInvoke(nomeMetodo, objeto, tipoObj);
    }

    public Collection selectMetodoAsColecao(String nomeMetodo, Object objeto, Class tipoObj) throws SelectObjectException {
        return (Collection) selectInvoke(nomeMetodo, objeto, tipoObj);
    }

    private Object selectInvoke(String nomeMetodo, Object objeto, Class tipoObj) {
        Class c;
        Object retorno = null;
        try {
            c = Class.forName(daoImpl.getClass().getName());
            Object obj = c.getConstructors()[0].newInstance(getDaoImpl().getUsrLogado());
            Method method = c.getMethod(nomeMetodo, new Class[]{tipoObj});
            retorno = method.invoke(obj, new Object[]{objeto});
        } catch (ClassNotFoundException ex) {
            log(ex);
        } catch (SecurityException ex) {
            log(ex);
        } catch (InstantiationException ex) {
            log(ex);
        } catch (IllegalAccessException ex) {
            log(ex);
        } catch (IllegalArgumentException ex) {
            log(ex);
        } catch (InvocationTargetException ex) {
            log(ex);
        } catch (NoSuchMethodException ex) {
            log(ex);
        }
        return retorno;
    }

    protected void log(Exception e) {
        LogManager.getLogger(getClass()).error(e);
        e.printStackTrace();
    }

    private void atualizaEntidadeCom(LCEntity lcEntity) throws SelectObjectException {
        LCEntity entityBanco = (LCEntity) getDaoImpl().select(lcEntity.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(lcEntity, entityBanco);
        lcEntity.setDataAtualizacao(new Date());
    }
}
