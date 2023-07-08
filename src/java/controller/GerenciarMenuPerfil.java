
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;
import model.PerfilDAO;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;


/**
 *
 * @author ltp3etb
 */
@WebServlet(name = "GerenciarMenuPerfil", 
        urlPatterns = {"/gerenciarMenuPerfil"})
public class GerenciarMenuPerfil extends HttpServlet {

  
   
 
    @Override
    protected void doGet(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html; charset=utf-8");
        String acao = request.getParameter("acao");
        String idPerfil = request.getParameter("idPerfil");
        String mensagem = "";
        
        Perfil p = new Perfil();
        PerfilDAO pdao = new PerfilDAO();
        
        try {
            
            if(acao.equals("vincular")){
                p = pdao.getCarregarPorId(
                      Integer.parseInt(idPerfil));
                if(p.getIdPerfil() > 0){
                    RequestDispatcher dispatcher =
                        getServletContext().
                            getRequestDispatcher("/cadastrarMenuPerfil.jsp");
                    request.setAttribute("perfilv", p);
                    dispatcher.forward(request, response);
                           
                }else{
                    mensagem = 
                        "Perfil não encontrado na base de dados!";
                }
            
        }else if(acao.equals("desvincular")){
            String idMenu = request.getParameter("idMenu");
            if(idMenu.equals("") || idMenu.isEmpty()){
                request.setAttribute("msg", "Selecione um menu");
                despacharRequisicao(request, response);
            }else{
                int perfilId = Integer.parseInt(idPerfil);
                int menuId = Integer.parseInt(idMenu);
                if(pdao.desvincular(menuId, perfilId)){
                    mensagem = "Menu desvinculado com sucesso!";
                }else{
                    mensagem = "Falha ao desvincular o menu!";
                }
                
            }
        }else{
            response.sendRedirect("index.jsp");
        }
            
        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }
        
        out.println(
            "<script type='text/javascript'>" +
            "alert('" +mensagem+ "');" +
            "location.href='gerenciarMenuPerfil?acao=vincular&idPerfil="
                +idPerfil+"';" +
            "</script>"
        );
        
       
        
        
        
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String idMenu = request.getParameter("idMenu");
        String idPerfil = request.getParameter("idPerfil");
        String mensagem = "";
        
        try {
            PerfilDAO pdao = new PerfilDAO();
            if(idMenu.equals("") || idMenu.isEmpty()){
                request.setAttribute("msg", "Escolha um Menu!");
                despacharRequisicao(request, response);
            }else{
                int menuId = Integer.parseInt(idMenu);
                int perfilId = Integer.parseInt(idPerfil);
                if(pdao.vincular(menuId, perfilId)){
                    mensagem = "Menu vinculado com sucesso!";
                }else{
                    mensagem = "Falha ao vincular o menu!";
                }
            }
            
        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }
        
         out.println(
            "<script type='text/javascript'>" +
            "alert('" +mensagem+ "');" +
            "location.href='gerenciarMenuPerfil?acao=vincular&idPerfil="
                +idPerfil+"';" +
            "</script>"
        );
        
        
    }
    
    private void despacharRequisicao(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException{
            getServletContext().
                getRequestDispatcher("/cadastrarMenuPerfil.jsp").
                    forward(request, response);
        
    }

   

}
