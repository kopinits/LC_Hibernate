<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<div class="caixaEsquerdaSup">
    <div>
        <html:link action="index.do"><img src="imgs/emprestar_sup.png" border="0" style="vertical-align:middle"></html:link>&nbsp;&nbsp;&nbsp;<bean:message key="app.titulo" />
    </div>
</div>
<div class="caixaDireitaSup">
    <html:form action="/login.do" method="post" styleId="frmLogin">
        <table border="0" width="100%">
            <tr>
                <td>
                    <bean:message key="label.login" />
                </td>
                <td colspan="2">
                    <bean:message key="label.senha" />
                </td>
            </tr>
            <tr>
                <td>
                    <html:text property="login" size="30" maxlength="50"/>
                </td>
                <td>
                    <html:password property="senha" size="30" maxlength="20"/>
                </td>
                <td><html:submit styleClass="botao"><bean:message key="label.botao.entrar"/></html:submit></td>
            </tr>
            <tr>
                <td colspan="2" style="font-size: 10px;"><html:link action="/esqueciSenha.do"><bean:message key="label.esqueci.senha" /></html:link></td>
            </tr>
        </table>
    </html:form>
</div>