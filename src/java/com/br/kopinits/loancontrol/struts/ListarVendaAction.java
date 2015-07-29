package com.br.kopinits.loancontrol.struts;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.br.kopinits.loancontrol.beans.Venda;
import com.br.kopinits.loancontrol.forms.VendaForm;
import com.br.kopinits.loancontrol.negocio.VendaNegocio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Markus
 */
public class ListarVendaAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        VendaForm frm = (VendaForm) form;
        ArrayList<Venda> listaVendas;
        VendaNegocio negocio = new VendaNegocio(getUsuarioLogado(request));
        String key;
        if (frm.getAcao() != null && frm.getAcao().equals(ConstantesAplicacao.PESQUISAR)) {
            listaVendas = (ArrayList<Venda>) negocio.filtrar(frm.getNomePesquisa().trim(),frm.getCampoPesquisa());
            key = "msg.sem.itens.pesquisa";
        } else {
            listaVendas = (ArrayList<Venda>) negocio.selectAll();
            key = "msg.sem.itens";
        }
        if (listaVendas.isEmpty()) {
            request.setAttribute(ConstantesAplicacao.MSG_RESULTS, getMessage(key, request));
        }else{
            request.setAttribute(ConstantesAplicacao.MSG_RESULTS, "");
        }

        request.setAttribute("listaVendas", listaVendas);
        return ConstantesAplicacao.SUCESSO;
    }

    @Override
    protected boolean isAutenticar() {
        return true;
    }

}
