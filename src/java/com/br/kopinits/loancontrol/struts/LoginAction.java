package com.br.kopinits.loancontrol.struts;

import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.forms.LoginForm;
import com.br.kopinits.loancontrol.negocio.UsuarioNegocio;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Markus
 */
public class LoginAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario usr = new Usuario();
        LoginForm frm = (LoginForm) form;
        if (!mapping.getPath().equals("/home")) {
            usr.setLogin(frm.getLogin());
            usr.setSenha(frm.getSenha());
            UsuarioNegocio negocio = new UsuarioNegocio();
            usr = negocio.login(usr);
        }else{
            if (request.getSession().getAttribute(ConstantesAplicacao.USUARIO) != null)
                usr = (Usuario)request.getSession().getAttribute(ConstantesAplicacao.USUARIO);
        }

        if (usr != null) {
            request.getSession().setAttribute(ConstantesAplicacao.NM_USUARIO, usr.getLogin());
            request.getSession().setAttribute(ConstantesAplicacao.USUARIO, usr);
            return ConstantesAplicacao.SUCESSO;
        } else {
            setMensagemSessao("erro.login.invalido", null, request);
            return ConstantesAplicacao.ERRO;
        }
    }

    @Override
    protected boolean isAutenticar() {
        return false;
    }
}
