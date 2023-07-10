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
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" href="webfonts/css/all.css" type="text/css">
        <link rel="stylesheet" href="datatables/css/dataTables.bootstrap4.min.css" type="text/css">

        <title>PROJETO ETB</title>
    </head>
    <body>

        <header id="header">
            <div>
                <jsp:include page="template/header.jsp"></jsp:include>
                </div>
                <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
                </div>
            </header>
            <main id="main">
            <%-- 
                 if( request.getAttribute("msg")!= null){
                            out.println(msg);
                %>
                <script type="text/javascript">
                    alert('<%= request.getAttribute("msg") %>');
                </script>
                <%
                
}               }
            --%>

            <%
                if (request.getAttribute("msg") != null) {

            %> 
            <script type="text/javascript">
                $(document).ready(function(){
                Swal(
                        "Atenção",
                        "<%= request.getAttribute("msg")%>",
                        "error"
                        );
            </script>


            <%

                }
            %>

            <form action="gerenciarPerfil" method="POST">
                <h3 class="text-center mt-3 mb-4 p-2" style="padding-top:20px">
                    Página de Cadastro
                </h3>
                
                <input type="hidden" id="idperfil" name="idPerfil"
                       value ="${perfil.idPerfil}">

                <div class="form-group row mt-3 offset-md-2">
                    <label for="idnome"
                           class="col-md-3 col-form-label-lg">Nome</label>
                    <div class="col-md-5">
                        <input type="text" id="idnome" name="nome"
                               class="form-control" value="${perfil.nome}">
                    </div>


                </div>
                    
                <div class="form-group row mt-2 offset-md-2">
                    <label for="iddata"
                           class="col-md-3 col-form-label-lg">Data Cadastro
                    </label>
                    <div class="col-md-5">
                        <input type="date" id="iddata" name="dataCadastro"
                               class="form-control" value="${perfil.dataCadastro}">
                    </div>


                </div>
                    
                <div class="form-group row mt-2 offset-md-2">
                    <label for="idstatus"
                           class="col-md-3 col-form-label-lg">Status
                    </label>
                    <div class="col-md-5">
                        <select id="idstatus" name="status"
                                class="form-control">
                            <option value="">Escolha uma opção</option>
                            <option value="1"
                                    <c:if test="${perfil.status == 1}">
                                        selected=""</c:if>>Ativado
                                    </option>
                                    <option value="0"
                                    <c:if test="${perfil.status == 0}">
                                        selected=""</c:if>>Desativado
                                    </option>

                            </select>
                        </div>

                    </div>
                    
                    <div class="d-md-flex justify-content-md-end mt-2">
                        <button class="btn btn-primary btn-md mr-4">
                            Gravar&nbsp;<i class="fa-solid fa-floppy-disk"></i>
                        </button>
                        <a href="gerenciarPerfil?acao=listar"
                           class="btn btn-danger btn-md mr-3"
                           role="button">
                            Voltar&nbsp;<i class="fa-solid fa-cirle-left"></i>

                        </a>

                    </div>
                </form>
            </main>

        <jsp:include page="template/footer.jsp"></jsp:include>

        <!-- JQuery.js -->
        <script src="js/jquery-3.6.0.min.js"></script>

        <!-- Popper via cdn -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha512-Ua/7Woz9L5O0cwB/aYexmgoaD7lw3dWe9FvXejVdgqu71gRog3oJgjSWQR55fwWx+WKuk8cl7UwA1RS6QCadFA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- Bootstrap.js -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/sweetalert.js"></script>

    </body>
</html>
