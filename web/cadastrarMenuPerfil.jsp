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
        <link rel="stylesheet" href="sweetalert2/sweetalert2.min.css" type ="text/css">
         <link rel="stylesheet" href="css/styles.css" type="text/css">
        <title>PROJETO ETB</title>
    </head>

    <body>
        <div id="container" class="bg-background">
            <div id="header">
                <jsp:include page="template/header.jsp"></jsp:include>
            </div>
            <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
            </div>
            <div id="conteudo"  >
              
                
               
                <form action="gerenciarMenuPerfil" method="POST">
                    
                    <h3 class="text-center mt-5" style="padding-top:20px">
                        Cadastro de Menu e Perfil
                    </h3>
                    
                    <script type="text/javascript">
                        function getUserInput(){
                            var nometxt = 
                                document.getElementById("idnome").value();
                                
                                document.getElementById("idnome").InnerHTML = nometxt;
                            
                        }
                        
                        var nometxt = 
                                document.getElementById(idnome).value();
                        
                    </script>
                    <input type="hidden" id="idperfil" name="idPerfil"
                           value ="${perfilv.idPerfil}">
                
                    <div class="form-group  row offset-md-3 mt-5">
                        <label class="col-md-2 form-label" for="idnome">Nome</label>
                        <div class="col-md-5">
                            <input type="text" name="nome" id="idnome"
                                class="form-control" readonly 
                                value="${perfilv.nome}">
                            
                        </div>
                    
                    </div>
                    <div class="form-group  row offset-md-3 mt-5">
                        <label class="col-md-2 form-label" for="idmenu">Menu</label>
                        <div class="col-md-5">
                            <select id="idmenu" name="idMenu" 
                                class="form-control-md mt-1">
                                <option value=""selected>Escolha um Menu</option>
                                <c:forEach var="m" items="${perfilv.naoMenus}">
                                    <option value="${m.idMenu}">${m.nome}</option>
                                </c:forEach>
                                
                            </select>
                            
                        </div>
                    
                    </div>
                                
                    
                    
               
                    <div class="d-md-flex justify-content-md-end mt-5">
                        <button class="btn btn-primary btn-md mr-4"
                                onclick="getUserInput()">
                            Vincular&nbsp;<i class="fa-solid fa-floppy-disk"></i>
                        </button>
                        <a href="gerenciarPerfil?acao=listar"
                           class="btn btn-danger btn-md mr-3"
                           role="button">
                            Voltar&nbsp;<i class="fa-solid fa-rotate-left"></i>
                            
                        </a>
                        
                    </div>
                    
                    
                    
                </form>
                <div class="h-100 justify-content-end align-items-center">
                    <h3 class="text-center">Menus Vinculados Por Perfil</h3>
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered"
                               id="listarMenuPerfil">
                            <thead>
                                <tr>
                                    <th>Nome</th>
                                    <th>Link</th>
                                    <th>Exibir</th>
                                    <th>Ação</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="m" items="${perfilv.menus}">
                                <tr>
                                    <td>${m.nome}</td>
                                    <td>${m.link}</td>
                                    <td>
                                        <c:if test="${m.exibir == 1}">
                                            Sim
                                        </c:if>
                                        <c:if test="${m.exibir == 0}">
                                            Não
                                        </c:if>
                                    </td>
                                    <td>
                                        <script type="text/javascript">
                                            function confirmDesvincular(idMenu, nome, idPerfil){
                                                if(confirm("Deseja desvincular o menu " +nome+ " ?")){
                                                    location.href="gerenciarMenuPerfil?acao=desvincular&idMenu="
                                                            +idMenu+"&idPerfil="+idPerfil;
                                                }
                                            }
                                        </script>
                                        <button class="btn btn-danger btn-sm"
                                            onclick="confirmDesvincular('${m.idMenu}', '${m.nome}', '${perfilv.idPerfil}')">
                                            Desvincular&nbsp;
                                            <i class="fa-solid fa-square-minus"></i>
                                        </button>
                                        
                                        
                                    </td>
                                    
                                </tr>
                                </c:forEach>
                            </tbody>
                            
                        </table>
                    </div>
                                    
                </div>
                </div> <!-- fim da div conteúdo -->
               
            </div><!-- fim da div container -->
       
        <!-- JQuery.js -->
        <script src="js/jquery-3.6.0.min.js"></script>
        
        <!-- Popper via cdn -->
         <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha512-Ua/7Woz9L5O0cwB/aYexmgoaD7lw3dWe9FvXejVdgqu71gRog3oJgjSWQR55fwWx+WKuk8cl7UwA1RS6QCadFA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- Bootstrap.js -->
        <script src="js/bootstrap.min.js"></script>
        <script src="datatables/js/jquery.dataTables.min.js"></script>
        <!--dataTables.bootstrap4.min.js -->
        <script src="datatables/js/dataTables.bootstrap4.min.js"></script>
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
             $(document).ready(function () {
                $("#listarMenuPerfil").dataTable({
                    "bJQueryUI": true,
                    "lengthMenu": [[5, 10, 20, 25, -1], [5, 10, 20, 25, "Todos"]],
                        "oLanguage": {
                            "sProcessing": "Processando..",
                            "sLengthMenu": "Mostrar _MENU_ registros",
                            "sZeroRecords": "Não foram encontrados resultados",
                            "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                            "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                            "sInfoFiltered": "",
                            "sInfoPostFix": "",
                            "sSearch": "Pesquisar",
                            "sUrl": "",
                        "oPaginate": {
                            "sFirst": "Primeiro",
                            "sPrevious": "Anterior",
                            "sNext": "Próximo",
                            "sLast": "último"
                            }
                        }
                    });
                }); 
            </script>
            

        
        
        </body>
</html>

