<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:javascript formName="formLogin"/>

<p>
<div class="caixaEsquerda">
    <html:link action="home.do"><img src="imgs/emprestar_sup.png" border="0" style="vertical-align:middle"></html:link>&nbsp;&nbsp;&nbsp;<bean:message key="app.titulo" />
</div>
<div class="caixaDireita">
    <i><bean:message key="label.bemvindo" />&nbsp;<bean:write scope="session" name="NM_USUARIO"/></i><br>
    <div align="right">
        <html:link action="/logoff.do" styleClass="font-color:#000000" titleKey="title.menu.sair">
            <bean:message key="label.menu.sair" />
        </html:link>
    </div>
</div>
</p>