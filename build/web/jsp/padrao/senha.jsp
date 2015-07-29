<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:javascript formName="formLogin"/>
<script type="text/javascript">
    function validar() {
       var lg = strTrim(document.getElementById('login').value);
        var msg = "";
        if (lg == "") {
            msg += "O login é obrigatório\n";
        }

        if (msg != "") {
            alert(msg);
            return false;
        } else {
            document.getElementById('formSenha').submit();
            return true;
        }
    }
</script>
<table border="0" width="100%" cellpadding="40">
    <tr>
        <td width="50%" valign="middle" align="center">
            <h4>
                <bean:message key="label.esqueci.senha" /> 
                <br><br>
            </h4>
        </td>
        <td width="50%" valign="center" align="center">
            <div class="tabelaAzul">
                <html:form action="/esqueciSenha.do" method="post" styleId="formSenha">
                    <html:hidden property="acao" value="PESQUISAR" />
                    <table border="0" width="80%" align="center">

                        <tr>
                            <td style="text-align: right;">
                                <bean:message key="label.login" />:
                            </td>
                            <td>
                                <html:text property="login" size="40" maxlength="50" styleId="login"/>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2"><br><html:button property="resetarSenha" styleClass="botao" onclick="return validar();"><bean:message key="label.botao.enviar"/></html:button></td>
                            </tr>
                        </table>
                </html:form>
            </div>
        </td>
    </tr>
</table>