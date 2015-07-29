package com.br.kopinits.loancontrol.struts;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.br.kopinits.loancontrol.beans.Emprestimo;
import com.br.kopinits.loancontrol.forms.EmprestimoForm;
import com.br.kopinits.loancontrol.negocio.EmprestimoNegocio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Markus
 */
public class ListarEmprestimoAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EmprestimoForm frm = (EmprestimoForm) form;
        ArrayList<Emprestimo> listaEmprestimos = new ArrayList<Emprestimo>();
        EmprestimoNegocio negocio = new EmprestimoNegocio(getUsuarioLogado(request));
        String key = "";
        if (frm.getAcao() != null && frm.getAcao().equals(ConstantesAplicacao.PESQUISAR)) {
            listaEmprestimos = (ArrayList<Emprestimo>) negocio.filtrar(frm.getNomePesquisa().trim(),frm.getIsPessoa(), frm.getTodos());
            key = "msg.sem.itens.pesquisa";
        } else {
            listaEmprestimos = (ArrayList<Emprestimo>) negocio.selectAll();
            key = "msg.sem.itens";
        }
        if (listaEmprestimos.isEmpty()) {
            request.setAttribute(ConstantesAplicacao.MSG_RESULTS, getMessage(key, request));
        }else{
            request.setAttribute(ConstantesAplicacao.MSG_RESULTS, "");
        }

        request.setAttribute("listaEmprestimos", listaEmprestimos);
        return ConstantesAplicacao.SUCESSO;
    }

    @Override
    protected boolean isAutenticar() {
        return true;
    }

}
