<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:javascript formName="formLogin"/>
<script type="text/javascript">
    function validar() {
        var pw = document.getElementById('senha').value;
        var pw2 = document.getElementById('repeteSenha').value;
        var msg = "";

        if (pw == "") {
            msg += "A senha é obrigatória\n";
        } else {
            if (pw != pw2) {
                msg += "As senhas não conferem";
            }
        }
        if (pw.length < 4){
            msg += "A senha deve possuir no mínimo 4 caracteres";
        }

        if (msg != "") {
            alert(msg);
            return false;
        } else {
            document.forms["formLogin"].submit();
            return true;
        }
    }
</script>
<div class="conteudoInterno">
    <h4><bean:message key="label.titulo.manter.usuario" /></h4>
    <br>
    <html:form action="/trocaSenha.do" method="post" styleId="formLogin">
        <html:hidden property="acao" value="SENHA"/>
        <html:hidden property="id"/>
        <table border="0" width="80%" align="center">
            <tr>
            <td style="text-align: right;">
                <bean:message key="label.senha" />:
            </td>
            <td>
                <html:password property="senha" size="40" maxlength="10" styleId="senha"/>
            </td>
            </tr>
            <tr>
            <td style="text-align: right;">
                <bean:message key="label.repete.senha" />:
            </td>
            <td>
                <html:password property="repeteSenha" size="40" maxlength="10" styleId="repeteSenha"/>
            </td>
            </tr>
        </table>

        <br><br>
        <div class="botoes">
            <html:button onclick="return validar();" property="btSalvar" styleClass="botao"><bean:message key="label.botao.salvar" /></html:button>
            </div>
    </html:form>
</div>