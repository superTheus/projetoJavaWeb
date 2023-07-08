
package model;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Servico {
    private int idServico;
    private String nome;
    private String descricao;
    private Double valor;
   
    private int status; 
    
}
