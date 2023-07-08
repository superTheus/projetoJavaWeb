
package model;
import java.sql.Time;
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
 
public class AgendamentoServico {
    private Long idAgendamentoServico;
    private Agendamento agendamento;
    private Servico servico;
    private Date data;
    private Time horario;
    
}
