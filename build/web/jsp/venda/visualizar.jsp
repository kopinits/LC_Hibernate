<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html:javascript formName="formVenda"/>
<script type="text/javascript">
    function voltar(){
        formVenda.action = "<html:rewrite action="/listarVenda.do"/>";
        formVenda.submit();        
    }
</script>
<div class="conteudoInterno">
    <h4><bean:message key="label.titulo.manter.venda" /></h4>
    <br>
    <html:form action="/venda.do" method="post" styleId="formVenda">
        <table border="0" width="80%" align="center">
            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.menu.bemmaterial" />:
                </td>
                <td>
                    ${formVenda.descBem} - ${formVenda.descCategoria}
                </td>
            </tr>
             <tr>
                <td style="text-align: right;">
                    <bean:message key="label.nome.comprador" />:
                </td>
                <td>
                    ${formVenda.nomeComprador}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.data" />:
                </td>
                <td>
                    <fmt:formatDate value="${formVenda.dataVenda}" pattern="dd/MM/yyyy" />
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.valor" />:
                </td>
                <td>
                   <fmt:setLocale value="pt-BR" />  
                <fmt:formatNumber value="${formVenda.valor}" type="currency" />
                </td>
            </tr>            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.motivo" />:
                </td>
                <td>
                    ${formVenda.motivoVenda}
                </td>
            </tr>
        </table>

        <br><br>
        <div class="botoes">
            <html:button onclick="return window.voltar();" property="btCancelar" styleClass="botao"><bean:message key="label.botao.cancelar" /></html:button>
        </div>
    </html:form>
</div>