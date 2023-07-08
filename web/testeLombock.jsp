
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="model.Perfil"
        import="java.util.Date"
        import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset ="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, 
              shrink-to-fit=no">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/nav.css" type="text/css">
        <link rel="stylesheet" href="webfonts/css/all.css" type="text/css">
        <title>Página Inicial</title>
    </head>
    <body>
        
        <div id="container">
            <div id="header">
                <jsp:include page="template/header.jsp"></jsp:include>
            </div>
            <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
            </div>
            <div id="conteudo">
                
                <%
                    Perfil p = new Perfil();
                    
                    p.setNome("Administrador");
                    p.setIdPerfil(1);
                    p.setStatus(1);
                    
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    
                    p.setDataCadastro(df.parse("15/03/2023"));
                    
                    out.println("Nome: " + p.getNome());
                    out.println("Código: " + p.getIdPerfil());
                    out.println("Status: " + p.getStatus());
                    out.println("Data de Cadastro: " + p.getDataCadastro());
                    
                   
                
                %>
               
                
            </div>
            
        </div>
        
        <!-- JQuery.js -->
        <script src="js/jquery-3.6.0.min.js"></script>
        <!-- Popper.js via cdn -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha512-Ua/7Woz9L5O0cwB/aYexmgoaD7lw3dWe9FvXejVdgqu71gRog3oJgjSWQR55fwWx+WKuk8cl7UwA1RS6QCadFA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>