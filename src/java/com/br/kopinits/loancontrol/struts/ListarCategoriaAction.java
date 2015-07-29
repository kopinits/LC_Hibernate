package com.br.kopinits.loancontrol.struts;

import com.br.kopinits.loancontrol.beans.Categoria;
import com.br.kopinits.loancontrol.forms.CategoriaForm;
import com.br.kopinits.loancontrol.negocio.CategoriaNegocio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Markus
 */
public class ListarCategoriaAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CategoriaForm frm = (CategoriaForm) form;
        ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
        CategoriaNegocio negocio = new CategoriaNegocio(getUsuarioLogado(request));
        String key = "";
        if (frm.getAcao() != null && frm.getAcao().equals(ConstantesAplicacao.PESQUISAR)) {
            listaCategorias = (ArrayList<Categoria>) negocio.filtrar(frm.getNomePesquisa().trim());
            key = "msg.sem.itens.pesquisa";
        } else {
            listaCategorias = (ArrayList<Categoria>) negocio.selectAll();
            key = "msg.sem.itens";
        }
        if (listaCategorias.isEmpty()) {
            request.setAttribute(ConstantesAplicacao.MSG_RESULTS, getMessage(key, request));
        }else{
            request.setAttribute(ConstantesAplicacao.MSG_RESULTS, "");
        }

        request.setAttribute("listaCategorias", listaCategorias);
        return ConstantesAplicacao.SUCESSO;
    }

    @Override
    protected boolean isAutenticar() {
        return true;
    }
}
