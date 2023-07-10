
package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;


public class PerfilDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    
    public ArrayList<Perfil> getLista()throws SQLException{
        ArrayList<Perfil> perfis = 
                new ArrayList<>();
        sql = "SELECT idPerfil, nome, dataCadastro, status " +
               "FROM perfil";
        con = ConexaoFactory.conectar();
        ps  = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while(rs.next()){
            Perfil p  = new Perfil();
            p.setIdPerfil(rs.getInt("idPerfil"));
            p.setNome(rs.getString("nome"));
            p.setDataCadastro(rs.getDate("dataCadastro"));
            p.setStatus(rs.getInt("status"));
            
            perfis.add(p);
            
        }
        
           ConexaoFactory.close(con);
      
       
        
        
        return perfis;
    }
    
    public boolean gravar(Perfil p)throws SQLException{
        con = ConexaoFactory.conectar();
        
        if(p.getIdPerfil() == 0){
            sql = "INSERT INTO perfil (nome, dataCadastro, status) " +
                  "VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setDate(2, new Date(p.getDataCadastro().getTime()));
            ps.setInt(3, p.getStatus());
            
        }else{
            sql = 
               "UPDATE perfil set nome = ?, dataCadastro = ?, status = ? " +
               "WHERE idPerfil = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setDate(2, new Date(p.getDataCadastro().getTime()));
            ps.setInt(3, p.getStatus());
            ps.setInt(4, p.getIdPerfil());
                  
            
        }
        
        ps.executeUpdate();
        ConexaoFactory.close(con);
               
        return true;
    }
    
    public Perfil getCarregarPorId(int idPerfil)
        throws SQLException{
        Perfil p = new Perfil();
        sql = "SELECT idPerfil, nome, dataCadastro, status " +
              "FROM perfil WHERE idPerfil = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idPerfil);
        rs = ps.executeQuery();
        if(rs.next()){
            p.setIdPerfil(rs.getInt("idPerfil"));
            p.setNome(rs.getString("nome"));
            p.setDataCadastro(rs.getDate("dataCadastro"));
            p.setStatus(rs.getInt("status"));
            
        }
        
        ConexaoFactory.close(con);
        
        
        return p;
        
    }
    
    public Perfil get(int idPerfil)
        throws SQLException{
        Perfil p = new Perfil();
        sql = "SELECT idPerfil, nome, dataCadastro, status " +
              "FROM perfil WHERE idPerfil = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idPerfil);
        rs = ps.executeQuery();
        if(rs.next()){
            p.setIdPerfil(rs.getInt("idPerfil"));
            p.setNome(rs.getString("nome"));
            p.setDataCadastro(rs.getDate("dataCadastro"));
            p.setStatus(rs.getInt("status"));
            
        }
        
        ConexaoFactory.close(con);
        
        
        return p;    
    }
    
    public ArrayList<Menu> getMenus(int idPerfil)
        throws SQLException{
        ArrayList<Menu> menus = new ArrayList<>();
        sql = "SELECT M.idmenu, M.nome, M.link, M.exibir FROM menu M\n" +
            "INNER JOIN menu_perfil MP ON MP.idmenu = M.idmenu\n" +
            "INNER JOIN perfil P ON P.idPerfil = MP.idPerfil\n" +
            "WHERE P.idPerfil = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idPerfil);
        rs = ps.executeQuery();      
        
        if(rs.next()){
            Menu m = new Menu();
            
            m.setIdMenu(rs.getInt("M.idmenu"));
            m.setNome(rs.getString("M.nome"));
            m.setLink(rs.getString("M.link"));
            m.setExibir(rs.getInt("M.exibir"));
            
            menus.add(m);
        }
        
        ConexaoFactory.close(con);
        
        return menus;    
    }
    
    public boolean desativar(Perfil p)throws SQLException{
        sql = "UPDATE perfil set status = 0 " +
              "WHERE idPerfil = ?";
        
        con = ConexaoFactory.conectar();
        
        ps = con.prepareStatement(sql);
        ps.setInt(1, p.getIdPerfil());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        
        return true;
    }
    
    public boolean ativar(Perfil p)throws SQLException{
        sql = "UPDATE PERFIL set status = 1 " +
              "WHERE idPerfil = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, p.getIdPerfil());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        
        
        return true;
    }
     public ArrayList<Menu> menusVinculadosPorPerfil(int idPerfil)
        throws SQLException {
        ArrayList<Menu> lista = new ArrayList<>();
        
        sql = "SELECT m.idMenu, m.nome, m.link, m.icone, " +
              "m.exibir, m.status " +
              "FROM menu as m, menu_perfil as mp " +
              "WHERE mp.idMenu = m.idMenu " +
              "AND mp.idPerfil = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idPerfil);
        rs = ps.executeQuery();
        
        while(rs.next()){
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("m.idMenu"));
            m.setNome(rs.getString("m.nome"));
            m.setLink(rs.getString("m.link"));
            m.setIcone(rs.getString("m.icone"));
            m.setExibir(rs.getInt("m.exibir"));
            m.setStatus(rs.getInt("m.status"));
            lista.add(m);
            
            
        }
        ConexaoFactory.close(con);
        return lista;
        
    }
    
    public ArrayList<Menu> menusNaoVinculadosPorPerfil(int idPerfil)
        throws SQLException{
        ArrayList<Menu> lista = new ArrayList<>();
        sql = "SELECT m.idMenu, m.nome, m.link, m.icone, " +
              "m.exibir, m.status " +
              "FROM menu as m " +
              "WHERE m.idMenu " +
              "NOT IN(SELECT mp.idMenu FROM menu_perfil as mp " +
              "WHERE mp.idPerfil = ?)";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idPerfil);
        rs = ps.executeQuery();
        while(rs.next()){
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("m.idMenu"));
            m.setNome(rs.getString("m.nome"));
            m.setLink(rs.getString("m.link"));
            m.setIcone(rs.getString("m.icone"));
            m.setExibir(rs.getInt("m.exibir"));
            m.setStatus(rs.getInt("m.status"));
            lista.add(m);
            
        }
        ConexaoFactory.close(con);
        return lista;
        
        
    }
    
    public boolean vincular(int idMenu, int idPerfil)
        throws SQLException{
        sql = "INSERT into menu_perfil (idMenu, idPerfil) " +
              "VALUES (?, ?)";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idMenu);
        ps.setInt(2, idPerfil);
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }
    
    public boolean desvincular(int idMenu, int idPerfil)
        throws SQLException{
        sql = "DELETE FROM menu_perfil " +
              "WHERE idMenu = ? AND idPerfil = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idMenu);
        ps.setInt(2, idPerfil);
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
        
    }
 
    
    
    
}
