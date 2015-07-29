package com.br.kopinits.loancontrol.struts;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Markus
 */
public class LogoffAction extends LCAction {


    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.getSession().removeAttribute("nomeUsuario");
        request.getSession().invalidate();
       return ConstantesAplicacao.SUCESSO;
    }

    @Override
    protected boolean isAutenticar() {
        return false;
    }
    
}
