
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
public class Usuario {
    private int idUsuario;
    private String nome;
    private String login;
    private String senha;
    private int status;
    private Perfil perfil;
    
}
