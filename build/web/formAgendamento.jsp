<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Agendamento,model.Cliente,model.Usuario,controller.GerenciarLogin,model.ClienteDAO,java.util.ArrayList,model.AgendamentoServico,model.Servico,model.ServicoDAO,java.sql.SQLException"%>
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
            <div id="conteudo"   >
                <div class="col-12">
                <%
                    Agendamento a = new Agendamento();
                    Cliente c = new Cliente();
                    ClienteDAO cdao = new ClienteDAO();
                    Usuario ulogado = new Usuario();
                    ulogado = GerenciarLogin.verificarAcesso(request, response);
                    request.setAttribute("ulogado",ulogado);
                    try {
                         String acao = request.getParameter("acao");
                         if(acao.equals("novo")){
                            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                            c = cdao.getCarregarPorId(idCliente);
                            a.setCliente(c);
                            a.setFotografo(ulogado);
                            a.setCarrinho(new ArrayList<AgendamentoServico>());
                            session.setAttribute("agendamento", a);
                        
                         }else{
                              a = (Agendamento) session.getAttribute("agendamento");
                         }
                        } catch(SQLException e) {
                            out.print("Erro" + e.getMessage());
                            e.printStackTrace();
                            
                            
                        }
                %>
                   
                </div>
                <div class="col-12" style="padding-bottom: 15px" >
                    <div>
                        <h4 class="text-center mt-1">
                            Catálogo(<%= a.getCarrinho().size() %>)
                        </h4>
                    </div>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <%
                                            ServicoDAO sdao = new ServicoDAO();
                                            ArrayList<Servico> lista = sdao.getLista();
                                            int salto = 0;
                                            for(Servico servico : lista){
                                        %>
                                        <th>
                                            <div>
                                                <img class="center" width="140" height="140"
                                                     src="servicoimagens/<%= servico.getCasamento.jpg()%>"
                                                     
                                            </div>
                                                <div>
                                                    <%=servico.getNome() %>
                                                </div>
                                                <div>
                                                    R$&nbsp;<fmt:formatNumber pattern="#,##0.00"
                                                    value="<%= servico.getValor()%>"/>
                                                </div>
                                                <div>
                                                    <input type="hidden" id="idServico"/>
                                                    <input type="text" name="qtd" value="1"
                                                           size="4" maxlength="3" readonly/>
                                                </div>
                                                <div>
                                                    <a href="gerenciarCarrinho?acao=add&idServico=<%=servico.getIdServico()%>&qtd=1"
                                                       class="btn btn-primary btn-sm" role="button">
                                                        Adicionar&nbsp;<i class="fas fa-cart-plus"></i>
                                                    </a>
                                                </div>
                                        <% salto++;
                                        if(salto == 3){
                                        %>
                                            </th>
                                            </tr>
                                            <%
                                                salto = 0;
                                        }
                                            }
                                        %>
                                   
                                </thead>
                            </table>
                        </div>
                </div>
                <div class="container">
                    <table class="table table-hover" id="listarMenus">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Link</th>
                                 <th>Icone</th>
                                <th>Exibir</th>
                                <th>Status</th>
                                <th>Ações</th>
                                
                            </tr>
                        </thead>
                        <c:forEach items="${menus}" var="m">
                        <tbody>
                            <tr>
                                <td>${m.nome}</td>
                                <td>${m.link}</td>
                                <td>${m.icone}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${m.exibir == 1}">
                                            Sim
                                        </c:when>
                                        <c:otherwise>
                                            Não
                                         </c:otherwise>
                                        
                                    </c:choose>
                                    
                                    
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${m.status == 1}">
                                            Ativado
                                        </c:when>
                                        <c:otherwise>
                                            Desativado
                                        </c:otherwise>
                                        
                                    </c:choose>
                                
                                  </td>
                                  <td>
                                      <a href="gerenciarMenu?acao=alterar&idMenu=${m.idMenu}"
                                         class="btn btn-info btn-md" role="button">
                                          Alterar&nbsp;<i class="fa-solid fa-pen-to-square"></i>
                                          
                                      </a>
                                         <script type="text/javascript">
                                             function confirmDesativar(id, nome){
                                                 if(confirm('Deseja desativar o menu ' + nome + '?')){
                                                     location.href="gerenciarMenu?acao=desativar&idMenu="+id;
                                                 }
                                             }
                                             
                                             function confirmAtivar(id, nome){
                                                 if(confirm('Deseja ativar o menu ' + nome + '?')){
                                                     location.href="gerenciarMenu?acao=ativar&idMenu="+id;
                                                 }
                                             }
                                         </script>
                                      
                                       
                                      <c:choose>
                                          <c:when test="${m.status == 1}">
                                              <button class="btn btn-danger btn-sm" 
                                                onclick="confirmDesativar('${m.idMenu}', '${m.nome}')">
                                                  Desativar&nbsp;
                                                  <i class="fa-solid fa-user-lock"></i>
                                               </button>
                                          </c:when>
                                          <c:otherwise>
                                              <button class="btn btn-sucesss btn-sm"
                                                onclick="confirmAtivar('${m.idMenu}', '${m.nome}')">
                                                  Ativar&nbsp;
                                                  <i class="fa-solid fa-user-shield"></i>
                                              </button>
                                              
                                          </c:otherwise>
                                              
                                          
                                      </c:choose>
                                      
                                  </td>
                            </tr>
                         </c:forEach>   
                        </tbody>
                        
                    </table>
                    
                    
                </div>
                
             
            </div>
            
            
        </div>
       
        <!-- JQuery.js -->
        <script src="js/jquery-3.6.0.min.js"></script>
        
        <!-- Popper via cdn -->
         <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha512-Ua/7Woz9L5O0cwB/aYexmgoaD7lw3dWe9FvXejVdgqu71gRog3oJgjSWQR55fwWx+WKuk8cl7UwA1RS6QCadFA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- Bootstrap.js -->
        <script src="js/bootstrap.min.js"></script>
        <!--jquery.datatables.min.js -->
        <script src="datatables/js/jquery.dataTables.min.js"></script>
        <!--dataTables.bootstrap4.min.js -->
        <script src="datatables/js/dataTables.bootstrap4.min.js"></script>
        
        <script type="text/javascript">
             $(document).ready(function () {
                $("#listarMenus").dataTable({
                    paging: false,
                    ordering: false,
                    info: false,
                    searching: false,
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
