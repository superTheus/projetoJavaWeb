
package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    //Configuração dos parâmetros de configuração com o BD 
    
    private static final String URL = 
        "jdbc:mysql://localhost:3306/projetoetb2023?useTimeZone=true&serverTimeZone=UTC&useSSL=false";
    private static final String USUARIO = "root";
    private static final String SENHA = "senhaSimples2023@";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    //Configuração das propriedas useTimeZone, serverTimeZone e SSL.
    
    
    public static Connection conectar()
        throws SQLException{
        Connection conexao = null;
        
        try {
             Class.forName(DRIVER);
             conexao = 
                DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.out.println(
                    "Falha ao registrar o driver: "
                    + e.getMessage()
            );
        } 
             
        
        return conexao;
        
    }
    
    public static void close(Connection conexao)
        throws SQLException{
        if(conexao != null){
            conexao.close();
            
        }
    }
    
    
}
