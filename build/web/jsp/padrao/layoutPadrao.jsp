<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">

<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:html lang="true">
    <head>
        <title><bean:message key="app.titulo" /></title>
        <link href='http://fonts.googleapis.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <script src="js/scripts.js" type="text/javascript" language="javascript" charset="utf-8"></script>
        <script src="js/jquery.maskMoney.js" type="text/javascript"></script>
        <script src="http://code.jquery.com/jquery-1.11.0.min.js" type="text/javascript" language="javascript" ></script>
        <script type="text/javascript">	
            <logic:present name="MENSAGEM" scope="session">
                alert('<bean:write scope="session" name="MENSAGEM" />');
                <%request.getSession().removeAttribute("MENSAGEM");%>
            </logic:present>

            function showInfos()
            {
                var infos = "";
                <html:messages id="info" message="true">
                        infos += "<bean:write name="info" />\n";
                </html:messages>
                     if (infos != "") 
                            alert(infos);
                    }
		
                    function showErrors()
                    {
                        var errors = "";
                <html:messages id="erro" >
                        errors += "<bean:write name="erro" />\n";
            </html:messages>
                    
                        if (errors != "") 
                            alert(errors);
                    }
		
                    window.onload = function() {
                        showErrors();
                        showInfos();
                        if (document.getElementById("frmLogin") != null){
                            document.getElementById("frmLogin").getElementsByTagName("input")[0].focus();
                        }
                    }
        </script>


    </head>
    <body>
        <!--estrutura-->
        <div class="estrutura">
            <!--cabecalho-->
            <div class="cabecalho">
                <tiles:get name="superior" />
            </div>
            <!--/cabecalho-->

            <!--menuSuperior-->
            <tiles:get name="menu" />
            <!--/menuSuperior-->

            <!--corpo-->
            <div class="corpo">
                <tiles:get name="conteudo" />
            </div>	
            <!--/corpo-->

            <!-- rodapé-->
            <div class="rodape">
                <tiles:get name="rodape" />
            </div>
            <!-- rodapé-->
        </div>
        <!--/estrutura-->
    </body>
</html:html>
