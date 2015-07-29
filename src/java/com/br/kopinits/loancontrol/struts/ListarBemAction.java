package com.br.kopinits.loancontrol.struts;

import com.br.kopinits.loancontrol.beans.BemMaterial;
import com.br.kopinits.loancontrol.forms.BemMaterialForm;
import com.br.kopinits.loancontrol.negocio.BemMaterialNegocio;
import java.util.ArrayList;
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
public class ListarBemAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BemMaterialForm frm = (BemMaterialForm) form;
        ArrayList<BemMaterial> listaBens;
        BemMaterialNegocio negocio = new BemMaterialNegocio(getUsuarioLogado(request));
        String key;
        if (frm.getAcao() != null && frm.getAcao().equals(ConstantesAplicacao.PESQUISAR)) {
            listaBens = (ArrayList<BemMaterial>) negocio.filtrar(frm.getNomePesquisa().trim(), frm.getCampoPesquisa());
            key = "msg.sem.itens.pesquisa";
        } else {
            listaBens = (ArrayList<BemMaterial>) negocio.selectAll();
            key = "msg.sem.itens";
        }
        if (listaBens.isEmpty()) {
            request.setAttribute(ConstantesAplicacao.MSG_RESULTS, getMessage(key, request));
        }else{
            request.setAttribute(ConstantesAplicacao.MSG_RESULTS, "");
        }

        request.setAttribute("listaBens", listaBens);
        return ConstantesAplicacao.SUCESSO;
    }

    @Override
    protected boolean isAutenticar() {
        return true;
    }
}
