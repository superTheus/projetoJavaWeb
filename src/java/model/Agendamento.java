
package model;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {
  private int  idAgendamento;
  private Double valorTotal;
  private int status;
  private Cliente cliente;
  private Usuario usuario;
  private ArrayList<AgendamentoServico> carrinho;
  
   
   
    
}
