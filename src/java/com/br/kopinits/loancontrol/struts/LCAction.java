/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.struts;

import com.br.kopinits.loancontrol.beans.LCEntity;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.db.BDConnection;
import com.br.kopinits.loancontrol.db.DBObjectException;
import com.br.kopinits.loancontrol.db.LCException;
import com.br.kopinits.loancontrol.forms.LCForm;
import java.lang.reflect.Method;
import java.sql.BatchUpdateException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Markus
 */
public abstract class LCAction extends Action {

    protected abstract boolean isAutenticar();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String retorno;
        ActionForward actionForward = null;
        try {
            if (isAutenticar()) {
                if (request.getSession() == null || request.getSession().getAttribute(ConstantesAplicacao.USUARIO) == null) {
                    setMensagemSessao("error.nao.autenticado", null, request);
                    return mapping.findForward(ConstantesAplicacao.NAO_AUTENTICADO);
                }
            }
            retorno = executar(mapping, form, request, response);
            if (retorno != null) {
                actionForward = mapping.findForward(retorno);
                if (actionForward == null) {
                    String msg = "[forward \"" + retorno + "\" não declarado para a Action " + getClass().getName() + "]";
                    LogManager.getLogger(getClass()).error(msg);
                    actionForward = mapping.findForward(ConstantesAplicacao.ERRO);
                }
            }

        } catch (Exception e) {
            retorno = ConstantesAplicacao.ERRO;
            actionForward = mapping.findForward(retorno);
            if (actionForward == null) {
                String msg = "[forward \"" + retorno + "\" não declarado para a Action " + getClass().getName() + "]";
                LogManager.getLogger(getClass()).error(msg);
                actionForward = mapping.findForward(ConstantesAplicacao.ERRO);
            }
            BDConnection.rollback();
            if (e instanceof DBObjectException) {
                trataExcecao(e, request);
            } else {
                String msgErro = "erro.nao.tratado";
                if (e.getMessage() != null && !e.getMessage().equals("")){
                    msgErro = e.getMessage();
                }
                setMensagemSessao(msgErro, null, request);
                LogManager.getLogger(getClass()).error(e.getMessage());
            }
        }
        return actionForward;
    }

    private void trataExcecao(Exception e, HttpServletRequest request) {
        Throwable t = e.getCause();
        if (t != null) {
            Throwable tCause = t.getCause();
            if (tCause != null) {
                if (tCause instanceof PSQLException) {
                    trataErroPostGress(tCause,request);
                }else if (tCause instanceof BatchUpdateException) {
                    BatchUpdateException pex = (BatchUpdateException) tCause;
                    trataErroPostGress(pex.getNextException(), request);
                }else if (tCause instanceof SQLIntegrityConstraintViolationException) {
                    SQLIntegrityConstraintViolationException ex = (SQLIntegrityConstraintViolationException) tCause;
                } else {
                    setMensagemSessao(tCause.getMessage(), null, request);
                    LogManager.getLogger(getClass()).error(t.getCause().getMessage());
                }
            } else {
                setMensagemSessao(t.getMessage(), null, request);
                LogManager.getLogger(getClass()).error(t.getMessage());
            }
        } else {
            setMensagemSessao(e.getMessage(), null, request);
            LogManager.getLogger(getClass()).error(e.getMessage());
        }
    }

    private void trataErroPostGress(Throwable tCause, HttpServletRequest request) {
        PSQLException pex = (PSQLException) tCause;
        String msg = LCException.buscarChaveErro(pex.getServerErrorMessage().getSQLState(), pex.getServerErrorMessage().getMessage());
        setMensagemSessao(msg, null, request);
    }

    public abstract String executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public void setMensagemSessao(String key, Object[] values, HttpServletRequest request) {
        if (key != null) {
            String msg;
            msg = getMessage(key, request);
            if (values != null) {
                for (int pos = 0; pos < values.length; pos++) {
                    msg = msg.replace("{" + pos + "}", values[pos].toString());
                }
            }
            request.getSession().setAttribute(ConstantesAplicacao.MENSAGEM, msg);
        }
    }

    protected String getMessage(String nomeKey, HttpServletRequest request) {
        try {
            return getBundle(request).getString(nomeKey);
        } catch (MissingResourceException e) {
            return nomeKey;
        }
    }

    protected ResourceBundle getBundle(HttpServletRequest request) {
        return ResourceBundle.getBundle("lc.menssagens_pt_BR", this.getLocale(request));
    }

    protected Usuario getUsuarioLogado(HttpServletRequest request) {
        if (request.getSession().getAttribute(ConstantesAplicacao.USUARIO) != null) {
            return (Usuario) request.getSession().getAttribute(ConstantesAplicacao.USUARIO);
        }
        return null;
    }

protected void preencheBeanPeloForm(LCEntity bean, LCForm form) {
        Class classeBean;
        Class classeForm;
        try {
            classeBean = Class.forName(bean.getClass().getName());
            classeForm = Class.forName(form.getClass().getName());

            Method[] declaredMethods = classeBean.getDeclaredMethods();
            for (Method m : declaredMethods) {
                if (m.getName().startsWith("set")) {
                    String nomeMetodoForm = "get" + m.getName().substring(3);
                    Method[] methods = classeForm.getMethods();
                    int pos = 0;
                    boolean axou = false;
                    for (Method mForm : methods) {
                        if (mForm.getName().equals(nomeMetodoForm)) {
                            axou = true;
                            break;
                        }
                        pos++;
                    }
                    if (axou) {
                        m.invoke(bean, new Object[]{methods[pos].invoke(form, new Object[]{})});
                    }
                }
            }
        } catch (Exception ex) {
            //
        }
    }
    
    protected void preencheFormPeloBean(LCEntity bean, LCForm form) {
        Class classeBean;
        Class classeForm;
        try {
            classeBean = Class.forName(bean.getClass().getName());
            classeForm = Class.forName(form.getClass().getName());

            Method[] declaredMethods = classeForm.getDeclaredMethods();
            for (Method m : declaredMethods) {
                if (m.getName().startsWith("set")) {
                    String nomeMetodoForm = "get" + m.getName().substring(3);
                    Method[] methods = classeBean.getMethods();
                    int pos = 0;
                    boolean axou = false;
                    for (Method mForm : methods) {
                        if (mForm.getName().equals(nomeMetodoForm)) {
                            axou = true;
                            break;
                        }
                        pos++;
                    }
                    if (axou) {
                        m.invoke(bean, new Object[]{methods[pos].invoke(form, new Object[]{})});
                    }
                }
            }
        } catch (Exception ex) {
            //
        }
    } 
}
