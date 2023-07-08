
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Servico;
import model.ServicoDAO;

@WebServlet(name = "GerenciarServico", urlPatterns = {"/gerenciarServico"})
public class GerenciarServico extends HttpServlet {

  @Override
    protected void doGet(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idServico = request.getParameter("idServico");
        String mensagem = "";
        
     Servico s = new Servico();
     ServicoDAO sdao = new ServicoDAO();
        
        try {
            if(acao.equals("listar")){
                ArrayList<Servico> servico = new ArrayList<>();
                servico = sdao.getLista();
                RequestDispatcher dispatcher =
                        getServletContext().
                                getRequestDispatcher("/listarServico.jsp");
                request.setAttribute("servico", servico);
                dispatcher.forward(request, response);
                        
            
            }else if(acao.equals("alterar")){
                s = sdao.getCarregarPorId(
                        Integer.parseInt(idServico));
                if(s.getIdServico() > 0){
                    RequestDispatcher dispatcher =
                            getServletContext().
                                getRequestDispatcher("/cadastrarServico.jsp");
                    request.setAttribute("servico", s);
                    dispatcher.forward(request, response);
                    
                }else{
                    mensagem = "Servico não encontrado na base de dados!";
                }
            
            }else if(acao.equals("ativar")){
            s.setIdServico(Integer.parseInt(idServico));
            if(sdao.ativar(s)){
                mensagem = "Servico ativado com sucesso!";
            }else{
                 mensagem = "erro na alteração";
            }
            }else if(acao.equals("desativar")){
            s.setIdServico(Integer.parseInt(idServico));
             if(sdao.desativar(s)){
                mensagem = "Servico desativado com sucesso!";
            }else{
                 mensagem = "erro na alteração";
            }
            }else{
                response.sendRedirect("/index.jsp");
            }
            
        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }
        
        out.println(
                "<script type='text/javascript'>" +
                "alert('" + mensagem + "');" +
                "location.href='gerenciarServico?acao=listar';" +
                "</script>"
        );
        
      
        
    }
  
    @Override
    protected void doPost(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String idServico = request.getParameter("idServico");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String valor = request.getParameter("valor");
        
        String status = request.getParameter("status");
        String mensagem = "";
        
      Servico s = new Servico();
      ServicoDAO sdao = new ServicoDAO();
        try {
           
            
            if(!idServico.isEmpty()){
                s.setIdServico(Integer.parseInt(idServico));
            }
            
            if(nome.isEmpty() || nome.equals("")){
                request.setAttribute("msg", "Informe o nome do Servico!");
                getServletContext().
                        getRequestDispatcher("/cadastrarServico.jsp").
                        forward(request, response);
                
               
            }else{
                s.setNome(nome);
            }
             if(descricao.isEmpty() || descricao.equals("")){
                request.setAttribute("msg", "Informe a descricao!");
                getServletContext().
                        getRequestDispatcher("/cadastrarServico.jsp").
                        forward(request, response);
                
            }else{
                s.setDescricao(descricao);
            }
            
              if(valor.isEmpty() || valor.equals("")){
                request.setAttribute("msg", "Informe o valor!");
                getServletContext().
                        getRequestDispatcher("/cadastrarServico.jsp").
                        forward(request, response);
                
            }else{
                s.setValor(Double.parseDouble(valor));
            }
                
            
            
                if(status.isEmpty() || status.equals("")){
                request.setAttribute("msg", "Informe o status do Servico!");
                getServletContext().
                        getRequestDispatcher("/cadastrarServico.jsp").
                            forward(request, response);
            }else{
                s.setStatus(Integer.parseInt(status));
            }
            
            if(sdao.gravar(s)){
                mensagem = "Servico salvo na base de dados";
               
            }else{
                mensagem = "falha ao gravar o servico na base de dados";
            }
        } catch (SQLException e){
            mensagem = "Erro: " + e.getMessage();
           } 
       
        out.println(
                "<script type='text/javascript'>" +
                "alert('" + mensagem + "');" +
                "location.href='gerenciarServico?acao=listar';" +
                "</script>"
        
        );
    }
    
    
    
    
}

     
