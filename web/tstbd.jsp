<%-- 
    Document   : tstbd
    Created on : 21/03/2014, 10:10:42
    Author     : Markus
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.br.kopinits.loancontrol.db.BDConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% out.write(new BDConnection().displayDbProperties());
        
        out.write(BDConnection.getConnection().getMetaData().getUserName());
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
//                BDConnection.closeConnection();
                String sql = "select nextval('sq_usua_sq_usua') as proxSequence";
                ps = BDConnection.getConnection().prepareStatement(sql);

                rs = ps.executeQuery();
            } catch (Exception e) {
                out.write(e.getMessage());
                e.printStackTrace();
            }

        %>
    </body>
</html>
