/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lc;

import com.br.kopinits.loancontrol.beans.Categoria;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.db.DBObjectException;
import com.br.kopinits.loancontrol.negocio.CategoriaNegocio;
import com.br.kopinits.loancontrol.negocio.UsuarioNegocio;
import java.util.Date;

/**
 *
 * @author Markus
 */
public class Teste {

    public static void main(String[] args) {
        //  EmailUtils.enviarEmail("assunto", "texto", "mkpnts@gmail.com");
        try {
            Usuario usuario = new Usuario();
            usuario.setId(27l);
            Categoria cate = new Categoria();
            cate.setDescricao("Teste Hibernate");
            cate.setUsuario(usuario);
            cate.setDataAtualizacao(new Date());
            CategoriaNegocio negocio = new CategoriaNegocio(usuario);
            negocio.create(cate);
        } catch (DBObjectException e) {
            System.out.println("erro");
        }
    }
}
