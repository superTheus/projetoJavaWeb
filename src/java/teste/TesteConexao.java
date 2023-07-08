
package teste;
import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.SQLException;


public class TesteConexao {

 
    public static void main(String[] args) {
        
        
        try {
            Connection conexao = null;
        
            conexao = ConexaoFactory.conectar();
            System.out.println("Conexão Efetuada com sucesso!");
            
            ConexaoFactory.close(conexao);
            System.out.println("Conexão Efetuada com sucesso!");
            
            
        } catch (SQLException e) {
            System.out.println("Falha na comunicação com o bd: "
                + e.getMessage());
        }
    }
    
}
