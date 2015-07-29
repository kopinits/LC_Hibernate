<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:javascript formName="formEmprestimo"/>
<script type="text/javascript">
    function validar() {
        var ps = formEmprestimo.idPessoa.value;
        var bem = formEmprestimo.idBem.value;
        var es = "";
        if (formEmprestimo.acao.value == "DEVOLVER"){
            es = strTrim(formEmprestimo.estado.value);
        }
        
        var msg = "";
        if (ps == "") {
            msg = "A Pessoa é obrigatória.\n"
        }
        if (bem == "") {
            msg += "O Bem Material é obrigatório.\n"
        }
        if (formEmprestimo.acao.value == "DEVOLVER"){
            if (es == "") {
                msg += "O Estado de Devolução é obrigatório.\n"
            }
        }
        if (msg != "") {
            alert(msg);
            return false;
        } else {
            formEmprestimo.submit();
            return true;
        }
    }
    function voltar(){
        formEmprestimo.action = "<html:rewrite action="/listarEmprestimo.do"/>";
        formEmprestimo.submit();        
    }
</script>
<div class="conteudoInterno">
    <h4><bean:message key="label.titulo.manter.emprestimo" /></h4>
    <br>
    <html:form action="/emprestimo.do" method="post" styleId="formEmprestimo">
        <html:hidden property="acao" value="SALVAR"/>
        <html:hidden property="id"/>
        <table border="0" width="80%" align="center">
            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.pessoa" /> <span class="obrigatorio">*</span>:
                </td>
                <td>
                    <c:if test="${formEmprestimo.id ne ''}">
                        ${formEmprestimo.nomePessoa}
                        <html:hidden property="idPessoa"/>
                    </c:if>
                    <c:if test="${formEmprestimo.id eq ''}">
                    <html:select property="idPessoa" style="width:262px;" styleId="idPessoa">
                        <html:option value=""/>
                        <html:options collection="listComboPessoas" labelProperty="nome" property="id" />
                    </html:select>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.menu.bemmaterial" /> <span class="obrigatorio">*</span>:
                </td>
                <td>
                    <c:if test="${formEmprestimo.id ne ''}">
                        ${formEmprestimo.nomeBem}
                        <html:hidden property="idBem"/>
                    </c:if>
                    <c:if test="${formEmprestimo.id eq ''}">
                    <html:select property="idBem" style="width:262px;" styleId="idBem">
                        <html:option value=""/>
                        <html:options collection="listComboBens" labelProperty="descricao" property="id" />
                    </html:select>
                    </c:if>
                </td>
            </tr>
            <c:if test="${formEmprestimo.id ne ''}">
                <tr>
                    <td style="text-align: right;">
                        <bean:message key="label.estado.devolucao" />:
                    </td>
                    <td>
                        <html:text property="estado" size="80" maxlength="200" styleId="estado"/>
                    </td>
                </tr>
                <tr>
            </c:if>
        </table>

        <br><br>
        <div class="botoes">
            <c:if test="${formEmprestimo.id eq ''}">
                <html:button onclick="return window.validar();" property="btSalvar" styleClass="botao"><bean:message key="label.botao.salvar" /></html:button>
            </c:if>
            <c:if test="${formEmprestimo.id ne ''}">
                <html:button onclick="return window.validar();" property="btSalvar" styleClass="botao"><bean:message key="label.botao.devolver" /></html:button>
            </c:if>
            <html:button onclick="return window.voltar();" property="btCancelar" styleClass="botao"><bean:message key="label.botao.cancelar" /></html:button>
        </div>
        <div class="obrigatorio"><bean:message key="campos.obrigatorios" /></div>
    </html:form>
</div>