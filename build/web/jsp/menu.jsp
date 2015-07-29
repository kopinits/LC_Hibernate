<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<div id='cssmenu'>
    <ul>
        <li>
        <html:link action="/listarCategoria.do" titleKey="title.menu.categorias">
            <bean:message key="label.menu.categorias" />
        </html:link>
        </li>
        <li>
        <html:link action="/listarPessoa.do" titleKey="title.menu.pessoas">
            <bean:message key="label.menu.pessoas"/>
        </html:link> 
        </li>
        <li>
        <html:link action="/listarBem.do" titleKey="title.menu.bemmaterial">
            <bean:message key="label.menu.bemmaterial"/>
        </html:link>
        </li>
        <li>
        <html:link action="/listarEmprestimo.do" titleKey="title.menu.emprestimos">
            <bean:message key="label.menu.emprestimos"/>
        </html:link>
        </li>
        <li>
        <html:link action="/listarVenda.do" titleKey="title.menu.vendas">
            <bean:message key="label.menu.vendas"/>
        </html:link>
        </li>
        <li class='last'>
        <html:link action="/meusDados.do" titleKey="title.menu.meusdados">
            <bean:message key="label.menu.meusdados"/>
        </html:link>
        </li>
    </ul>
</div>