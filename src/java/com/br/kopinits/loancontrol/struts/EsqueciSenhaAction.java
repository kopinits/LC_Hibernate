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
public class EsqueciSenhaAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LoginForm frm = (LoginForm) form;
        if (frm.getAcao() != null && frm.getAcao().equals(ConstantesAplicacao.PESQUISAR)) {
            UsuarioNegocio negocio = new UsuarioNegocio();
            long novaSenha = negocio.resetarSenha(frm.getLogin());
            if (novaSenha >0)
                setMensagemSessao("msg.senha.recuperada", new Object[]{novaSenha}, request);
        }

        return ConstantesAplicacao.SUCESSO;

    }

    @Override
    protected boolean isAutenticar() {
        return false;
    }
}
