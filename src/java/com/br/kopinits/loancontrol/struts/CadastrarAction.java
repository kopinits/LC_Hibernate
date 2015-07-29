package com.br.kopinits.loancontrol.struts;

import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.forms.LoginForm;
import com.br.kopinits.loancontrol.negocio.UsuarioNegocio;
import com.br.kopinits.loancontrol.utils.EmailUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Markus
 */
public class CadastrarAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LoginForm frm = (LoginForm) form;
        Usuario usr = new Usuario();
        usr.setNome(frm.getNome());
        usr.setLogin(frm.getLogin());
        usr.setEmail(frm.getEmail());
        usr.setSenha(frm.getSenha());
        UsuarioNegocio negocio = new UsuarioNegocio();
        negocio.create(usr);
        EmailUtils.enviarEmailLogin(usr);
        setMensagemSessao("msg.cadastro.efetuado.com.sucesso", new Object[]{frm.getNome()}, request);
        return ConstantesAplicacao.SUCESSO;

    }

    @Override
    protected boolean isAutenticar() {
        return false;
    }
}
