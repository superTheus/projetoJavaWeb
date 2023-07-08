<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,
              initial-scale=1.0, shrink-to-fit=no">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" 
              type="text/css">
        <link rel="stylesheet" href="css/nav.css" type="text/css">
        <link rel="stylesheet" href="webfonts/css/all.css" type="text/css">
        <link rel="stylesheet" href="datatables/css/dataTables.bootstrap4.min.css" type="text/css">
        <link rel="stylesheet" href="sweetalert2/sweetalert2.min.css" 
              type ="text/css">
        <title>PROJETO ETB</title>
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
              
                
               
                <form action="gerenciarMenuPerfil" method="POST">
                    
                    <h3 class="text-center mt-5" style="padding-top:20px">
                        Cadastro de Menu e Perfil
                    </h3>
                    <input type="hidden" id="idperfil" name="idPerfil"
                           value="${perfilv.idPerfil}">
                    <div class="form-group row offset-md-4 mt-4">
                        <label
                            class="col-md-1form-label btn btn-primary btn-md"
                            >Nome</label>
                        <div class="col-md-4">
                            <input type="text" id="idnome" name="nome"
                                   class="form-control" value="${perfilv.nome}"
                                   readonly>
                        </div>
                    
                    
                    </div>
                    <div class="form-group row offset-md-4 mt-4">
                        <label
                            class="col-md-1form-label btn btn-primary btn-md">
                            Menu
                        </label>
                        <div class="col-md-4">
                            <select id="idmenu" name="idMenu"
                                class="form-control-sm mt-3">
                                <option value=""selected>Escolha um menu</option>
                                <c:forEach var="m" items="${perfilv.naoMenus}">
                                    <option value="${m.idMenu}">${m.nome}</option>
                                    
                                </c:forEach>
                                
                            </select>
                          
                        </div>
                                       
                    </div>
                    
                 
                    
                    
                    
                </form>
               
            </div> <!-- fim da div conteúdo -->
       
        <!-- JQuery.js -->
        <script src="js/jquery-3.6.0.min.js"></script>
        
        <!-- Popper via cdn -->
         <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha512-Ua/7Woz9L5O0cwB/aYexmgoaD7lw3dWe9FvXejVdgqu71gRog3oJgjSWQR55fwWx+WKuk8cl7UwA1RS6QCadFA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- Bootstrap.js -->
        <script src="js/bootstrap.min.js"></script>
        <script src="sweetalert2/sweetalert2.min.js"></script>
        <% 
            if(request.getAttribute("msg") != null){
        
         %>
         <script>
             $(document).ready(function(){
               Swal.fire({
                position: 'top-center',
                title: 'Atenção',
                text: '<%= request.getAttribute("msg")%>',
                icon: 'error',
                showConfirmButton: false,
                timer: 3000
                });
                 
                 
             });
                    
         </script>
        <%
            }
            
        %>
        
        <script type="text/javascript">
            function valida_form(){
                var txtnome = document.getElementById("idnome").value;
                var txtdata = document.getElementById("iddata").value;
                var txtidstatus = document.getElementById("idstatus").value;
                
                document.getElementById("idnome").innerHTML() = txtnome;
                document.getElementById("iddata").innerHTML() = txtdata;
                document.getElementById("idstatus").innerHTML() = txtidstatus;
               
            }
            
        </script>
            

        
        
        </body>
</html>

