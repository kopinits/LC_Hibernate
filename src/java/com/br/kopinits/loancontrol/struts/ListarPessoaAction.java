package com.br.kopinits.loancontrol.struts;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.br.kopinits.loancontrol.beans.Pessoa;
import com.br.kopinits.loancontrol.forms.PessoaForm;
import com.br.kopinits.loancontrol.negocio.PessoaNegocio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Markus
 */
public class ListarPessoaAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PessoaForm frm = (PessoaForm) form;
        ArrayList<Pessoa> listaPessoas;
        PessoaNegocio negocio = new PessoaNegocio(getUsuarioLogado(request));
        String key;
        if (frm.getAcao() != null && frm.getAcao().equals(ConstantesAplicacao.PESQUISAR)) {
            listaPessoas = (ArrayList<Pessoa>) negocio.filtrar(frm.getNomePesquisa().trim(), frm.getCampoPesquisa());
            key = "msg.sem.itens.pesquisa";
        } else {
            listaPessoas = (ArrayList) negocio.selectAll();
            key = "msg.sem.itens";
        }
        if (listaPessoas.isEmpty()) {
            request.setAttribute(ConstantesAplicacao.MSG_RESULTS, getMessage(key, request));
        }else{
            request.setAttribute(ConstantesAplicacao.MSG_RESULTS, "");
        }

        request.setAttribute("listaPessoas", listaPessoas);
        return ConstantesAplicacao.SUCESSO;
    }

    @Override
    protected boolean isAutenticar() {
        return true;
    }

}
